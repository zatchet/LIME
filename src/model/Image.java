package model;

/**
 * Represents an image consisting of pixel data that indicates the colors of each portion of the
 * image. These pixels can be operated on to produce a new image that looks different than the
 * original.
 */
public interface Image {

  /**
   * Gets a copy of the pixel in the image at the given row and column.
   *
   * @param row the row of the pixel
   * @param col the column of the pixel
   * @return the pixel at the row and column given
   * @throws IllegalArgumentException if the given row or column is invalid for the image
   */
  Pixel getPixelAt(int row, int col);

  /**
   * Gets the number of columns of pixels in the image.
   *
   * @return the width of the image
   */
  int getWidth();

  /**
   * Gets the number of rows of pixels in the image.
   *
   * @return the width of the image
   */
  int getHeight();

}
