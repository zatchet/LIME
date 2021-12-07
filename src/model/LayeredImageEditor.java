package model;

/**
 * Represents an image processing tool for layered images. Operations such as filtering and color
 * transformations are supported as well as making some layers invisible. Invisible layers will not
 * be exported.
 */
public interface LayeredImageEditor extends Image {

  /**
   * Sharpens the layer with the provided layer number.
   * @param layer the number of the layer to sharpen
   */
  void sharpen(int layer);

  /**
   * Blurs the layer with the provided layer number.
   * @param layer the number of the layer to blur
   */
  void blur(int layer);

  /**
   * Converts the layer with the provided layer number to greyscale.
   * @param layer the number of the layer to make greyscale
   */
  void greyscale(int layer);

  /**
   * Converts the layer with the provided layer number to sepia.
   * @param layer the number of the layer to make sepia
   */
  void sepia(int layer);

  /**
   * Applies the custom filter to the provided layer.
   * @param kernel the kernel to apply to the layer
   * @param layer  the number of the layer to filter
   */
  void customFilter(Kernel kernel, int layer);

  /**
   * Applies the custom color transformation to the provided layer.
   * @param matrix the matrix to apply to the layer
   * @param layer  the number of the layer to transform
   */
  void customColorTransform(TransformationMatrix matrix, int layer);

  /**
   * Adds a new layer to the image that is the same as the top layer.
   */
  void addLayer();

  /**
   * Adds a layer to the image that is the given image.
   * @param image the image to make the new layer
   */
  void addLayer(Layer image);

  /**
   * Removes the given layer.
   * @param layerNum the number of the layer to remove
   */
  void removeLayer(int layerNum);

  /**
   * Returns the layer with the given layer number.
   * @param layerNum the number of the layer to return
   * @return the layer with the given number
   */
  Layer getLayer(int layerNum);

  /**
   * Gets the total number of layers in the layered image.
   * @return the number of layers in the image
   */
  int getNumLayers();

  /**
   * Makes a layer invisible so it won't be exported.
   * @param layer the number of the layer to make invisible
   */
  void makeVisible(int layer);

  /**
   * Makes a layer visible.
   * @param layer the number of the layer to make visible.
   */
  void makeInvisible(int layer);

  /**
   * Determines if a layer is visible or not.
   * @param layer the number of the layer to check for visibility
   * @return whether the layer is visible
   */
  boolean visible(int layer);
}
