package model;

import controller.ImageExporter;
import controller.ImageFromPPM;
import controller.PPM;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests the functionality of the PPM class.
 */
public class PPMTest {

  private Image nullImage;
  private ImageExporter exporter;

  private final Image simpleImage = new ImageImpl(new Pixel[][]{
      {new Pixel(new ClampedColor(10, 50, 110)),
          new Pixel(new ClampedColor(60, 90, 0)),
          new Pixel(new ClampedColor(10, 50, 110))},

      {new Pixel(new ClampedColor(60, 90, 0)),
          new Pixel(new ClampedColor(10, 50, 110)),
          new Pixel(new ClampedColor(60, 90, 0))},

      {new Pixel(new ClampedColor(10, 50, 110)),
          new Pixel(new ClampedColor(60, 90, 0)),
          new Pixel(new ClampedColor(10, 50, 110))}});

  @Test
  public void testExportFile() {
    exporter = new PPM(simpleImage);
    exporter.export("simpleImage.ppm");
    assertEquals(simpleImage.getHeight(), new ImageFromPPM().create("simpleImage.ppm").getHeight());
  }

  @Test (expected = IllegalArgumentException.class)
  public void testConstructorNullImage() {
    exporter = new PPM(nullImage);
  }

  /**
   * Main method for generating images.
   */
  public static void main(String [] args) {
    Image original = new ImageFromPPM().create("res/logo.ppm");
    Image altered = new Greyscale().apply(original);
    ImageExporter exporter = new PPM(altered);
    exporter.export("greyScaleLogo.ppm");
  }
}
