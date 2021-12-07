package model;

/**
 * Represents a color with three channels: red, green, blue. The color is an essential fundamental
 * component of an image as each pixel has a color with three channels.
 */
public interface Color {

  /**
   * Gets the value of the red channel in the color.
   *
   * @return the red channel value
   */
  int getRed();

  /**
   * Gets the value of the green channel in the color.
   *
   * @return the green channel value
   */
  int getGreen();

  /**
   * Gets the value of the blue channel in the color.
   *
   * @return the blue channel value
   */
  int getBlue();

  /**
   * Displays the color represented as a tuple of its channels (r, g, b).
   *
   * @return the string representation of the color
   */
  String toString();

}
