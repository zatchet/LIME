package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import view.InteractiveView;

public class MockController implements ImageEditorController, ActionListener {
  private final InteractiveView mockView;
  private final Appendable out;

  public MockController(InteractiveView mockView, Appendable out) {
    this.mockView = mockView;
    this.out = out;
    this.mockView.registerViewEventListener(this);
  }

  @Override
  public void run() {
    write("run initiated");
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    this.write(e.getActionCommand());
  }

  //writes the message
  private void write(String message) {
    try {
      this.out.append(message);
    } catch (IOException var3) {
      throw new IllegalStateException("Appendable failed test should fail");
    }
  }
}
