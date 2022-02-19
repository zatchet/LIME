package model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests for the correct functionality of the ImageSharpener class.
 */
public class ImageSharpenerTest {

  @Test(expected = IllegalArgumentException.class)
  public void testNullImage() {
    ImageOperation sharpen = new ImageSharpener();
    sharpen.apply(null);
  }

  @Test
  public void testSharpenedImage() {
    Pixel aa = new Pixel(new ClampedColor(255, 0, 0));
    Pixel ab = new Pixel(new ClampedColor(0, 255, 0));
    Pixel ba = new Pixel(new ClampedColor(0, 255, 0));
    Pixel bb = new Pixel(new ClampedColor(255, 0, 0));
    Pixel[][] pixels = new Pixel[][]{{aa, ab}, {ba, bb}};
    Image image = new ImageImpl(pixels);
    ImageOperation sharpen = new ImageSharpener();
    Image sharpened = sharpen.apply(image);

    Pixel[][] expected = new Pixel[2][2];
    expected[0][0] = new Pixel(new ClampedColor(255, 127, 0));
    expected[0][1] = new Pixel(new ClampedColor(127, 255, 0));
    expected[1][0] = new Pixel(new ClampedColor(127, 255, 0));
    expected[1][1] = new Pixel(new ClampedColor(255, 127, 0));

    Image expectedImage = new ImageImpl(expected);

    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        assertEquals(sharpened.getPixelAt(i, j).getColor().getRed(),
            expectedImage.getPixelAt(i, j).getColor().getRed());
        assertEquals(sharpened.getPixelAt(i, j).getColor().getGreen(),
            expectedImage.getPixelAt(i, j).getColor().getGreen());
        assertEquals(sharpened.getPixelAt(i, j).getColor().getBlue(),
            expectedImage.getPixelAt(i, j).getColor().getBlue());
      }
    }
  }

}