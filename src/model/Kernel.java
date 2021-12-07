package model;

/**
 * Represents a kernel to be used for image filtering. A Kernel must be a square, and must be of
 * odd dimensions. These constraints are enforced in the constructor and preserved by the lone
 * observer method.
 */

public class Kernel extends AbstractOperand {

  /**
   * Creates an instance of a kernel object.
   * @param data the 2d array of doubles to be used as the kernel.
   * @throws IllegalArgumentException if the data is not a square or is not of odd dimensions.
   */
  public Kernel(double[][] data) {
    super(data);
    if (data.length % 2 != 1 || data[0].length % 2 != 1) {
      throw new IllegalArgumentException("Dimensions must be odd");
    }

    if (data.length != data[0].length) {
      throw new IllegalArgumentException("data must be a square");
    }
  }
}
