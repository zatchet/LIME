package controller;

import model.Image;

/**
 * Creates an Image object from the file with the given filename. Implementations of this interface
 * will create images from different file formats including ppm.
 */
public interface ImageCreator {

  /**
   * Creates an Image from the file containing pixel data.
   *
   * @param fileName the name of the image file
   * @return the Image object with data from the file
   * @throws IllegalArgumentException if the file at the filename path could not be found or if the
   *                                  filename is null.
   */
  Image create(String fileName);

}
