package controller.commands;

import java.io.IOException;
import java.util.Scanner;
import model.LayeredImageEditor;

/**
 * A command object for making a layer in the project. Contains a single apply() method that
 * executes the operation.
 */

public class MakeCommand implements CommandOperation {

  private final LayeredImageEditor model;
  private final Scanner scan;

  /**
   * Creates a make command object.
   *
   * @param model the model
   * @param scan  the scanner
   */
  public MakeCommand(LayeredImageEditor model, Scanner scan) {
    this.model = model;
    this.scan = scan;
  }

  @Override
  public void apply() throws IOException {
    if (scan.hasNext()) {
      String s1 = scan.next();
      if (scan.hasNextInt()) {
        int num = scan.nextInt();
        processMake(s1, num);
      }
    }
  }

  private void processMake(String visibility, int layer) throws IOException {
    if (visibility.equals("visible")) {
      model.makeVisible(layer);
    } else if (visibility.equals("invisible")) {
      model.makeInvisible(layer);
    } else {
      throw new IllegalArgumentException("Make must be followed by either visible or invisible");
    }
  }
}
