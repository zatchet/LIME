package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MockView implements InteractiveView {
  private ActionListener listener;
  private final Appendable out;

  public MockView(Appendable out) {
    this.out = out;
  }

  private void write(String message) {
    try {
      this.out.append(message);
    } catch (IOException var3) {
      throw new IllegalStateException("Appendable failed test should fail");
    }
  }

  public void fireSaveEvent() {
    this.write("save initiated");
    this.listener.actionPerformed(new ActionEvent(this, 0, "save handled"));
  }

  public void fireLoadEvent() {
    this.write("load initiated");
    this.listener.actionPerformed(new ActionEvent(this, 0, "load handled"));
  }

  public void fireExecuteEvent() {
    this.write("execute initiated");
    this.listener.actionPerformed(new ActionEvent(this, 0, "execute handled"));
  }

  public void fireBlurEvent() {
    this.write("blur initiated");
    this.listener.actionPerformed(new ActionEvent(this, 0, "blur handled"));
  }

  public void fireSepiaEvent() {
    this.write("sepia initiated");
    this.listener.actionPerformed(new ActionEvent(this, 0, "sepia handled"));
  }

  public void fireGreyscaleEvent() {
    this.write("greyscale initiated");
    this.listener.actionPerformed(new ActionEvent(this, 0, "greyscale handled"));
  }

  public void fireSharpenEvent() {
    this.write("sharpen initiated");
    this.listener.actionPerformed(new ActionEvent(this, 0, "sharpen handled"));
  }

  @Override
  public void renderMessage(String message) {
    write("rendering message: " + message);
  }

  @Override
  public void registerViewEventListener(ActionListener a) {
    this.listener = a;
  }

  @Override
  public File chooseFile() {
    write("file choose");
    return null;
  }

  @Override
  public void refreshImage(BufferedImage buff) {
    write("image refresh");
  }

  @Override
  public String askForExt() {
    write("asking for ext");
    return null;
  }

  @Override
  public String askForProjectName() {
    write("ask for proj name");
    return null;
  }

  @Override
  public int askForSaveMethod() {
    write("ask for save method");
    return 0;
  }

  @Override
  public int askForLayer(int numLayers) {
    write("ask for layer " + numLayers);
    return 0;
  }

  @Override
  public int[] getCheckDimensions() {
    write("getting dimensions");
    return new int[0];
  }

  @Override
  public void refreshMessageBar(int x, int y) {
    write("refreshing bar " + x + y);
  }
}
