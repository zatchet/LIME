package controller.commands;

import java.io.IOException;
import java.util.Scanner;
import model.LayeredImageEditor;

/**
 * A command object for sepia-ing a layer in the project. Contains a single apply()
 * method that executes the operation.
 */
public class SepiaCommand implements CommandOperation {

  private final LayeredImageEditor model;
  private final Scanner scan;

  /**
   * Constructs a SepiaCommand object.
   * @param model the model to process images with
   * @param scan the scanner to get input from
   */
  public SepiaCommand(LayeredImageEditor model, Scanner scan) {
    this.model = model;
    this.scan = scan;
  }

  @Override
  public void apply() throws IOException {
    if (scan.hasNextInt()) {
      int layer = scan.nextInt();
      model.sepia(layer);
    }
  }
}