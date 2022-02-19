package controller;

import model.ClampedColor;
import model.Image;
import model.ImageImpl;
import model.LayeredImageEditor;
import model.LayeredImageEditorImpl;
import model.Pixel;
import org.junit.Test;

/**
 * Tests the functionality of the LayeredImageExporter class.
 */
public class LayeredImageExporterImplTest {

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
    LayeredImageExporter exporter = new LayeredImageExporterImpl(null);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testExportAllBadFileExt() {
    LayeredImageExporter exporter = new LayeredImageExporterImpl(
        new LayeredImageEditorImpl(simpleImage));
    exporter.exportAll("badName", "ff");
  }

  @Test (expected = IllegalArgumentException.class)
  public void testExportTopBadFileName() {
    LayeredImageExporter exporter = new LayeredImageExporterImpl(
        new LayeredImageEditorImpl(simpleImage));
    exporter.exportTop("different", "jpg");
  }

  @Test (expected = IllegalStateException.class)
  public void testExportTopNoneVisible() {
    LayeredImageEditor editor = new LayeredImageEditorImpl(simpleImage);
    editor.makeInvisible(0);
    LayeredImageExporter exporter = new LayeredImageExporterImpl(editor);
    exporter.exportTop("different.jpg", "jpg");
  }

}