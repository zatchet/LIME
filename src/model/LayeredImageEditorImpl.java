package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an image processing model for a layered image. LayeredImageEditor contains multiple
 * layers. Operations can be applied on any layer.
 */
public class LayeredImageEditorImpl implements LayeredImageEditor {

  private final List<Layer> layers;

  /**
   * Constructs a LayeredImageEditorImpl with one layer to start with.
   * @param startImage the image for the base layer
   */
  public LayeredImageEditorImpl(Image startImage) {
    layers = new ArrayList<>();
    layers.add(new LayerImpl(startImage));
  }

  /**
   * Constructs a LayeredImageEditorImpl object with a checkerboard image base layer.
   * @param rows the number of rows in the checkerboard
   * @param cols the number of columns in the checkerboard
   */
  public LayeredImageEditorImpl(int rows, int cols) {
    layers = new ArrayList<>();
    layers.add(new LayerImpl(rows, cols));
  }

  public LayeredImageEditorImpl() {
    layers = new ArrayList<>();
  }

  @Override
  public void sharpen(int layer) {
    checkValidLayer(layer);
    layers.get(layer).sharpen();
  }

  @Override
  public void blur(int layer) {
    checkValidLayer(layer);
    layers.get(layer).blurr();
  }

  @Override
  public void greyscale(int layer) {
    checkValidLayer(layer);
    layers.get(layer).greyscale();
  }

  @Override
  public void sepia(int layer) {
    checkValidLayer(layer);
    layers.get(layer).sepia();
  }

  @Override
  public void customFilter(Kernel kernel, int layer) {
    checkValidLayer(layer);
    layers.get(layer).customFilter(kernel);
  }

  @Override
  public void customColorTransform(TransformationMatrix matrix, int layer) {
    checkValidLayer(layer);
    layers.get(layer).customColorTransform(matrix);
  }

  @Override
  public void addLayer() {
    layers.add(new LayerImpl(layers.get(layers.size() - 1).getCurrentImage()));
  }

  @Override
  public void addLayer(Layer image) {
    if (image.getHeight() != getHeight() || image.getWidth() != getWidth()) {
      throw new IllegalArgumentException(
          "Layers must have the same dimensions as the layered image");
    }
    layers.add(image);
  }

  @Override
  public void removeLayer(int layerNum) {
    checkValidLayer(layerNum);
    if (layers.size() == 1) {
      throw new IllegalStateException("Cannot remove the only layer in an image");
    }
    layers.remove(layerNum);
  }

  @Override
  public Layer getLayer(int layerNum) {
    checkValidLayer(layerNum);
    return this.layers.get(layerNum);
  }

  @Override
  public int getNumLayers() {
    return this.layers.size();
  }

  @Override
  public void makeVisible(int layer) {
    checkValidLayer(layer);
    layers.get(layer).setVisible(true);
  }

  @Override
  public void makeInvisible(int layer) {
    checkValidLayer(layer);
    layers.get(layer).setVisible(false);
  }

  @Override
  public boolean visible(int layer) {
    checkValidLayer(layer);
    return layers.get(layer).visible();
  }

  @Override
  public Pixel getPixelAt(int row, int col) {
    return getTopLayer().getPixelAt(row, col);
  }

  @Override
  public int getWidth() {
    return getTopLayer().getWidth();
  }

  @Override
  public int getHeight() {
    return getTopLayer().getHeight();
  }

  //returns the current top layer of the image editor
  private Image getTopLayer() {
    return layers.get(layers.size() - 1);
  }

  //enforces that a given layer is in bounds
  private void checkValidLayer(int layer) {
    if (layer < 0 || layer >= layers.size()) {
      throw new IllegalArgumentException("layer out of bounds");
    }
  }
}

