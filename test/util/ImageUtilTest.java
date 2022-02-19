package util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import model.ClampedColor;
import model.Pixel;
import org.junit.Test;

/**
 * Tests for the correct functionality of the ImageUtil class.
 */
public class ImageUtilTest {

  @Test(expected = IllegalArgumentException.class)
  public void testFileDoesNotExist() {
    ImageUtil.readPPM("");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testFileNotPPM() {
    ImageUtil.readPPM("Test/p6.ppm");
  }

  @Test
  public void testPPMFileExists() {
    Pixel[][] data = ImageUtil.readPPM("Koala.ppm");
    assertEquals(1024, data[0].length);
    assertEquals(768, data.length);
  }

  @Test
  public void testDeepCopy() {
    Pixel[][] og = new Pixel[][]{{new Pixel(new ClampedColor(0, 0, 0))}};
    Pixel[][] shallowCopy = og;
    Pixel[][] deepCopy = ImageUtil.pixelDeepCopy(og);
    assertTrue(og.equals(shallowCopy));
    assertFalse(og.equals(deepCopy));
  }

}