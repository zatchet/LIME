package util;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import model.ClampedColor;
import model.Color;
import model.Pixel;

/**
 * This class contains utility methods for image processing operations including parsing an image
 * from a PPM file and clamping channel values.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and generate pixel array for the image.
   *
   * @param filename the path of the file.
   * @throws IllegalArgumentException if the file is not found or the file is not P3
   */
  public static Pixel[][] readPPM(String filename) {
    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File " + filename + " not found!");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }
    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());
    String token;
    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();
    Pixel[][] pixels = new Pixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        pixels[i][j] = new Pixel(new ClampedColor(r, g, b));
      }
    }
    return pixels;
  }

  /**
   * Creates a deep copy of the given 2d Pixel array.
   *
   * @param original the given array.
   * @return the new array.
   */
  public static Pixel[][] pixelDeepCopy(Pixel[][] original) {
    Pixel[][] newPixels = new Pixel[original.length][original[0].length];
    for (int i = 0; i < original.length; i++) {
      for (int j = 0; j < original[0].length; j++) {
        Color orig = original[i][j].getColor();
        newPixels[i][j] = new Pixel(new ClampedColor(
            orig.getRed(), orig.getGreen(), orig.getBlue()));
      }
    }
    return newPixels;
  }
}