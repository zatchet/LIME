package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

/**
 * Tests the functionality of the SimpleImageEditor.
 */
public class SimpleImageEditorTest {

  private Image nullImage;
  private ImageEditor imageEditor;
  private static final Kernel goodKernel =
      new Kernel(new double[][]{{1, 2, 3}, {2, 3, 4}, {4, 5, 6}});
  private static final TransformationMatrix goodTransfo =
      new TransformationMatrix(new double[][]{{1, 2, 3}, {2, 3, 4}});


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


  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullImage() {
    imageEditor = new SimpleImageEditor(nullImage);
  }

  @Test
  public void testConstructorValid() {
    imageEditor = new SimpleImageEditor(simpleImage);
    assertEquals(1, imageEditor.getVersionNumber());
    assertEquals(simpleImage, imageEditor.getCurrentImage());
  }

  @Test
  public void sharpen() {
    imageEditor = new SimpleImageEditor(simpleImage);
    imageEditor.sharpen();
    assertEquals(2, imageEditor.getVersionNumber());
    assertEquals(simpleImage.getHeight(), imageEditor.getCurrentImage().getHeight());
    imageEditor.revert();
    assertEquals(1, imageEditor.getVersionNumber());
    assertEquals(simpleImage, imageEditor.getCurrentImage());
  }

  @Test
  public void blurr() {
    imageEditor = new SimpleImageEditor(simpleImage);
    imageEditor.blurr();
    assertEquals(2, imageEditor.getVersionNumber());
    assertEquals(simpleImage.getHeight(), imageEditor.getCurrentImage().getHeight());
    imageEditor.revert();
    assertEquals(1, imageEditor.getVersionNumber());
    assertEquals(simpleImage, imageEditor.getCurrentImage());
  }

  @Test
  public void greyscale() {
    imageEditor = new SimpleImageEditor(simpleImage);
    imageEditor.greyscale();
    assertEquals(2, imageEditor.getVersionNumber());
    assertEquals(simpleImage.getHeight(), imageEditor.getCurrentImage().getHeight());
    imageEditor.revert();
    assertEquals(1, imageEditor.getVersionNumber());
    assertEquals(simpleImage, imageEditor.getCurrentImage());
  }

  @Test
  public void sepia() {
    imageEditor = new SimpleImageEditor(simpleImage);
    imageEditor.sepia();
    assertEquals(2, imageEditor.getVersionNumber());
    assertEquals(simpleImage.getHeight(), imageEditor.getCurrentImage().getHeight());
    imageEditor.revert();
    assertEquals(1, imageEditor.getVersionNumber());
    assertEquals(simpleImage, imageEditor.getCurrentImage());
  }

  @Test
  public void customFilter() {
    imageEditor = new SimpleImageEditor(simpleImage);
    imageEditor.customFilter(goodKernel);
    assertEquals(2, imageEditor.getVersionNumber());
    assertEquals(simpleImage.getHeight(), imageEditor.getCurrentImage().getHeight());
    imageEditor.revert();
    assertEquals(1, imageEditor.getVersionNumber());
    assertEquals(simpleImage, imageEditor.getCurrentImage());
  }

  @Test(expected = IllegalArgumentException.class)
  public void customFilterBadKernel() {
    imageEditor = new SimpleImageEditor(simpleImage);
    imageEditor.customFilter(new Kernel(new double[][]{{1, 2}, {3, 4}}));
    assertEquals(2, imageEditor.getVersionNumber());
    assertEquals(simpleImage.getHeight(), imageEditor.getCurrentImage().getHeight());
    imageEditor.revert();
    assertEquals(1, imageEditor.getVersionNumber());
    assertEquals(simpleImage, imageEditor.getCurrentImage());
  }

  @Test
  public void customColorTransform() {
    imageEditor = new SimpleImageEditor(simpleImage);
    imageEditor.customColorTransform(goodTransfo);
    assertEquals(2, imageEditor.getVersionNumber());
    assertEquals(simpleImage.getHeight(), imageEditor.getCurrentImage().getHeight());
    imageEditor.revert();
    assertEquals(1, imageEditor.getVersionNumber());
    assertEquals(simpleImage, imageEditor.getCurrentImage());
  }

  @Test(expected = IllegalArgumentException.class)
  public void customColorTransformBadTransfo() {
    imageEditor = new SimpleImageEditor(simpleImage);
    imageEditor.customColorTransform(new TransformationMatrix(new double[][]{{1, 2}, {2, 3}}));
    assertEquals(2, imageEditor.getVersionNumber());
    assertEquals(simpleImage.getHeight(), imageEditor.getCurrentImage().getHeight());
    imageEditor.revert();
    assertEquals(1, imageEditor.getVersionNumber());
    assertEquals(simpleImage, imageEditor.getCurrentImage());
  }

  @Test
  public void revert() {
    imageEditor = new SimpleImageEditor(simpleImage);
    imageEditor.blurr();
    assertEquals(2, imageEditor.getVersionNumber());
    imageEditor.revert();
    assertEquals(1, imageEditor.getVersionNumber());
  }

  @Test(expected = IllegalStateException.class)
  public void revertOnOrginal() {
    imageEditor = new SimpleImageEditor(simpleImage);
    imageEditor.revert();
  }

  @Test
  public void getCurrentImage() {
    imageEditor = new SimpleImageEditor(simpleImage);
    assertEquals(simpleImage, imageEditor.getCurrentImage());
    imageEditor.sharpen();
    assertNotEquals(simpleImage, imageEditor.getCurrentImage());
  }

  @Test
  public void getVersionNumber() {
    imageEditor = new SimpleImageEditor(simpleImage);
    assertEquals(1, imageEditor.getVersionNumber());
    imageEditor.sharpen();
    assertEquals(2, imageEditor.getVersionNumber());
    imageEditor.sharpen();
    assertEquals(3, imageEditor.getVersionNumber());
    imageEditor.sharpen();
    assertEquals(4, imageEditor.getVersionNumber());
    imageEditor.sharpen();
    assertEquals(5, imageEditor.getVersionNumber());
  }
}