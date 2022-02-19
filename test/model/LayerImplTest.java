package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Tests the functionality of the LayerImpl class.
 */
public class LayerImplTest {

  private Layer layer;

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
  public void testConstructor1() {
    layer = new LayerImpl(simpleImage);
    assertTrue(layer.visible());
  }

  @Test
  public void testConstructor2() {
    layer = new LayerImpl(simpleImage, false);
    assertFalse(layer.visible());
  }

  @Test
  public void testConstructor3() {
    layer = new LayerImpl(3, 3);
    assertEquals(3, layer.getHeight());
    assertEquals(3, layer.getWidth());
    assertTrue(layer.visible());
  }

  @Test
  public void testSetVisible() {
    layer = new LayerImpl(simpleImage);
    assertTrue(layer.visible());
    layer.setVisible(false);
    assertFalse(layer.visible());
    layer.setVisible(true);
    assertTrue(layer.visible());
  }

}