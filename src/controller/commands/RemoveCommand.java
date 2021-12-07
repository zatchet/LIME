package controller.commands;

import java.io.IOException;
import java.util.Scanner;
import model.LayeredImageEditor;

/**
 * A command object for removing a layer in the project. Contains a single apply()
 * method that executes the operation.
 */
public class RemoveCommand implements CommandOperation {

  private final LayeredImageEditor model;
  private final Scanner scan;

  /**
   * Creates a remove command object.
   * @param model the model
   * @param scan the scanner
   */
  public RemoveCommand(LayeredImageEditor model, Scanner scan) {
    this.model = model;
    this.scan = scan;
  }

  @Override
  public void apply() throws IOException {
    if (scan.hasNextInt()) {
      int layer = scan.nextInt();
      model.removeLayer(layer);
    }
  }
}
