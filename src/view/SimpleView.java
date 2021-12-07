package view;

import java.io.IOException;

/**
 * Represents a basic view implementation that renders messages to the user. Any appendable can be
 * provided.
 */
public class SimpleView implements ImageEditorView {

  private final Appendable ap;

  /**
   * Constructs a SimpleView object.
   *
   * @param ap the appendable to transmit messages to
   */
  public SimpleView(Appendable ap) {
    if (ap == null) {
      throw new IllegalArgumentException("appendable cannot be null");
    }
    this.ap = ap;
  }

  @Override
  public void renderMessage(String message) throws IOException {
    try {
      ap.append(message);
    } catch (IOException i) {
      throw new IOException("Message rendering failed: " + i);
    }
  }
}
