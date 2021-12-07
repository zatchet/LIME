package model;

/**
 * Represents an interface for a Layer, which is a simple image editor with toggleable visibility.
 * Visibility can be specified in constructor or toggled with the setter method.
 */
public interface Layer extends ImageEditor {

  /**
   * Sets the visibility of this layer to the given bool.
   * @param visible the desired visibility
   */
  void setVisible(boolean visible);

  /**
   * Retrieves the state of this layer's visibility.
   * @return true if this layer is visible or false otherwise
   */
  boolean visible();

}
