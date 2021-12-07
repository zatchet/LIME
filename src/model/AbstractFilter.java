package model;

/**
 * Represents a filtering operation to be applied to an Image. Each filter has a kernel which is
 * used to calculate the filtered value of each pixel in the image. The result of the filter is
 * calculated by multiplying together corresponding numbers in the kernel and the pixels and adding
 * them.
 */
public class AbstractFilter implements ImageOperation {

  private final Kernel kernel;

  /**
   * Creates an AbstractFilter object. Kernel cannot be null.
   * @param kernel the kernel to be used for the filtering.
   */
  public AbstractFilter(Kernel kernel) {
    if (kernel == null) {
      throw new IllegalArgumentException("The kernel for the filter cannot be null");
    }
    this.kernel = kernel;
  }

  @Override
  public Image apply(Image original) {
    if (original == null) {
      throw new IllegalArgumentException("Cannot apply filter to null image");
    }

    Pixel[][] newPixels = new Pixel[original.getHeight()][original.getWidth()];

    int num = kernel.getData().length / 2;

    for (int i = 0; i < original.getHeight(); i++) {
      for (int j = 0; j < original.getWidth(); j++) {

        //looking at one pixel

        double redCount = 0;
        double blueCount = 0;
        double greenCount = 0;


        for (int w = 0; w < kernel.getData().length; w ++) {
          for (int y = 0; y < kernel.getData()[0].length; y ++) {

            int currRow = i + w - num;
            int currCol = j + y - num;

            try {
              Color color = original.getPixelAt(currRow, currCol).getColor();
              redCount += color.getRed() * kernel.getData()[w][y];
              greenCount += color.getGreen() * kernel.getData()[w][y];
              blueCount += color.getBlue() * kernel.getData()[w][y];
            }
            catch (IllegalArgumentException e) {
              continue;
            }
            int k;
          }
        }

        newPixels[i][j] = new Pixel(new ClampedColor(redCount, greenCount, blueCount));
      }
    }
    return new ImageImpl(newPixels);
  }

}
