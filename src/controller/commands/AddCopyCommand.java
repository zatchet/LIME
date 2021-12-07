package controller.commands;

import java.io.IOException;
import model.LayeredImageEditor;

/**
 * A command object for adding a copy to the current project. Contains a single apply()
 * method that executes the operation.
 */
public class AddCopyCommand implements CommandOperation {

  private final LayeredImageEditor model;

  /**
   * Creates a copy command object.
   * @param model the model
   */
  public AddCopyCommand(LayeredImageEditor model) {
    this.model = model;
  }

  @Override
  public void apply() throws IOException {
    model.addLayer();
  }
}
