package controller;

import model.Image;
import model.ImageImpl;
import model.Pixel;
import util.ImageUtil;

/**
 * Creates an Image instance from a PPM file in "plain" ppm format signified by "p3" in the first
 * line. See the format specification here: http://netpbm.sourceforge.net/doc/ppm.html
 */
public final class ImageFromPPM implements ImageCreator {

  @Override
  public Image create(String fileName) throws IllegalArgumentException {

    if (fileName == null) {
      throw new IllegalArgumentException("filename cannot be null");
    }

    if (!fileName.substring(fileName.length() - 4).equals(".ppm")) {
      throw new IllegalArgumentException("File must be ppm.");
    }

    Pixel[][] pixels = ImageUtil.pixelDeepCopy(ImageUtil.readPPM(fileName));
    return new ImageImpl(pixels);
  }

}
