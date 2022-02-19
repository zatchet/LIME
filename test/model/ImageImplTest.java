package model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test verifying the correct functionality of the ImageImpl class.
 */
public class ImageImplTest {

  @Test(expected = IllegalArgumentException.class)
  public void testNullPixels() {
    Image image = new ImageImpl(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSomeNullPixels() {
    Pixel[][] pixels = new Pixel[3][3];
    pixels[0][0] = new Pixel(new ClampedColor(0, 0, 0));
    pixels[1][1] = new Pixel(new ClampedColor(0, 255, 0));
    Image image = new ImageImpl(pixels);
  }

  @Test
  public void testGetWidth() {
    Pixel[][] pixels = new Pixel[2][3];
    pixels[0][0] = new Pixel(new ClampedColor(0, 0, 0));
    pixels[0][1] = new Pixel(new ClampedColor(0, 0, 0));
    pixels[0][2] = new Pixel(new ClampedColor(0, 0, 0));
    pixels[1][0] = new Pixel(new ClampedColor(0, 0, 0));
    pixels[1][1] = new Pixel(new ClampedColor(0, 0, 0));
    pixels[1][2] = new Pixel(new ClampedColor(0, 0, 0));
    Image image = new ImageImpl(pixels);
    assertEquals(3, image.getWidth());
  }

  @Test
  public void testGetHeight() {
    Pixel[][] pixels = new Pixel[2][3];
    pixels[0][0] = new Pixel(new ClampedColor(0, 0, 0));
    pixels[0][1] = new Pixel(new ClampedColor(0, 0, 0));
    pixels[0][2] = new Pixel(new ClampedColor(0, 0, 0));
    pixels[1][0] = new Pixel(new ClampedColor(0, 0, 0));
    pixels[1][1] = new Pixel(new ClampedColor(0, 0, 0));
    pixels[1][2] = new Pixel(new ClampedColor(0, 0, 0));
    Image image = new ImageImpl(pixels);
    assertEquals(2, image.getHeight());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPixelNegRow() {
    Pixel[][] pixels = new Pixel[2][3];
    pixels[0][0] = new Pixel(new ClampedColor(0, 0, 0));
    pixels[0][1] = new Pixel(new ClampedColor(0, 0, 0));
    pixels[0][2] = new Pixel(new ClampedColor(0, 0, 0));
    pixels[1][0] = new Pixel(new ClampedColor(0, 0, 0));
    pixels[1][1] = new Pixel(new ClampedColor(0, 0, 0));
    pixels[1][2] = new Pixel(new ClampedColor(0, 0, 0));
    Image image = new ImageImpl(pixels);
    image.getPixelAt(-1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPixelNegCol() {
    Pixel[][] pixels = new Pixel[2][3];
    pixels[0][0] = new Pixel(new ClampedColor(0, 0, 0));
    pixels[0][1] = new Pixel(new ClampedColor(0, 0, 0));
    pixels[0][2] = new Pixel(new ClampedColor(0, 0, 0));
    pixels[1][0] = new Pixel(new ClampedColor(0, 0, 0));
    pixels[1][1] = new Pixel(new ClampedColor(0, 0, 0));
    pixels[1][2] = new Pixel(new ClampedColor(0, 0, 0));
    Image image = new ImageImpl(pixels);
    image.getPixelAt(1, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPixelTooBigRow() {
    Pixel[][] pixels = new Pixel[2][3];
    pixels[0][0] = new Pixel(new ClampedColor(0, 0, 0));
    pixels[0][1] = new Pixel(new ClampedColor(0, 0, 0));
    pixels[0][2] = new Pixel(new ClampedColor(0, 0, 0));
    pixels[1][0] = new Pixel(new ClampedColor(0, 0, 0));
    pixels[1][1] = new Pixel(new ClampedColor(0, 0, 0));
    pixels[1][2] = new Pixel(new ClampedColor(0, 0, 0));
    Image image = new ImageImpl(pixels);
    image.getPixelAt(2, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPixelTooBigCol() {
    Pixel[][] pixels = new Pixel[2][3];
    pixels[0][0] = new Pixel(new ClampedColor(0, 0, 0));
    pixels[0][1] = new Pixel(new ClampedColor(0, 0, 0));
    pixels[0][2] = new Pixel(new ClampedColor(0, 0, 0));
    pixels[1][0] = new Pixel(new ClampedColor(0, 0, 0));
    pixels[1][1] = new Pixel(new ClampedColor(0, 0, 0));
    pixels[1][2] = new Pixel(new ClampedColor(0, 0, 0));
    Image image = new ImageImpl(pixels);
    image.getPixelAt(1, 3);
  }
}
