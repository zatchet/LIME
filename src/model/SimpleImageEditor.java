package model;

import controller.ImageFromPPM;
import java.util.Stack;

/**
 * Represents a simple Image editor model. This implementation only supports having one image
 * at a time.
 */
public class SimpleImageEditor implements ImageEditor {

  private final Stack<Image> versions;

  /**
   * Constructs a simple image editor object.
   * @param image the initial image as an image object
   */
  public SimpleImageEditor(Image image) {
    if (image == null) {
      throw new IllegalArgumentException("image cannot be null");
    }
    versions = new Stack<Image>();
    versions.push(image);
  }

  /**
   * Constructs a simple image editor object based off a fileName.
   * @param fileName the name of the file
   */
  public SimpleImageEditor(String fileName) {
    if (fileName == null) {
      throw new IllegalArgumentException("file name cannot be null");
    }
    Image image = new ImageFromPPM().create(fileName);
    versions = new Stack<Image>();
    versions.push(image);
  }

  /**
   * Creates a checkerboard image programmatically.
   * @param checkerBoardRows the amount of rows
   * @param checkerBoardCols the amount of columns
   */
  public SimpleImageEditor(int checkerBoardRows, int checkerBoardCols) {

    if (checkerBoardCols < 0 || checkerBoardRows < 0) {
      throw new IllegalArgumentException("dimensions of checkerboard cannot be negative");
    }

    Pixel[][] checkerPixels = new Pixel[checkerBoardRows][checkerBoardCols];
    for (int i = 0; i < checkerBoardRows; i ++) {
      for (int j = 0; j < checkerBoardCols; j ++) {
        Color c;
        if ((i + j) % 2 == 0) {
          c = new ClampedColor(255,0,0);
        }
        else {
          c = new ClampedColor(0,255,0);
        }
        checkerPixels[i][j] = new Pixel(c);
      }
    }
    Image image = new ImageImpl(checkerPixels);
    versions = new Stack<>();
    versions.push(image);
  }


  @Override
  public void sharpen() {
    versions.push(new ImageSharpener().apply(versions.peek()));
  }

  @Override
  public void blurr() {
    versions.push(new ImageBlurrer().apply(versions.peek()));
  }

  @Override
  public void greyscale() {
    versions.push(new Greyscale().apply(versions.peek()));
  }

  @Override
  public void sepia() {
    versions.push(new Sepia().apply(versions.peek()));
  }

  @Override
  public void customFilter(Kernel kernel) {
    versions.push(new AbstractFilter(kernel).apply(versions.peek()));
  }

  @Override
  public void customColorTransform(TransformationMatrix transformationMatrix) {
    versions.push(new AbstractColorTransformation(transformationMatrix).apply(versions.peek()));
  }

  @Override
  public void revert() throws IllegalStateException {
    if (versions.size() == 1) {
      throw new IllegalStateException("Nothing to revert to");
    }
    versions.pop();
  }

  @Override
  public Image getCurrentImage() {
    return versions.peek();
  }

  @Override
  public int getVersionNumber() {
    return versions.size();
  }

  @Override
  public Pixel getPixelAt(int row, int col) {
    return getCurrentImage().getPixelAt(row, col);
  }

  @Override
  public int getWidth() {
    return getCurrentImage().getWidth();
  }

  @Override
  public int getHeight() {
    return getCurrentImage().getHeight();
  }
}
