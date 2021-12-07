package controller;

import java.io.IOException;

/**
 * Represents a controller for an ImageEditor model. The controller processes inputs from a Readable
 * and executes commands in the model accordingly. The controller interacts with both the
 * view and the model.
 */
public interface ImageEditorController {

  /**
   * Starts the controller. Begins parsing input and executing commands in the model accordingly.
   * Runs until the readable runs out.
   * @throws IOException if writing to the given appendable failed.
   */
  void run() throws IOException;

}
