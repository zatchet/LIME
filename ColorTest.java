package model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the functionality of the Color class.
 */
public class ColorTest {

  private Color c;

  @Before
  public void init() {
    c = new ClampedColor(1,2,3);
  }

  @Test
  public void testGetRed() {
    assertEquals(1, c.getRed());
  }

  @Test
  public void testGetGreen() {
    assertEquals(2, c.getGreen());
  }

  @Test
  public void testGetBlue() {
    assertEquals(3, c.getBlue());
  }

  @Test
  public void testConstructorClamps() {
    c = new ClampedColor(-100, 50, 300);
    assertEquals(0, c.getRed());
    assertEquals(50, c.getGreen());
    assertEquals(255, c.getBlue());
  }

  @Test
  public void testConstructorDoubles() {
    c = new ClampedColor(3.7, 100.3, 4.0);
    assertEquals(3, c.getRed());
    assertEquals(100, c.getGreen());
    assertEquals(4, c.getBlue());
  }

  @Test
  public void testToString() {
    assertEquals("(1, 2, 3)", c.toString());
  }
}