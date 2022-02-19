package view;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import org.junit.Test;

/**
 * Tests for the correct functionality of the SimpleView class.
 */
public class SimpleViewTest {

  @Test(expected = IllegalArgumentException.class)
  public void testNullAppendable() {
    ImageEditorView view = new SimpleView(null);
  }

  @Test
  public void testRenderMessage() throws IOException {
    Appendable ap = new StringBuilder();
    ImageEditorView view = new SimpleView(ap);
    view.renderMessage("Hello World.");
    assertEquals("Hello World.", ap.toString());
  }

  @Test(expected = IOException.class)
  public void testBadAppendable() throws IOException {
    Appendable ap = new BadAppendable();
    ImageEditorView view = new SimpleView(ap);
    view.renderMessage("Hello World.");
    assertEquals("Hello World.", ap.toString());
  }
}
