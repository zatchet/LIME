package controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;

/**
 * Represents common utility operations for working with file names. All methods are public and
 * static.
 */
public class FileUtil {

  /**
   * Returns the file extensions currently supported by our program.
   *
   * @return a list of string file extensions, e.g. "jpg" or "ppm"
   */
  public static List<String> getSupportedFileTypes() {
    List<String> supportedTypes = new ArrayList<>(Arrays.asList(
        ImageIO.getWriterFileSuffixes()));
    supportedTypes.add("ppm");
    return supportedTypes;
  }

  /**
   * Ensures that the extension matches the extensions of the give file name.
   *
   * @param extension the extensions, e.g. "jpg"
   * @param fileName  the file name, e.g. "clifford.jpg"
   * @throws IllegalArgumentException if they do not match or the file name is malformed.
   */
  public static void checkNotMatches(String extension, String fileName) {
    try {
      if (!extension.equals(fileName.substring(fileName.lastIndexOf('.') + 1))) {
        throw new IllegalArgumentException("extension does not match file type");
      }
    } catch (StringIndexOutOfBoundsException e) {
      throw new IllegalArgumentException("bad file name");
    }
  }

  /**
   * Ensures that the given file type is currently supported by our program.
   *
   * @param fileType the file type.
   */
  public static void checkNotSupported(String fileType) {
    if (!getSupportedFileTypes().contains(fileType)) {
      throw new IllegalArgumentException("unsupported file type");
    }
  }

  /**
   * Gets the file extensions based off a given file name.
   *
   * @param fileName the name of the file.
   * @return the extension, e.g. "jpg"
   * @throws IllegalArgumentException if the name is bad.
   */
  public static String getFileExtension(String fileName) {
    if (!fileName.contains(".")) {
      throw new IllegalArgumentException("bad file name");
    }
    return fileName.substring(fileName.lastIndexOf('.') + 1);
  }

  /**
   * Gets the filename from the given file path.
   *
   * @param fileName the path
   * @return the name of the file
   */
  public static String getNameFromPath(String fileName) {
    String c = File.separator;
    if (!fileName.contains(File.separator)) {
      return fileName;
    }
    return fileName.substring(fileName.lastIndexOf(File.separator) + 1);
  }
}
