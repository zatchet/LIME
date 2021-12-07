package model;

/**
 * Represents a standard Greyscale transformation that can be applied to an Image. Contains a
 * standard matrix used for greyscaling as a static field.
 */
public class Greyscale extends AbstractColorTransformation {

  private static final TransformationMatrix greyscale = new TransformationMatrix(
      new double[][]{{0.2126, 0.7152, 0.0722}, {0.2126, 0.7152, 0.0722}, {0.2126, 0.7152, 0.0722}});

  /**
   * Standard constructor for a greyscale object. Simply calls the super constructor with the
   * preset greyscale matrix.
   */
  public Greyscale() {
    super(greyscale);
  }
}
