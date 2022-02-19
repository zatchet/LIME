package model;

import static org.junit.Assert.assertEquals;

import controller.ImageFromPPM;
import java.io.IOException;
import org.junit.Test;

/**
 * Tests the correct functionality of the ImageFromPPM class which creates an Image object from a
 * PPM file.
 */
public class ImageFromPPMTest {

  @Test
  public void testCreateWidthHeight() {
    assertEquals(1024, new ImageFromPPM().create("Koala.ppm").getWidth());
    assertEquals(768, new ImageFromPPM().create("Koala.ppm").getHeight());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNullFilename() {
    new ImageFromPPM().create(null);
  }

  @Test
  public void testCreateData() {
    Appendable out = new StringBuilder();

    Image testImage = new ImageFromPPM().create("Koala.ppm");

    //just testing 50 elements
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 5; j++) {
        try {
          out.append(testImage.getPixelAt(i, j).toString());
          out.append("\n");
        } catch (IOException e) {
          System.out.println("failed");
        }
      }
    }
    assertEquals(""
        + "(101, 90, 58)\n"
        + "(103, 92, 62)\n"
        + "(110, 95, 66)\n"
        + "(104, 91, 59)\n"
        + "(104, 91, 59)\n"
        + "(102, 89, 57)\n"
        + "(108, 94, 65)\n"
        + "(106, 92, 65)\n"
        + "(100, 86, 59)\n"
        + "(97, 83, 54)\n"
        + "(106, 95, 65)\n"
        + "(107, 93, 66)\n"
        + "(106, 94, 68)\n"
        + "(103, 89, 63)\n"
        + "(100, 90, 63)\n"
        + "(110, 95, 66)\n"
        + "(99, 83, 57)\n"
        + "(98, 84, 57)\n"
        + "(109, 95, 68)\n"
        + "(110, 99, 71)\n"
        + "(107, 91, 65)\n"
        + "(98, 84, 57)\n"
        + "(97, 83, 56)\n"
        + "(100, 91, 60)\n"
        + "(104, 93, 65)\n"
        + "(111, 95, 72)\n"
        + "(104, 89, 66)\n"
        + "(100, 85, 62)\n"
        + "(100, 90, 65)\n"
        + "(99, 84, 61)\n"
        + "(115, 97, 75)\n"
        + "(100, 83, 63)\n"
        + "(99, 84, 63)\n"
        + "(104, 89, 68)\n"
        + "(103, 88, 67)\n"
        + "(102, 91, 63)\n"
        + "(99, 88, 60)\n"
        + "(98, 92, 60)\n"
        + "(104, 95, 66)\n"
        + "(101, 94, 66)\n"
        + "(101, 94, 66)\n"
        + "(100, 93, 64)\n"
        + "(102, 96, 64)\n"
        + "(106, 95, 67)\n"
        + "(100, 93, 64)\n"
        + "(100, 88, 62)\n"
        + "(100, 91, 62)\n"
        + "(99, 94, 62)\n"
        + "(106, 97, 68)\n"
        + "(104, 95, 66)\n", out.toString());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNotPPM() {
    Image testImage = new ImageFromPPM().create("Test/clifford.jpeg");
  }

}
