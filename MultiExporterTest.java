package controller;

import model.ClampedColor;
import model.Image;
import model.ImageImpl;
import model.Pixel;
import org.junit.Test;

/**
 * Tests the functionality of the exportJPEGPNG class.
 */
public class MultiExporterTest {

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

  @Test (expected = IllegalArgumentException.class)
  public void testConstructorNull() {
    ImageExporter exporter = new MultiExporter(null);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testBadFileName() {
    ImageExporter exporter = new MultiExporter(simpleImage);
    exporter.export("badname");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testBadFileExt() {
    ImageExporter exporter = new MultiExporter(null);
    exporter.export("badname.lol");
  }
}