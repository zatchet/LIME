package view;

import java.io.IOException;

/**
 * Represents the operations associated with a view for in image editor. The view receives
 * instructions from the controller. This interface contains just one method renderMessage().
 */
public interface ImageEditorView {

  /**
   * Appends the given message to the desired output location.
   * @param message the message
   * @throws IOException if writing to the appendable fails
   */
  void renderMessage(String message) throws IOException;

}
