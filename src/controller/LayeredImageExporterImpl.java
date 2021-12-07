package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import model.Image;
import model.Layer;
import model.LayeredImageEditor;

/**
 * Class for exporting multilayered images. Contains two operations: export top visible layer, or
 * export all visible layers.
 */
public class LayeredImageExporterImpl implements LayeredImageExporter {

  private final LayeredImageEditor layeredImage;

  /**
   * Creates an instance of a layered image exporter.
   *
   * @param layeredImage the layered image.
   */
  public LayeredImageExporterImpl(LayeredImageEditor layeredImage) {
    if (layeredImage == null) {
      throw new IllegalArgumentException("Cannot export a null layered image");
    }
    this.layeredImage = layeredImage;
  }

  @Override
  public void exportTop(String fileName, String format) {
    Image topVisible = getTopVisible();
    export(topVisible, fileName, format);
  }

  @Override
  public void exportAll(String fileName, String fileFormat) {

    if (!FileUtil.getSupportedFileTypes().contains(fileFormat)) {
      throw new IllegalArgumentException("Unsupported file type");
    }

    StringBuilder txtFile = new StringBuilder();

    File existing = new File(fileName.substring(0, fileName.lastIndexOf(File.separator) + 1));
    File newFile;
    if (existing.getPath().equals("")) {
      newFile = new File(fileName);
    }
    else {
      newFile = new File(existing, FileUtil.getNameFromPath(fileName));
    }
    if (newFile.mkdir()) {

      for (int i = 0; i < layeredImage.getNumLayers(); i++) {
        String filePath;
        if (!layeredImage.visible(i)) {
          filePath = fileName + File.separator + "layer" + i + "INVISIBLE." + fileFormat;
        } else {
          filePath = fileName + File.separator + "layer" + i + "." + fileFormat;
        }
        Layer layer = layeredImage.getLayer(i);
        export(layer, filePath, fileFormat);
        txtFile.append(filePath).append("\n");
      }
    } else {
      throw new IllegalArgumentException("Folder cannot be created");
    }

    String textFileName = FileUtil.getNameFromPath(fileName) + ".txt";
    try {
      FileWriter fileWriter = new FileWriter(fileName + File.separator + textFileName);
      fileWriter.append(txtFile);
      fileWriter.close();
    } catch (IOException e) {
      throw new IllegalArgumentException("exporting failed.");
    }

  }

  //handles exporting oen image
  private void export(Image image, String fileName, String format) {
    ImageExporter exporter;
    //String format = FileUtil.getFileExtension(fileName);
    FileUtil.checkNotSupported(format);
    if (format.equals("ppm")) {
      exporter = new PPM(image);
    } else {
      exporter = new MultiExporter(image);
    }

    exporter.export(fileName);

  }

  //retrieves the top most visible image from a layered image
  private Layer getTopVisible() {
    for (int i = layeredImage.getNumLayers() - 1; i >= 0; i--) {
      Layer layer = layeredImage.getLayer(i);
      if (layer.visible()) {
        return layer;
      }
    }
    throw new IllegalStateException("No visible layers to export.");
  }
}
