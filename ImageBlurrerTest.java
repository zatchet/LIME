package model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests for the correct functionality of the ImageBlurrer class.
 */
public class ImageBlurrerTest {

  @Test(expected = IllegalArgumentException.class)
  public void testNullImage() {
    ImageOperation blur = new ImageBlurrer();
    blur.apply(null);
  }

  @Test
  public void testBlurredImage() {
    Pixel aa = new Pixel(new ClampedColor(255, 0, 0));
    Pixel ab = new Pixel(new ClampedColor(0, 255, 0));

    Pixel ba = new Pixel(new ClampedColor(0, 255, 0));
    Pixel bb = new Pixel(new ClampedColor(255, 0, 0));

    Pixel[][] pixels = new Pixel[][]{{aa, ab}, {ba, bb}};
    Image image = new ImageImpl(pixels);
    ImageOperation blur = new ImageBlurrer();
    Image blurred = blur.apply(image);

    Pixel[][] expected = new Pixel[2][2];

    expected[0][0] = new Pixel(new ClampedColor(79, 63, 0));
    expected[0][1] = new Pixel(new ClampedColor(63, 79, 0));
    expected[1][0] = new Pixel(new ClampedColor(63, 79, 0));
    expected[1][1] = new Pixel(new ClampedColor(79, 63, 0));


    Image expectedImage = new ImageImpl(expected);

    for (int i = 0; i < expected.length; i ++) {
      for (int j = 0; j < expected[0].length; j ++) {
        assertEquals(expectedImage.getPixelAt(i,j).getColor().getRed(),
            blurred.getPixelAt(i, j).getColor().getRed());
        assertEquals(expectedImage.getPixelAt(i,j).getColor().getGreen(),
            blurred.getPixelAt(i, j).getColor().getGreen());
        assertEquals(expectedImage.getPixelAt(i,j).getColor().getBlue(),
            blurred.getPixelAt(i, j).getColor().getBlue());

      }
    }

  }

  private static String imageToString(Image img) {
    StringBuilder s = new StringBuilder();
    for (int i = 0; i < img.getHeight(); i++) {
      for (int j = 0; j < img.getWidth(); j++) {
        s.append(img.getPixelAt(i, j).toString());
        s.append(" ");
      }
      s.append("\n");
    }
    return s.toString();
  }
}