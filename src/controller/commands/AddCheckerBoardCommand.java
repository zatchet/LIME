package controller.commands;

import java.io.IOException;
import java.util.Scanner;
import model.LayerImpl;
import model.LayeredImageEditor;

/**
 * A command object for adding a checkerboard to the current project. Contains a single apply()
 * method that executes the operation.
 */
public class AddCheckerBoardCommand implements CommandOperation {

  private final LayeredImageEditor model;
  private final Scanner scan;

  /**
   * Creates an add checkerboard command object.
   *
   * @param model the model
   * @param scan  the scanner
   */
  public AddCheckerBoardCommand(LayeredImageEditor model, Scanner scan) {
    this.model = model;
    this.scan = scan;
  }

  @Override
  public void apply() throws IOException {
    if (scan.hasNextInt()) {
      int n1 = scan.nextInt();
      if (scan.hasNextInt()) {
        int n2 = scan.nextInt();
        model.addLayer(new LayerImpl(n1, n2));
      }
    }
  }
}
