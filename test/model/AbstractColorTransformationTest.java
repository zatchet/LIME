package model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the functionality of the AbstractColorTransformation class.
 */
public class AbstractColorTransformationTest {

  private Image checkerBoard;
  private TransformationMatrix nullMatrix;
  private Image nullImage;
  private AbstractColorTransformation act;
  private TransformationMatrix goodMatrix;

  @Before
  public void setUp() {
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
    checkerBoard = new ImageImpl(checkerPixels);

    goodMatrix = new TransformationMatrix(new double[][]{
        {0.1, 0.2, 0.3},
        {0.4, 0.5, 0.6}});
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullMatrix() {
    AbstractColorTransformation transfo = new AbstractColorTransformation(nullMatrix);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testApplyNullImage() {
    act = new AbstractColorTransformation(goodMatrix);
    Image whatever = act.apply(nullImage);
  }

  @Test
  public void testApplyValid() {
    act = new AbstractColorTransformation(goodMatrix);
    Image whatever = act.apply(checkerBoard);
    Pixel[][] expected = new Pixel[whatever.getHeight()][whatever.getWidth()];

    expected[0][0] = new Pixel(new ClampedColor(76,153,0));
    expected[0][1] = new Pixel(new ClampedColor(51,127,0));
    expected[0][2] = new Pixel(new ClampedColor(76,153,0));

    expected[1][0] = new Pixel(new ClampedColor(51,127,0));
    expected[1][1] = new Pixel(new ClampedColor(76,153,0));
    expected[1][2] = new Pixel(new ClampedColor(51,127,0));

    expected[2][0] = new Pixel(new ClampedColor(76,153,0));
    expected[2][1] = new Pixel(new ClampedColor(51,127,0));
    expected[2][2] = new Pixel(new ClampedColor(76,153,0));

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


