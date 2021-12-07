package model;

/**
 * Represents a standard Sepia transformation that can be applied to an Image. Contains a
 * standard matrix used for the sepia effect as a static field.
 */
public class Sepia extends AbstractColorTransformation {

  private static final TransformationMatrix sepia = new TransformationMatrix(
      new double[][]{{0.393, 0.769, 0.189}, {0.349, 0.686, 0.168}, {0.272, 0.534, 0.131}});

  /**
   * Standard constructor for a sepia object. Simply calls the super constructor with the
   * preset sepia matrix.
   */
  public Sepia() {
    super(sepia);
  }
}
