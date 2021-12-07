package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import model.Color;
import model.Image;

/**
 * Class for exporting images as jpeg or png files. Uses ImageIO library to handle output. Really,
 * file type that is supported by ImageIO is supported here.
 */
public class MultiExporter implements ImageExporter {

  private final Image image;

  /**
   * Initializes an instance of an exporter object given an image.
   *
   * @param image the image
   */
  public MultiExporter(Image image) {
    if (image == null) {
      throw new IllegalArgumentException("The image to be exported cannot be null");
    }
    this.image = image;
  }

  @Override
  public void export(String fileName) {
    File outFile = new File(fileName);
    try {
      outFile.createNewFile();
      String format = FileUtil.getFileExtension(fileName);
      FileUtil.checkNotSupported(format);
      ImageIO.write(makeBufferedImage(), format, outFile);
    } catch (IOException e) {
      throw new IllegalArgumentException("Writing to the given file failed: " + e.getMessage());
    }
  }

  //makes the buffered image using the images pixel data
  private BufferedImage makeBufferedImage() {
    BufferedImage buffered = new BufferedImage(image.getWidth(), image.getHeight(),
        BufferedImage.TYPE_3BYTE_BGR);

    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        Color col = image.getPixelAt(i, j).getColor();
        int rgb = col.getRed();
        rgb = (rgb << 8) + col.getGreen();
        rgb = (rgb << 8) + col.getBlue();
        buffered.setRGB(j, i, rgb);
      }
    }
    return buffered;
  }

}
