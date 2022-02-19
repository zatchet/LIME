package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import controller.ImageCreator;
import controller.ImageFromJPEGPNG;
import controller.LayeredImageImporter;
import controller.LayeredImageImporterImpl;
import org.junit.Test;

/**
 * Tests for the correct functionality of the LayeredImageEditor class.
 */
public class LayeredImageEditorImplTest {

  @Test(expected = IllegalArgumentException.class)
  public void testNullStartImage() {
    LayeredImageEditor layered = new LayeredImageEditorImpl(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidLayerSharpen() {
    LayeredImageEditor layeredImageEditor = new LayeredImageEditorImpl(3, 3);
    layeredImageEditor.sharpen(2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidLayerBlur() {
    LayeredImageEditor layeredImageEditor = new LayeredImageEditorImpl(3, 3);
    layeredImageEditor.blur(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidLayerSepia() {
    LayeredImageEditor layeredImageEditor = new LayeredImageEditorImpl(3, 3);
    layeredImageEditor.sepia(1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidLayerGreyScale() {
    LayeredImageEditor layeredImageEditor = new LayeredImageEditorImpl(3, 3);
    layeredImageEditor.sepia(1);
  }

  @Test
  public void testGetNumLayers() {
    LayeredImageEditor layeredImageEditor = new LayeredImageEditorImpl(3, 3);
    assertEquals(1, layeredImageEditor.getNumLayers());
    layeredImageEditor.addLayer();
    layeredImageEditor.addLayer();
    assertEquals(3, layeredImageEditor.getNumLayers());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvisibilityInvalidLayer() {
    ImageCreator creator = new ImageFromJPEGPNG();
    Image baseLayer = creator.create("greyCheckerBoard.jpeg");
    LayeredImageEditor layeredImageEditor = new LayeredImageEditorImpl(baseLayer);
    layeredImageEditor.makeInvisible(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testVisibilityInvalidLayer() {
    ImageCreator creator = new ImageFromJPEGPNG();
    Image baseLayer = creator.create("greyCheckerBoard.jpeg");
    LayeredImageEditor layeredImageEditor = new LayeredImageEditorImpl(baseLayer);
    layeredImageEditor.makeVisible(1);
  }

  @Test
  public void testInvisibility() {
    ImageCreator creator = new ImageFromJPEGPNG();
    Image baseLayer = creator.create("greyCheckerBoard.jpeg");
    LayeredImageEditor layeredImageEditor = new LayeredImageEditorImpl(baseLayer);
    layeredImageEditor.makeInvisible(0);
    assertFalse(layeredImageEditor.visible(0));
    layeredImageEditor.addLayer();
    assertTrue(layeredImageEditor.visible(1));
    layeredImageEditor.makeVisible(0);
    assertTrue(layeredImageEditor.visible(0));
  }

  @Test
  public void testGetters() {
    ImageCreator creator = new ImageFromJPEGPNG();
    Image baseLayer = creator.create("greyCheckerBoard.jpeg");
    LayeredImageEditor layered = new LayeredImageEditorImpl(baseLayer);
    assertEquals(3, layered.getHeight());
    assertEquals(3, layered.getWidth());
    Pixel pixel = new Pixel(new ClampedColor(64, 64, 64));
    assertEquals(pixel.toString(), layered.getPixelAt(0, 0).toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetInvalidLayer() {
    ImageCreator creator = new ImageFromJPEGPNG();
    Image baseLayer = creator.create("greyCheckerBoard.jpeg");
    LayeredImageEditor layered = new LayeredImageEditorImpl(baseLayer);
    layered.getLayer(-1);
  }

  @Test(expected = IllegalStateException.class)
  public void testRemoveLastLayer() {
    ImageCreator creator = new ImageFromJPEGPNG();
    Image baseLayer = creator.create("greyCheckerBoard.jpeg");
    LayeredImageEditor layered = new LayeredImageEditorImpl(baseLayer);
    layered.removeLayer(0);
  }

  @Test()
  public void testRemoveLayer() {
    ImageCreator creator = new ImageFromJPEGPNG();
    Image baseLayer = creator.create("greyCheckerBoard.jpeg");
    LayeredImageEditor layered = new LayeredImageEditorImpl(baseLayer);
    layered.addLayer();
    assertEquals(2, layered.getNumLayers());
    layered.removeLayer(0);
    assertEquals(1, layered.getNumLayers());
  }

  @Test()
  public void testSharpenLayer() {
    LayeredImageEditor editor = new LayeredImageEditorImpl(3, 3);
    Image baseLayer = editor.getLayer(0);
    ImageEditor imageEditor = new SimpleImageEditor(baseLayer);
    imageEditor.sharpen();
    Image sharpened = imageEditor.getCurrentImage();
    editor.sharpen(0);
    Image sharpenedLayer = editor.getLayer(0);
    assertEquals(sharpened.getPixelAt(1, 1).toString(),
        sharpenedLayer.getPixelAt(1, 1).toString());
  }

  @Test()
  public void testBlurLayer() {
    LayeredImageEditor editor = new LayeredImageEditorImpl(3, 3);
    Image baseLayer = editor.getLayer(0);
    ImageEditor imageEditor = new SimpleImageEditor(baseLayer);
    imageEditor.blurr();
    Image blurred = imageEditor.getCurrentImage();
    editor.blur(0);
    Image blurredLayer = editor.getLayer(0);
    assertEquals(blurred.getPixelAt(2, 2).toString(),
        blurredLayer.getPixelAt(2, 2).toString());
  }

  @Test()
  public void testSepiaLayer() {
    LayeredImageEditor editor = new LayeredImageEditorImpl(3, 3);
    Image baseLayer = editor.getLayer(0);
    ImageEditor imageEditor = new SimpleImageEditor(baseLayer);
    imageEditor.sepia();
    Image sepia = imageEditor.getCurrentImage();
    editor.sepia(0);
    Image sepiaLayer = editor.getLayer(0);
    assertEquals(sepia.getPixelAt(2, 2).toString(),
        sepiaLayer.getPixelAt(2, 2).toString());
  }

  @Test()
  public void testGreyLayer() {
    LayeredImageEditor editor = new LayeredImageEditorImpl(3, 3);
    Image baseLayer = editor.getLayer(0);
    ImageEditor imageEditor = new SimpleImageEditor(baseLayer);
    imageEditor.greyscale();
    Image grey = imageEditor.getCurrentImage();
    editor.greyscale(0);
    Image greyLayer = editor.getLayer(0);
    assertEquals(grey.getPixelAt(0, 0).toString(),
        greyLayer.getPixelAt(0, 0).toString());
  }

  @Test()
  public void testGreyLayerMultipleLayers() {
    LayeredImageEditor editor = new LayeredImageEditorImpl(3, 3);
    editor.addLayer();
    editor.addLayer();
    Image baseLayer = editor.getLayer(0);
    ImageEditor imageEditor = new SimpleImageEditor(baseLayer);
    imageEditor.greyscale();
    Image grey = imageEditor.getCurrentImage();
    editor.greyscale(0);
    Image greyLayer = editor.getLayer(0);
    assertEquals(grey.getPixelAt(0, 0).toString(),
        greyLayer.getPixelAt(0, 0).toString());
  }

  @Test()
  public void editImportedLayeredImage() {
    LayeredImageImporter importer = new LayeredImageImporterImpl("jpg");
    LayeredImageEditor editor = importer.create("layeredChecker.jpg");
    editor.addLayer();
    editor.sharpen(1);
    assertEquals("(100, 93, 51)", editor.getPixelAt(0, 0).toString());
  }

  @Test
  public void testCreateCheckerboard() {
    LayeredImageEditor editor = new LayeredImageEditorImpl(5, 5);
    assertEquals(editor.getHeight(), 5);
    assertEquals(editor.getWidth(), 5);
  }

}