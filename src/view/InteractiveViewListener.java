package view;

public interface InteractiveViewListener {

  public void handleLoadEvent();

  public void handleSaveEvent();

  public void handleBlurEvent();

  public void handleSharpenEvent();

  public void handleSepiaEvent();

  public void handleGreyscaleEvent();

  public void handleAddLayerEvent();

  public void handleRemoveLayerEvent();

  public void handleInvisibleEvent();

}
