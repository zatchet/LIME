package model;

/**
 * Represents an operation that can be applied to an image for image processing. A new image is
 * produced consisting of pixels that are calculated by performing operations on the original
 * image's pixels. Some examples for an image operation are blurring, sharpening and converting the
 * colors of the image to greyscale or sepia tones.
 */
public interface ImageOperation {

  /**
   * Applies the operation to the image by performing calculations on each of the pixels.
   *
   * @param image the image to be processed
   * @return a new Image consisting of pixels that are the result of the operation
   */
  Image apply(Image image);
}
