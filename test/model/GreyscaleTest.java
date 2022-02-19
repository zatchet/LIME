package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import org.junit.Before;
import org.junit.Test;

/**
 * Tests the functionality of the Greyscale class.
 */
public class GreyscaleTest {

  private AbstractColorTransformation greyscale;

  @Before
  public void setUp() {
    greyscale = new Greyscale();
  }

  @Test
  public void testType() {
    assertTrue(greyscale instanceof AbstractColorTransformation);
    assertEquals(Greyscale.class, greyscale.getClass());
  }

  @Test
  public void testSepiaTransformation() {

    Pixel[][] checkerPixels = new Pixel[3][3];

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if ((i + j) % 2 == 0) {
          checkerPixels[i][j] = new Pixel(new ClampedColor(0, 0, 255));
        } else {
          checkerPixels[i][j] = new Pixel(new ClampedColor(0, 255, 0));
        }
      }
    }

    Image checkerBoard = new ImageImpl(checkerPixels);

    ImageOperation greyscale = new Greyscale();
    Image whatever = greyscale.apply(checkerBoard);
    Pixel[][] expected = new Pixel[whatever.getHeight()][whatever.getWidth()];

    expected[0][0] = new Pixel(new ClampedColor(18, 18, 18));
    expected[0][1] = new Pixel(new ClampedColor(182, 182, 182));
    expected[0][2] = new Pixel(new ClampedColor(18, 18, 18));

    expected[1][0] = new Pixel(new ClampedColor(182, 182, 182));
    expected[1][1] = new Pixel(new ClampedColor(18, 18, 18));
    expected[1][2] = new Pixel(new ClampedColor(182, 182, 182));

    expected[2][0] = new Pixel(new ClampedColor(18, 18, 18));
    expected[2][1] = new Pixel(new ClampedColor(182, 182, 182));
    expected[2][2] = new Pixel(new ClampedColor(18, 18, 18));

    for (int i = 0; i < expected.length; i++) {
      for (int j = 0; j < expected[0].length; j++) {
        assertEquals(expected[i][j].getColor().getRed(),
            whatever.getPixelAt(i, j).getColor().getRed());

        assertEquals(expected[i][j].getColor().getGreen(),
            whatever.getPixelAt(i, j).getColor().getGreen());

        assertEquals(expected[i][j].getColor().getBlue(),
            whatever.getPixelAt(i, j).getColor().getBlue());
      }
    }

  }

}

