package controller.commands;

import java.io.IOException;
import java.util.Scanner;
import model.LayeredImageEditor;

/**
 * A command object for sharpening a layer in the project. Contains a single apply()
 * method that executes the operation.
 */
public class SharpenCommand implements CommandOperation {

  private final LayeredImageEditor model;
  private final Scanner scan;

  /**
   * Creates a sharpen command object.
   * @param model the model
   * @param scan the scanner
   */
  public SharpenCommand(LayeredImageEditor model, Scanner scan) {
    this.model = model;
    this.scan = scan;
  }

  @Override
  public void apply() throws IOException {
    if (scan.hasNextInt()) {
      int layer = scan.nextInt();
      model.sharpen(layer);
    }
  }
}