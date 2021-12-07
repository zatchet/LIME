package controller;

import java.io.FileWriter;
import java.io.IOException;
import model.Color;
import model.Image;
import model.Pixel;

/**
 * Represents an Image exporter that exports in ASCII P3 PPM file format. Exports as a file.
 */
public class PPM implements ImageExporter {

  private final Image image;

  /**
   * Constructs an instance of the exporter.
   * @param image the image to be exported.
   */
  public PPM(Image image) {
    if (image == null) {
      throw new IllegalArgumentException("Image to export as PPM cannot be null");
    }

    this.image = image;
  }

  @Override
  public void export(String fileName) {
    try {
      FileWriter out = new FileWriter(fileName);
      out.append(buildPPM());
      out.close();
    } catch (IOException e) {
      throw new IllegalArgumentException(
          "Could not write to output location");
    }
  }

  // formats the Image into a String in PPM format.
  private String buildPPM() {
    StringBuilder ppm = new StringBuilder();
    ppm.append("P3\n");
    ppm.append(Integer.toString(this.image.getWidth())).append(" ");
    ppm.append(Integer.toString(this.image.getHeight())).append("\n");
    ppm.append(Integer.toString(255)).append("\n");
    for (int i = 0; i < this.image.getHeight(); i++) {
      for (int j = 0; j < this.image.getWidth(); j++) {
        Pixel pixel = image.getPixelAt(i, j);
        Color rgb = pixel.getColor();
        ppm.append(Integer.toString(rgb.getRed())).append("\n");
        ppm.append(Integer.toString(rgb.getGreen())).append("\n");
        ppm.append(Integer.toString(rgb.getBlue())).append("\n");
      }
    }
    return ppm.toString().strip();
  }
}