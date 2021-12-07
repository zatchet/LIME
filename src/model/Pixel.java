package model;

/**
 * Class representing a pixel in a 24-bit image with three 8 bit channels corresponding to rgb color
 * values.
 */
public final class Pixel {

  private final Color color;

  /**
   * Constructs a pixel from the given color and position of the pixel.
   *
   * @param color the color of the pixel
   */
  public Pixel(Color color) {
    this.color = color;
  }

  /**
   * Generates the pixel in string form as a tuple of its color channels.
   *
   * @return the string representation of the pixel
   */
  public String toString() {
    return color.toString();
  }

  /**
   * Gets the color of the pixel.
   *
   * @return a copy of the color of the pixel
   */
  public Color getColor() {
    return new ClampedColor(color.getRed(), color.getGreen(), color.getBlue());
  }
}