package controller;

/**
 * An interface for exporting Images in various formats to various locations. We expect this to be
 * implemented numerous times for new export locations.
 */
public interface ImageExporter {

  /**
   * Exports the image to a file with the given name. The format of the image is determined by the
   * extension of the given file name.
   *
   * @param fileName the name of the file to create
   * @throws IllegalArgumentException if the fileName is invalid or if writing to the file fails
   */
  void export(String fileName);
}
