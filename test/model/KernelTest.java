package model;

import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

/**
 * Tests the functionality of the Kernel class.
 */
public class KernelTest {

  private ArrayOperand kernel;
  private double[][] nullData;
  private double[] nullInnerData;

  @Test (expected = IllegalArgumentException.class)
  public void testConstructorNullData() {
    kernel = new Kernel(nullData);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testConstructorNullInnerData() {
    kernel = new Kernel(new double[][]{nullInnerData, nullInnerData});
  }

  @Test (expected = IllegalArgumentException.class)
  public void testConstructorEvenDimensions() {
    double[][] even = new double[][]{{1,2}, {4,5}};
    kernel = new Kernel(even);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testConstructorNotSquare() {
    double[][] notSquare = new double[][]{{1,2,3}, {4,5,6}, {7,8,9}, {14,55,6}, {4,65,16}};
    kernel = new Kernel(notSquare);
  }

  @Test
  public void testGetDataMakesCopy() {
    double[][] data = new double[][]{{1,2,3}, {1,2,3}, {1,2,3}};
    kernel = new Kernel(data);
    assertNotEquals(data, kernel.getData());
  }

}