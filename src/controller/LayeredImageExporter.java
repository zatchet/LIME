package controller;

/**
 * Exports the given image in a file with the given name and format. Supported formats include JPEG,
 * JPG, PNG, and PPM. A layered image is exported in a folder containing the images for each layer
 * and a text file listing the locations of each layer. Exporting the top visible layer is also
 * supported and is exported like a regular image.
 */
public interface LayeredImageExporter {

  /**
   * Exports the top visible layer to a file with the given name and format.
   *
   * @param fileName the name of the file to export the image to
   * @param format   the format of the file to export
   */
  void exportTop(String fileName, String format);

  /**
   * Exports all the layers in the image.
   *
   * @param fileName   the name of the folder to create and name of the text file containing the
   *                   layer locations
   * @param fileFormat the format to export the layers in
   */
  void exportAll(String fileName, String fileFormat);
}
