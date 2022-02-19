package model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests for the correct functionality of the TransformationMatrix class.
 */
public class TransformationMatrixTest {

  private double[][] data;

  @Test(expected = IllegalArgumentException.class)
  public void testNullData() {
    ArrayOperand t = new TransformationMatrix(data);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNotThreeColumns() {
    double[][] data = new double[][]{{1, 2}, {3, 4}};
    ArrayOperand t = new TransformationMatrix(data);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPartialNullData() {
    double[][] data = new double[3][3];
    data[0][0] = 2.0;
    data[1] = null;
    ArrayOperand t = new TransformationMatrix(data);
  }

  @Test
  public void testValidMatrix() {
    double[][] data = new double[][]{{2.5, -1.3, 10.1}};
    ArrayOperand tm = new TransformationMatrix(data);
    assertEquals(2.5, tm.getData()[0][0], 0.0001);
  }

}