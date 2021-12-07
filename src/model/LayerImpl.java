package model;

/**
 * Represents a layer that can be stored in a multi layered image. A Layer is an image that
 * can be set to either visible or invisible. This class has three constructors.
 */
public class LayerImpl extends SimpleImageEditor implements Layer {

  private boolean visible;

  /**
   * Creates a layer based off the given image and sets visible to true.
   * @param image the image
   */
  public LayerImpl(Image image) {
    super(image);
    visible = true;
  }

  /**
   * Creates a layer given the specified parameters.
   * @param image the image
   * @param visible the visibility
   */
  public LayerImpl(Image image, boolean visible) {
    super(image);
    this.visible = visible;
  }

  /**
   * Creates a layer as a checkerboard image.
   * @param checkerBoardRows the rows
   * @param checkerBoardCols the columns
   */
  public LayerImpl(int checkerBoardRows, int checkerBoardCols) {
    super(checkerBoardRows, checkerBoardCols);
    visible = true;
  }

  @Override
  public void setVisible(boolean visible) {
    this.visible = visible;
  }

  @Override
  public boolean visible() {
    return visible;
  }
}
