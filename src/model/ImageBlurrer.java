package model;

/**
 * Represents the blur operation to be applied to an image. This operation can be applied multiple
 * times to produce a more drastic blurred effect.
 */
public class ImageBlurrer extends AbstractFilter {

  private static final Kernel blur = new Kernel(new double[][]{
      {0.0625, 0.125, 0.0625},
      {0.125, 0.25, 0.125},
      {0.0625, 0.125, 0.0625}});

  /**
   * Constructs an ImageBlurrer with the default blur kernel.
   */
  public ImageBlurrer() {
    super(blur);
  }

}
