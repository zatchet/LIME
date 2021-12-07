package controller.commands;

import java.io.IOException;

/**
 * Interface for a command operation. Operation can be applied to a layered image editor.
 */
public interface CommandOperation {

  /**
   * Applies the operation to the model.
   * @throws IOException if I/O fails.
   */
  void apply() throws IOException;
}
