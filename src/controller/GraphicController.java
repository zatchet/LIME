package controller;

import controller.commands.AddFromImageCommand;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Objects;
import java.util.Scanner;
import model.Color;
import model.Image;
import model.LayerImpl;
import model.LayeredImageEditor;
import model.LayeredImageEditorImpl;
import view.InteractiveView;

/**
 * Represents the controller for processing commands from the Guided User Interface. This is the
 * action listener for the GraphicalView and handles all the model operations when a button is
 * clicked to process an image in the GUI.
 */
public class GraphicController extends SimpleController implements
    ImageEditorController, ActionListener {

  InteractiveView newView;

  /**
   * Constructs a GraphiController object.
   *
   * @param view the view setting up the GUI
   */
  public GraphicController(InteractiveView view) {
    super(view);
    if (view == null) {
      throw new IllegalArgumentException("The model and view cannot be null");
    }
  }

  @Override
  public void run() {
    newView = (InteractiveView) view;
    newView.registerViewEventListener(this);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      handleAllActions(e.getActionCommand());
    } catch (IllegalArgumentException | IllegalStateException | IOException i) {
      try {
        view.renderMessage(i.getMessage());
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    }
  }

  //handles all actions
  private void handleAllActions(String s) throws IOException {
    switch (s) {
      case "load":
        try {
          File file = newView.chooseFile();
          String loadFilename = file.getAbsolutePath();
          if (file.isDirectory()) {
            String ext = Objects.requireNonNull(newView.askForExt());
            loadHelper(ext, loadFilename);
          } else {
            createHelper(FileUtil.getFileExtension(loadFilename), loadFilename);
          }
        } catch (NullPointerException n) {
          break;
        }
        break;
      case "save":
        if (checkNullModel()) {
          break;
        }
        int saveMethod = newView.askForSaveMethod();
        if (saveMethod < 0) {
          break;
        }
        try {
          String projectName = Objects.requireNonNull(newView.askForProjectName());
          String path = newView.chooseFile().getAbsolutePath() + File.separator + projectName;
          String extension = Objects.requireNonNull(newView.askForExt());
          if (saveMethod == 1) {
            new LayeredImageExporterImpl(model).exportAll(path, extension);
          } else if (saveMethod == 0) {
            new LayeredImageExporterImpl(model).exportTop(path + "." + extension, extension);
          }
        } catch (NullPointerException n) {
          break;
        }
        break;
      case "execute":
        try {
          File file = newView.chooseFile();
          if (!FileUtil.getFileExtension(file.getAbsolutePath()).equals("txt")) {
            throw new IllegalArgumentException("Not a txt file!");
          }
          setRd(new FileReader(file));
          super.run();
        } catch (NullPointerException n) {
          break;
        }
        break;
      case "grey":
        if (checkNullModel()) {
          break;
        }
        model.greyscale(getTopVisibleIndex(model));
        break;
      case "sepia":
        if (checkNullModel()) {
          break;
        }
        model.sepia(getTopVisibleIndex(model));
        break;
      case "sharpen":
        if (checkNullModel()) {
          break;
        }
        model.sharpen(getTopVisibleIndex(model));
        break;
      case "blur":
        if (checkNullModel()) {
          break;
        }
        model.blur(getTopVisibleIndex(model));
        break;
      case "visible":
        if (checkNullModel()) {
          break;
        }
        try {
          int layer = newView.askForLayer(model.getNumLayers());
          model.makeVisible(layer);
        } catch (NullPointerException n) {
          break;
        }
        break;
      case "invisible":
        if (checkNullModel()) {
          break;
        }
        try {
          int layerr = newView.askForLayer(model.getNumLayers());
          model.makeInvisible(layerr);
        } catch (NullPointerException n) {
          break;
        }
        break;
      case "revert":
        if (checkNullModel()) {
          break;
        }
        model.getLayer(getTopVisibleIndex(model)).revert();
        break;
      case "addCopy":
        if (checkNullModel()) {
          break;
        }
        model.addLayer();
        break;
      case "addImage":
        if (checkNullModel()) {
          break;
        }
        try {
          String ext = Objects.requireNonNull(newView.askForExt());
          String fileName = newView.chooseFile().getAbsolutePath();
          new AddFromImageCommand(model, new Scanner(new StringReader(ext + " " + fileName)))
              .apply();
        } catch (NullPointerException n) {
          break;
        }
        break;
      case "remove":
        if (checkNullModel()) {
          break;
        }
        try {
          model.removeLayer(newView.askForLayer(model.getNumLayers()));
        } catch (NullPointerException n) {
          break;
        }
        break;
      case "checker":
        try {
          int[] dim = newView.getCheckDimensions();
          model = new LayeredImageEditorImpl(dim[0], dim[1]);
        } catch (NullPointerException e) {
          break;
        }
        break;
      case "addCheck":
        if (checkNullModel()) {
          break;
        }
        try {
          int[] dims = newView.getCheckDimensions();
          model.addLayer(new LayerImpl(dims[0], dims[1]));
        } catch (NullPointerException e) {
          break;
        }
        break;
      default:
        //will never get here because only action commands are switched. An action must occur
        //to enter the switch statement.
    }

    if (model != null) {
      newView.refreshImage(toBuff());
      newView.refreshMessageBar(getTopVisibleIndex(model) + 1, model.getNumLayers());
    }
  }

  //gets the top visible index
  private int getTopVisibleIndex(LayeredImageEditor model) {
    for (int i = model.getNumLayers() - 1; i >= 0; i--) {
      if (model.getLayer(i).visible()) {
        return i;
      }
    }
    throw new IllegalStateException("No visible layers to display");
  }

  // creates a buffered image representation of the current image
  private BufferedImage toBuff() {
    Image image;
    try {
      image = model.getLayer(getTopVisibleIndex(model));
    } catch (IllegalStateException e) {
      return makeBlank(model.getLayer(model.getNumLayers() - 1));
    }
    //makes the buffered image using the images pixel data
    BufferedImage buffered = new BufferedImage(image.getWidth(), image.getHeight(),
        BufferedImage.TYPE_3BYTE_BGR);

    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        Color col = image.getPixelAt(i, j).getColor();
        int rgb = col.getRed();
        rgb = (rgb << 8) + col.getGreen();
        rgb = (rgb << 8) + col.getBlue();
        buffered.setRGB(j, i, rgb);
      }
    }
    return buffered;
  }

  //makes a blank buffered image. this is displayed when no layers in the model are visible.
  private BufferedImage makeBlank(Image image) {
    BufferedImage buffered = new BufferedImage(image.getWidth(), image.getHeight(),
        BufferedImage.TYPE_3BYTE_BGR);
    for (int i = 0; i < image.getWidth(); i++) {
      for (int j = 0; j < image.getHeight(); j++) {
        buffered.setRGB(i, j, 65536 * 200 + 256 * 200 + 200);
      }
    }
    return buffered;

  }

}
