package controller;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import java.awt.Color;
import model.ClampedColor;
import model.Image;
import model.ImageImpl;
import model.Pixel;

/**
 * Class for creating an Image from the JPEG, JPG, or PNG file formats. An image is created with the
 * given file name.
 */
public class ImageFromJPEGPNG implements ImageCreator {

  @Override
  public Image create(String fileName) {
    if (fileName == null) {
      throw new IllegalArgumentException("The name of the file must not be null.");
    }
    InputStream inputStream;
    BufferedImage image;

    try {
      inputStream = new FileInputStream(fileName);
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Could not find file.");
    }

    try {
      image = ImageIO.read(inputStream);
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not convert file to buffered image");
    }

    Pixel[][] pixels = new Pixel[image.getHeight()][image.getWidth()];

    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        int pixel = image.getRGB(j, i);
        Color color = new Color(pixel);
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();

        pixels[i][j] = new Pixel(new ClampedColor(red, green, blue));
      }
    }

    return new ImageImpl(pixels);
  }
}
