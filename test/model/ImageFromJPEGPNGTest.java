package model;

import static org.junit.Assert.assertEquals;


import controller.MultiExporter;
import controller.ImageCreator;
import controller.ImageExporter;
import controller.ImageFromJPEGPNG;
import org.junit.Test;

/**
 * Tests for the correct functionality of the ImageFromJPEGPNG class.
 */
public class ImageFromJPEGPNGTest {

  @Test(expected = IllegalArgumentException.class)
  public void testNullFileName() {
    new ImageFromJPEGPNG().create(null);
  }


  @Test
  public void testFromJPG() {
    assertEquals(318, new ImageFromJPEGPNG().create("flower.jpg").getWidth());
    assertEquals(159, new ImageFromJPEGPNG().create("flower.jpg").getHeight());
  }

  @Test
  public void testFromPNG() {
    assertEquals(1808, new ImageFromJPEGPNG().create("class.diagram.png").getWidth());
    assertEquals(1134, new ImageFromJPEGPNG().create("class.diagram.png").getHeight());
  }

  @Test
  public void testFromJPEG() {
    ImageEditor editor = new SimpleImageEditor(3, 3);
    editor.greyscale();
    ImageExporter exporter = new MultiExporter(editor.getCurrentImage());
    exporter.export("greyCheckerBoard.jpeg");
    ImageCreator creator = new ImageFromJPEGPNG();
    Image greyChecker = creator.create("greyCheckerBoard.jpeg");
    assertEquals(3, greyChecker.getHeight());
    assertEquals(3, greyChecker.getWidth());
  }

}