package model;

/**
 * Represents an interface for an Image editing application. Provides methods to create a new
 * altered image.
 */
public interface ImageEditor extends Image {

  /**
   * Sharpens the current image.
   */
  void sharpen();

  /**
   * Blurs the current image.
   */
  void blurr();

  /**
   * Applies greyscale effect to the current image.
   */
  void greyscale();

  /**
   * Applies sepia effect to the current image.
   */
  void sepia();

  /**
   * Filters the current image according to the provided kernel.
   * @param kernel the kernel to be used
   * @throws IllegalArgumentException if the Kernel is malformed.
   */
  void customFilter(Kernel kernel);

  /**
   * Transforms the current images color according to the provided matrix.
   * @param transformationMatrix the transformationMatrix to be used
   * @throws IllegalArgumentException if the transformationMatrix is malformed.
   */
  void customColorTransform(TransformationMatrix transformationMatrix);

  /**
   * Reverts to the previous version of the image.
   */
  void revert();

  /**
   * Retrieves the current version of the Image that is being worked on.
   * @return the current Image.
   */
  Image getCurrentImage();

  /**
   * Gets the version number of the current Image, where the version number the amount of times
   * the image has been altered.
   * @return the version number.
   */
  int getVersionNumber();
}
