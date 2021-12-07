package controller;

import model.LayeredImageEditor;

/**
 * Interface for importing multilayered images. Contains just one method create that returns a
 * layered image editor based off a filename.
 */
public interface LayeredImageImporter {

  /**
   * Searches file directory for the given file and if possible, constructs the layers into a
   * multi layered image.
   * @param fileName the name of the directory in which all the layers are stored.
   * @return the new layered image editor.
   */
  LayeredImageEditor create(String fileName);

}
