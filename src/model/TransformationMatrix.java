package model;

/**
 * Represents the data for the operation to perform for a color transformation on an image. The
 * matrix must have three columns so as to be able to perform matrix multiplication on the three
 * color channels for each pixel in the image.
 */
public class TransformationMatrix extends AbstractOperand {

  /**
   * Construct a TransformationMatrix from the given data.
   * @param data the 2d array
   */
  public TransformationMatrix(double[][] data) {
    super(data);
    if (data[0].length != 3) {
      throw new IllegalArgumentException("The transformation matrix must have three columns");
    }
  }

  /**
   * Constructor that takes in a 2d int array.
   * @param data the 2d int array
   */
  public TransformationMatrix(int[][] data) {
    this(intToDoubleArray(data));
  }

  private static double[][] intToDoubleArray(int[][] data) {
    if (data == null) {
      throw new IllegalArgumentException("Transformation matrix must not be null");
    }
    double[][] doubleMatrix = new double[data.length][data[0].length];
    for (int i = 0; i < data.length; i++) {
      for (int j = 0; j < data[0].length; j++) {
        doubleMatrix[i][j] = (double) data[i][j];
      }
    }
    return doubleMatrix;
  }


}
