package controller;

import controller.commands.AddCheckerBoardCommand;
import controller.commands.AddCopyCommand;
import controller.commands.AddFromImageCommand;
import controller.commands.BlurCommand;
import controller.commands.CommandOperation;
import controller.commands.ExportAllCommand;
import controller.commands.ExportCommand;
import controller.commands.GreyscaleCommand;
import controller.commands.MakeCommand;
import controller.commands.RemoveCommand;
import controller.commands.SepiaCommand;
import controller.commands.SharpenCommand;
import java.io.IOException;
import java.util.Scanner;
import model.LayeredImageEditor;
import model.LayeredImageEditorImpl;
import view.ImageEditorView;
import view.SimpleView;

/**
 * A simple controller for an image editor. See USEME.md for the supported commands. Can read from
 * any readable and write to any appendable.
 */
public class SimpleController implements ImageEditorController {

  private Readable rd;
  protected ImageEditorView view;
  protected LayeredImageEditor model;

  /**
   * Creates a controller with the given parameters.
   *
   * @param rd the readable
   * @param ap the appendable
   */
  public SimpleController(Readable rd, Appendable ap) {
    if (rd == null || ap == null) {
      throw new IllegalArgumentException("Readable and appendable must not be null");
    }
    this.rd = rd;
    this.view = new SimpleView(ap);
  }

  public SimpleController(ImageEditorView view) {
    this.view = view;
  }

//  public SimpleController(LayeredImageEditor model, Readable rd, Appendable ap) {
//    this(rd, ap);
//    this.model = model;
//  }

  protected void setRd(Readable rd) {
    this.rd = rd;
  }

  @Override
  public void run() throws IOException {
    Scanner scan = new Scanner(rd);
    while (scan.hasNext()) {
      CommandOperation cmd = null;
      switch (scan.next()) {
        case "create":
          processCreate(scan);
          break;
        case "load":
          processLoad(scan);
          break;
        case "createCheckerboard":
          createCheck(scan);
          break;
        case "sepia":
          if (checkNullModel()) {
            break;
          }
          cmd = new SepiaCommand(model, scan);
          break;
        case "greyscale":
          if (checkNullModel()) {
            break;
          }
          cmd = new GreyscaleCommand(model, scan);
          break;
        case "blur":
          if (checkNullModel()) {
            break;
          }
          cmd = new BlurCommand(model, scan);
          break;
        case "sharpen":
          if (checkNullModel()) {
            break;
          }
          cmd = new SharpenCommand(model, scan);
          break;
        case "export":
          if (checkNullModel()) {
            break;
          }
          cmd = new ExportCommand(model, scan);
          break;
        case "exportAll":
          if (checkNullModel()) {
            break;
          }
          cmd = new ExportAllCommand(model, scan);
          break;
        case "make":
          if (checkNullModel()) {
            break;
          }
          cmd = new MakeCommand(model, scan);
          break;
        case "addCopy":
          if (checkNullModel()) {
            break;
          }
          cmd = new AddCopyCommand(model);
          break;
        case "addFromImage":
          if (checkNullModel()) {
            break;
          }
          cmd = new AddFromImageCommand(model, scan);
          break;
        case "addCheckerboard":
          if (checkNullModel()) {
            break;
          }
          cmd = new AddCheckerBoardCommand(model, scan);
          break;
        case "remove":
          if (checkNullModel()) {
            break;
          }
          cmd = new RemoveCommand(model, scan);
          break;
        default:
          view.renderMessage("Unknown command\n");
      }
      if (cmd != null) {
        try {
          cmd.apply();
        } catch (IllegalArgumentException | IllegalStateException | IOException e) {
          view.renderMessage(e.getMessage() + "\n");
        }
      }
    }
  }

  //assigns model field to a layered image based off the given parameters
  protected void createHelper(String fileType, String fileName) {
    FileUtil.checkNotSupported(fileType);
    FileUtil.checkNotMatches(fileType, fileName);
    ImageCreator creator;
    if (fileType.equals("ppm")) {
      creator = new ImageFromPPM();
    } else {
      creator = new ImageFromJPEGPNG();
    }
    model = new LayeredImageEditorImpl(creator.create(fileName));
  }

  //loads in a multilayered image
  protected void loadHelper(String fileType, String fileName) {
    LayeredImageImporter creator = new LayeredImageImporterImpl(fileType);
    FileUtil.checkNotSupported(fileType);
    model = creator.create(fileName);
  }

  //enforces that the model cannot be null
  protected boolean checkNullModel() throws IOException {
    if (model == null) {
      view.renderMessage("need to create or load first\n");
      return true;
    }
    return false;
  }

  //creates multilayered image
  private void processCreate(Scanner scan) throws IOException {
    if (scan.hasNext()) {
      String s1 = scan.next();
      if (scan.hasNext()) {
        String s2 = scan.next();
        try {
          createHelper(s1, s2);
        } catch (IllegalArgumentException e) {
          view.renderMessage(e.getMessage() + "\n");
        }
      }
    }
  }

  //loads in a multilayered image
  private void processLoad(Scanner scan) throws IOException {
    if (scan.hasNext()) {
      String s1 = scan.next();
      if (scan.hasNext()) {
        String s2 = scan.next();
        try {
          loadHelper(s1, s2);
        } catch (IllegalArgumentException e) {
          view.renderMessage(e.getMessage() + "\n");
        }
      }
    }
  }

  //creates a multilayered image with a checkerboard as the starting point
  protected void createCheck(Scanner scan) throws IOException {
    if (scan.hasNextInt()) {
      int n1 = scan.nextInt();
      if (scan.hasNextInt()) {
        int n2 = scan.nextInt();
        try {
          model = new LayeredImageEditorImpl(n1, n2);
        } catch (IllegalArgumentException e) {
          view.renderMessage(e.getMessage() + "\n");
        }
      }
    }
  }
}