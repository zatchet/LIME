package model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests for the correct functionality of the AbstractFilter class.
 */
public class AbstractFilterTest {

  @Test(expected = IllegalArgumentException.class)
  public void testNullKernel() {
    ImageOperation filter = new AbstractFilter(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullImage() {
    double[][] k = new double[][]{{2, 1, 2}, {-1, 0, 2}, {1, 1, 1}};
    Kernel kernel = new Kernel(k);
    ImageOperation op = new AbstractFilter(kernel);
    op.apply(null);
  }

  @Test
  public void testFilter1() {
    Pixel[][] testImagePixels = new Pixel[4][4];

    //creating checkerboard
    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {

        if ((i + j) % 2 == 0) {
          testImagePixels[i][j] = new Pixel(new ClampedColor(0, 0, 255));
        } else {
          testImagePixels[i][j] = new Pixel(new ClampedColor(0, 0, 255));
        }
      }
    }

    double[][] testKernel = new double[3][3];
    testKernel[0][0] = 0.1;
    testKernel[0][1] = 0.1;
    testKernel[0][2] = 0.1;
    testKernel[1][0] = 0.1;
    testKernel[1][1] = 0.1;
    testKernel[1][2] = 0.1;
    testKernel[2][0] = 0.1;
    testKernel[2][1] = 0.1;
    testKernel[2][2] = 0.1;

    Image testImage = new ImageImpl(testImagePixels);
    assertEquals("(0, 0, 255) (0, 0, 255) (0, 0, 255) (0, 0, 255) \n"
        + "(0, 0, 255) (0, 0, 255) (0, 0, 255) (0, 0, 255) \n"
        + "(0, 0, 255) (0, 0, 255) (0, 0, 255) (0, 0, 255) \n"
        + "(0, 0, 255) (0, 0, 255) (0, 0, 255) (0, 0, 255) \n", imageToString(testImage));
    assertEquals("(0, 0, 102) (0, 0, 153) (0, 0, 153) (0, 0, 102) \n"
            + "(0, 0, 153) (0, 0, 229) (0, 0, 229) (0, 0, 153) \n"
            + "(0, 0, 153) (0, 0, 229) (0, 0, 229) (0, 0, 153) \n"
            + "(0, 0, 102) (0, 0, 153) (0, 0, 153) (0, 0, 102) \n",
        imageToString(new AbstractFilter(new Kernel(testKernel)).apply(testImage)));
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
