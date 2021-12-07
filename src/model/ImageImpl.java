package model;

import util.ImageUtil;

/**
 * Represents an Image containing pixel data with a set height and width. An Image can be processed
 * by performing ImageOperations on it which will create a new image with altered pixel data.
 */
public class ImageImpl implements Image {

  private final Pixel[][] pixels;
  private final int width;
  private final int height;

  /**
   * Constructs an ImageImpl from the given pixel data.
   *
   * @param pixels the pixel data for the image
   */
  public ImageImpl(Pixel[][] pixels) {
    if (pixels == null) {
      throw new IllegalArgumentException("Pixel array cannot be null to create an image.");
    }
    checkNullPixels(pixels);
    this.pixels = ImageUtil.pixelDeepCopy(pixels);
    this.height = pixels.length;
    this.width = pixels[0].length;

  }

  @Override
  public Pixel getPixelAt(int row, int col) {
    if (row < 0 || row > this.getHeight() - 1 || col < 0 || col > this.getWidth() - 1) {
      throw new IllegalArgumentException("Invalid row or column for a pixel");
    }
    Pixel p = this.pixels[row][col];
    Color c = p.getColor();
    return new Pixel(new ClampedColor(c.getRed(), c.getGreen(), c.getBlue()));
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  //checks for null pixels
  private static void checkNullPixels(Pixel[][] pixels) {
    for (int i = 0; i < pixels.length; i++) {
      if (pixels[i] == null) {
        throw new IllegalArgumentException("Error: An array in the 2D array of pixels was null");
      }
      for (int j = 0; j < pixels[0].length; j++) {
        if (pixels[i][j] == null) {
          throw new IllegalArgumentException("Error: At least one of the pixels was null.");
        }
      }
    }
  }
}