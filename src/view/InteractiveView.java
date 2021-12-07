package view;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public interface InteractiveView extends ImageEditorView{

  void registerViewEventListener(ActionListener a);

  File chooseFile();

  void refreshImage(BufferedImage buff);

  String askForExt();

  String askForProjectName();

  int askForSaveMethod();

  int askForLayer(int numLayers);

  int[] getCheckDimensions();

  void refreshMessageBar(int x, int y);

}
