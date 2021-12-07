package model;

/**
 * Represents a color transformation operation such as a greyscale or sepia. Contains a single
 * method apply(Image image) which applies the transformation to the given image.
 */
public class AbstractColorTransformation implements ImageOperation {

  private final TransformationMatrix transformation;

  /**
   * Creates an Abstract Color Transformation object.
   * @param t the transformation matrix to be used for the operation.
   */
  public AbstractColorTransformation(TransformationMatrix t) {
    if (t == null) {
      throw new IllegalArgumentException("Transformation cannot be null");
    }
    this.transformation = t;
  }

  @Override
  public Image apply(Image image) throws IllegalArgumentException {

    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }

    Pixel[][] transformed = new Pixel[image.getHeight()][image.getWidth()];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        Pixel p = image.getPixelAt(i, j);
        Color c = p.getColor();
        int[] channels = new int[]{c.getRed(), c.getGreen(), c.getBlue()};
        double[] newChannels = matrixMultiply(this.transformation, channels);
        Pixel newPixel = new Pixel(new ClampedColor(newChannels[0],
            newChannels[1], newChannels[2]));
        transformed[i][j] = newPixel;
      }
    }
    return new ImageImpl(transformed);
  }

  /**
   * Performs matrix multiplication on the given arrays.
   *
   * @param transformation the transformation matrix to multiply the other matrix by
   * @param channels       the array of rgb channels to multiply the transformation matrix by
   * @return the new rgb channels that are the result of the multiplication
   */
  private static double[] matrixMultiply(TransformationMatrix transformation, int[] channels) {
    if (transformation.getData()[0].length != channels.length) {
      throw new IllegalArgumentException(
          "The number of rows of the first matrix must equal the number of columns of the second.");
    }
    double[] result = new double[channels.length];
    for (int i = 0; i < transformation.getData().length; i++) {
      double counter = 0;
      for (int j = 0; j < transformation.getData()[0].length; j++) {
        counter += transformation.getData()[i][j] * channels[j];
      }
      result[i] = counter;
    }
    return result;
  }
}
