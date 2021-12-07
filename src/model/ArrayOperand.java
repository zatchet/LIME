package model;

/**
 * Represents a 2D array of data, or matrix, that is the operand of an operation that can be applied
 * to an image's pixels. Some operations require arrays with specific dimensions.
 */
public interface ArrayOperand {

  /**
   * Gets the operand in matrix form.
   *
   * @return the 2D array of values
   */
  double[][] getData();
}
