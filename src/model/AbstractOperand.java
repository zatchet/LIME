package model;

/**
 * Represents a matrix operand that is used to perform operations on pixel data to process an
 * image.
 */
public class AbstractOperand implements ArrayOperand {

  protected final double[][] data;

  /**
   * Constructs an AbstractOperand given the matrix.
   *
   * @param data the matrix representing the operand
   * @throws IllegalArgumentException if the data or part of the data is null
   */
  public AbstractOperand(double[][] data) {
    if (data == null) {
      throw new IllegalArgumentException("Matrix must not be null");
    }

    for (double[] arr : data) {
      if (arr == null) {
        throw new IllegalArgumentException("data cannot be null");
      }
    }
    this.data = data;
  }

  @Override
  public double[][] getData() {
    double[][] newData = new double[data.length][data[0].length];
    for (int i = 0; i < data.length; i++) {
      newData[i] = data[i].clone();
    }
    return newData;
  }
}
