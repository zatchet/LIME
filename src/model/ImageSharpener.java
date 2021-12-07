package model;

/**
 * Represents the sharpen operation to be applied to an image. This operation can be applied
 * multiple times to produce a more drastic sharpened effect.
 */
public class ImageSharpener extends AbstractFilter {

  private static final Kernel sharpen = new Kernel(
      new double[][]{{-0.125, -0.125, -0.125, -0.125, -0.125},
          {-0.125, 0.25, 0.25, 0.25, -0.125},
          {-0.125, 0.25, 1, 0.25, -0.125},
          {-0.125, 0.25, 0.25, 0.25, -0.125},
          {-0.125, -0.125, -0.125, -0.125, -0.125}});

  /**
   * Constructs an ImageSharpener with the default sharpen kernel.
   */
  public ImageSharpener() {
    super(sharpen);
  }
}

