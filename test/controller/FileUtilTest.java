package controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

/**
 * Tests the functionality of the File Util class.
 */
public class FileUtilTest {

  @Test
  public void testGetSupportedFileTypes() {
    List<String> expected = new ArrayList<>(Arrays.asList(
        "jpg", "tif", "tiff", "bmp", "gif", "png", "jpeg", "wbmp", "ppm"));
    assertTrue(expected.containsAll(FileUtil.getSupportedFileTypes()));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCheckNotMatches() {
    FileUtil.checkNotMatches("jpg", "Koala.ppm");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCheckNotSupported() {
    FileUtil.checkNotSupported("blah");
  }

  @Test
  public void testGetFileExtension() {
    assertEquals("png", FileUtil.getFileExtension("test.png"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetFileExtensionBadName() {
    System.out.println(FileUtil.getFileExtension("blah"));
  }
}