package controller.commands;

import controller.FileUtil;
import controller.ImageCreator;
import controller.ImageFromJPEGPNG;
import controller.ImageFromPPM;
import java.io.IOException;
import java.util.Scanner;
import model.LayeredImageEditor;
import model.LayerImpl;

/**
 * A command object for adding a from an image to the current project. Contains a single apply()
 * method that executes the operation.
 */
public class AddFromImageCommand implements CommandOperation {

  private final LayeredImageEditor model;
  private final Scanner scan;

  /**
   * Creates an add from image command object.
   * @param model the model
   * @param scan the scanner
   */
  public AddFromImageCommand(LayeredImageEditor model, Scanner scan) {
    this.model = model;
    this.scan = scan;
  }

  @Override
  public void apply() throws IOException {
    if (scan.hasNext()) {
      String s1 = scan.next();
      if (scan.hasNext()) {
        String s2 = scan.next();
        processAddImage(s1, s2);
      }
    }
  }

  private void processAddImage(String fileType, String fileName) throws IOException {
    FileUtil.checkNotSupported(fileType);
    FileUtil.checkNotMatches(fileType, fileName);
    ImageCreator creator;

    if (fileType.equals("ppm")) {
      creator = new ImageFromPPM();
    } else {
      creator = new ImageFromJPEGPNG();
    }
    model.addLayer(new LayerImpl(creator.create(fileName)));
  }
}
