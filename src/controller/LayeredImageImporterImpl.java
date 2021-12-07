package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import model.Image;
import model.Layer;
import model.LayerImpl;
import model.LayeredImageEditor;
import model.LayeredImageEditorImpl;

/**
 * Class for importing layered images from different filetypes. The supported file types include
 * PPM, JPEG, JPG and PNG.
 */
public class LayeredImageImporterImpl implements LayeredImageImporter {

  private ImageCreator delegate;

  /**
   * Constructs a LayeredImageImporterImpl object.
   *
   * @param fileType the type of image file to import
   */
  public LayeredImageImporterImpl(String fileType) {
    if (fileType == null) {
      throw new IllegalArgumentException("File extension for images to import cannot be null");
    }
    if (fileType.equals("ppm")) {
      this.delegate = new ImageFromPPM();
    } else {
      this.delegate = new ImageFromJPEGPNG();
    }
  }

  @Override
  public LayeredImageEditor create(String fileName) {
    if (fileName == null) {
      throw new IllegalArgumentException("filename cannot be null");
    }
    InputStream input;
    Scanner scan;
    try {
      input = new FileInputStream(fileName + File.separator + FileUtil.getNameFromPath(fileName)
          + ".txt");
      scan = new Scanner(input);
    } catch (IOException i) {
      throw new IllegalArgumentException("Could not load the file with the given name");
    }
    String layerName = scan.next();
    Image base;
    if (layerName.contains("INVISIBLE")) {
      base = new LayerImpl(delegate.create(layerName), false);
    } else {
      base = new LayerImpl(delegate.create(layerName), true);
    }
    LayeredImageEditor layeredImage = new LayeredImageEditorImpl(base);
    while (scan.hasNext()) {
      layerName = scan.next();
      Layer layer;
      if (layerName.contains("INVISIBLE")) {
        layer = new LayerImpl(delegate.create(layerName), false);
      } else {
        layer = new LayerImpl(delegate.create(layerName), true);
      }
      layeredImage.addLayer(layer);
    }
    return layeredImage;
  }
}
