package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


import org.junit.Before;
import org.junit.Test;

/**
 * Tests the functionality of the Sepia class.
 */
public class SepiaTest {

  private AbstractColorTransformation sepia;

  @Before
  public void setUp() {
    sepia = new Sepia();
  }

  @Test
  public void testType() {
    assertTrue(sepia instanceof AbstractColorTransformation);
    assertEquals(Sepia.class, sepia.getClass());
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

    ImageOperation sepia = new Sepia();
    Image whatever = sepia.apply(checkerBoard);
    Pixel[][] expected = new Pixel[whatever.getHeight()][whatever.getWidth()];

    expected[0][0] = new Pixel(new ClampedColor(48, 42, 33));
    expected[0][1] = new Pixel(new ClampedColor(196, 174, 136));
    expected[0][2] = new Pixel(new ClampedColor(48, 42, 33));

    expected[1][0] = new Pixel(new ClampedColor(196, 174, 136));
    expected[1][1] = new Pixel(new ClampedColor(48, 42, 33));
    expected[1][2] = new Pixel(new ClampedColor(196, 174, 136));

    expected[2][0] = new Pixel(new ClampedColor(48, 42, 33));
    expected[2][1] = new Pixel(new ClampedColor(196, 174, 136));
    expected[2][2] = new Pixel(new ClampedColor(48, 42, 33));

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