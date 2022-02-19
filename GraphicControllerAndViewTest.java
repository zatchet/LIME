package controller;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import view.MockView;

public class GraphicControllerAndViewTest {
  private MockController mockController;
  private MockView mockView;
  private Appendable vout;
  private Appendable cout;

  @Before
  public void init() {
    this.vout = new StringBuilder();
    this.cout = new StringBuilder();
    this.mockView = new MockView(this.vout);
    this.mockController = new MockController(this.mockView, this.cout);
  }

  @Test
  public void testRun(){
    mockController.run();
    assertEquals("run initiated", cout.toString());
  }

  @Test
  public void testSaveWiring() {
    this.mockView.fireSaveEvent();
    assertEquals("save initiated", this.vout.toString());
    assertEquals("save handled", this.cout.toString());
  }

  @Test
  public void testLoadWiring() {
    this.mockView.fireLoadEvent();
    assertEquals("load initiated", this.vout.toString());
    assertEquals("load handled", this.cout.toString());
  }

  @Test
  public void testExecuteWiring() {
    this.mockView.fireExecuteEvent();
    assertEquals("execute initiated", this.vout.toString());
    assertEquals("execute handled", this.cout.toString());
  }

  @Test
  public void testBlurWiring() {
    this.mockView.fireBlurEvent();
    assertEquals("blur initiated", this.vout.toString());
    assertEquals("blur handled", this.cout.toString());
  }

  @Test
  public void testSepiaWiring() {
    this.mockView.fireSepiaEvent();
    assertEquals("sepia initiated", this.vout.toString());
    assertEquals("sepia handled", this.cout.toString());
  }

  @Test
  public void testGreyscaleWiring() {
    this.mockView.fireGreyscaleEvent();
    assertEquals("greyscale initiated", this.vout.toString());
    assertEquals("greyscale handled", this.cout.toString());
  }

  @Test
  public void testSharpenWiring() {
    this.mockView.fireSharpenEvent();
    assertEquals("sharpen initiated", this.vout.toString());
    assertEquals("sharpen handled", this.cout.toString());
  }

  @Test
  public void testChooseFile() {
    mockView.chooseFile();
    assertEquals("file choose", vout.toString());
  }

  @Test
  public void testRefreshImage() {
    mockView.refreshImage(null);
    assertEquals("image refresh", vout.toString());
  }

  @Test
  public void testRenderMessage() {
    mockView.renderMessage("blah");
    assertEquals("rendering message: blah", vout.toString());
  }

  @Test
  public void testAskExt() {
    mockView.askForExt();
    assertEquals("asking for ext", vout.toString());
  }

  @Test
  public void testAskProjName() {
    mockView.askForProjectName();
    assertEquals("ask for proj name", vout.toString());
  }

  @Test
  public void testAskForSaveMethod() {
    mockView.askForSaveMethod();
    assertEquals("ask for save method", vout.toString());
  }

  @Test
  public void testAskForLayer() {
    mockView.askForLayer(5);
    assertEquals("ask for layer 5", vout.toString());
  }

  @Test
  public void testGetDim() {
    mockView.getCheckDimensions();
    assertEquals("getting dimensions", vout.toString());
  }

  @Test
  public void testRefreshBar() {
    mockView.refreshMessageBar(5, 5);
    assertEquals("refreshing bar 55", vout.toString());
  }

}
