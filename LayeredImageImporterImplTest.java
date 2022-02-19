package controller;

import static org.junit.Assert.assertEquals;

import model.LayeredImageEditor;
import org.junit.Test;

/**
 * Tests for the correct functionality of the LayeredImageImporterImpl class.
 */
public class LayeredImageImporterImplTest {

  @Test
  public void testCreateLayeredImagePPM() {
    LayeredImageImporter creator = new LayeredImageImporterImpl("ppm");
    LayeredImageEditor image = creator.create("UltraDifferentName");
    assertEquals(2, image.getNumLayers());
    assertEquals(3, image.getHeight());
    assertEquals(3, image.getWidth());
  }

  @Test
  public void testCreateLayeredImageJPG() {
    /*
    ImageCreator creator = new ImageFromJPEGPNG();
    Image image = creator.create("greyCheckerBoard.jpeg");
    LayeredImageEditor layered = new LayeredImageEditorImpl(image);
    layered.addLayer();
    layered.sepia(1);
    LayeredImageExporter exporter = new LayeredImageExporterImpl(layered);
    exporter.exportAll("layeredChecker.jpg", "jpg");
     */
    LayeredImageImporter importer = new LayeredImageImporterImpl("layeredChecker.jpg");
    LayeredImageEditor layeredImage = importer.create("layeredChecker.jpg");
    assertEquals(2, layeredImage.getNumLayers());
    assertEquals(3, layeredImage.getLayer(0).getWidth());
  }

  @Test
  public void testCreateLayeredImageJPEG() {
    /*
    ImageCreator creator = new ImageFromJPEGPNG();
    Image image = creator.create("greyCheckerBoard.jpeg");
    LayeredImageEditor layered = new LayeredImageEditorImpl(image);
    layered.addLayer();
    layered.sepia(1);
    LayeredImageExporter exporter = new LayeredImageExporterImpl(layered);
    exporter.exportAll("layeredCheckerJPEG.jpeg", "jpeg");
     */
    LayeredImageImporter importer = new LayeredImageImporterImpl("jpeg");
    LayeredImageEditor layeredImage = importer.create("layeredCheckerJPEG.jpeg");
    assertEquals(2, layeredImage.getNumLayers());
    assertEquals(3, layeredImage.getLayer(0).getWidth());
  }

  @Test
  public void testCreateLayeredImagePNG() {
    /*
    ImageCreator creator = new ImageFromJPEGPNG();
    Image image = creator.create("greyCheckerBoard.jpeg");
    LayeredImageEditor layered = new LayeredImageEditorImpl(image);
    layered.addLayer();
    layered.sepia(1);
    LayeredImageExporter exporter = new LayeredImageExporterImpl(layered);
    exporter.exportAll("layeredCheckerPNG.png", "png");
     */
    LayeredImageImporter importer = new LayeredImageImporterImpl("png");
    LayeredImageEditor layeredImage = importer.create("layeredCheckerPNG.png");
    assertEquals(2, layeredImage.getNumLayers());
    assertEquals(3, layeredImage.getLayer(0).getWidth());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullFileType() {
    LayeredImageImporter importer = new LayeredImageImporterImpl(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullFileName() {
    LayeredImageImporter importer = new LayeredImageImporterImpl("ppm");
    importer.create(null);
  }


}