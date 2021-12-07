package model;

/**
 * Class representing a color with three channels: red, green, blue. The maximum value for a channel
 * is 255 and the minimum value is 0 and is enforced by the constructor.
 */
public final class ClampedColor implements Color {

  private final int red;
  private final int green;
  private final int blue;

  /**
   * Constructs a ClampedColor given the channel values as integers.
   *
   * @param red   the value of the red channel
   * @param green the value of the green channel
   * @param blue  the value of the blue channel
   */
  public ClampedColor(int red, int green, int blue) {
    this.red = clamp(red);
    this.green = clamp(green);
    this.blue = clamp(blue);
  }

  /**
   * Constructs a ClampedColor given the channel values as doubles.
   *
   * @param red   the value of the red channel
   * @param green the value of the green channel
   * @param blue  the value of the blue channel
   */
  public ClampedColor(double red, double green, double blue) {
    this.red = clamp((int) red);
    this.green = clamp((int) green);
    this.blue = clamp((int) blue);
  }

  @Override
  public int getRed() {
    return this.red;
  }

  @Override
  public int getGreen() {
    return this.green;
  }

  @Override
  public int getBlue() {
    return this.blue;
  }

  @Override
  public String toString() {
    return "(" + this.red + ", " + this.green + ", " + this.blue + ")";
  }

  //Clamps the given channel value to ensure there is not overflow or underflow.
  private static int clamp(int channel) {
    if (channel > 255) {
      return 255;
    } else if (channel < 0) {
      return 0;
    } else {
      return channel;
    }
  }
}
