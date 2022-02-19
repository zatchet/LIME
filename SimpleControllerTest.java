package controller;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

/**
 * Tests the functionality of the simple controller.
 */
public class SimpleControllerTest {

  private ImageEditorController controller;
  private Appendable out;
  private Readable in;

  @Test
  public void testModelNull() throws IOException {

    List<String> commands = new ArrayList<>(Arrays.asList("sepia", "greyscale", "blur",
        "sharpen", "export", "exportAll", "make", "addCopy", "addFromImage", "addCheckerboard",
        "remove"));
    out = new StringBuilder();
    for (int i = 0; i < 11; i++) {
      in = new StringReader(commands.get(i));
      controller = new SimpleController(in, out);
      controller.run();
    }

    assertEquals("need to create or load first\n"
        + "need to create or load first\n"
        + "need to create or load first\n"
        + "need to create or load first\n"
        + "need to create or load first\n"
        + "need to create or load first\n"
        + "need to create or load first\n"
        + "need to create or load first\n"
        + "need to create or load first\n"
        + "need to create or load first\n"
        + "need to create or load first\n", out.toString());
  }

  @Test
  public void testUnknownCommand() throws IOException {
    out = new StringBuilder();
    in = new StringReader("hi");
    controller = new SimpleController(in, out);
    controller.run();
    assertEquals("Unknown command\n", out.toString());
  }

  @Test
  public void testRemoveOutOfBounds() throws IOException {
    out = new StringBuilder();
    in = new StringReader("create ppm Koala.ppm remove 1");
    controller = new SimpleController(in, out);
    controller.run();
    assertEquals("layer out of bounds\n", out.toString());
  }

  @Test
  public void testCreateBadFileExt() throws IOException {
    out = new StringBuilder();
    in = new StringReader("create blah Koala.ppm");
    controller = new SimpleController(in, out);
    controller.run();
    assertEquals("unsupported file type\n", out.toString());
  }

  @Test
  public void testCreateNonMatching() throws IOException {
    out = new StringBuilder();
    in = new StringReader("create png Koala.ppm");
    controller = new SimpleController(in, out);
    controller.run();
    assertEquals("extension does not match file type\n", out.toString());
  }

  @Test
  public void testBadLoad() throws IOException {
    out = new StringBuilder();
    in = new StringReader("load blah blah");
    controller = new SimpleController(in, out);
    controller.run();
    assertEquals("unsupported file type\n", out.toString());
  }

  @Test
  public void testBadLoadType() throws IOException {
    out = new StringBuilder();
    in = new StringReader("load png blah");
    controller = new SimpleController(in, out);
    controller.run();
    assertEquals("Could not load the file with the given name\n", out.toString());
  }

  @Test
  public void testCreateCheckerBoard() throws IOException {
    out = new StringBuilder();
    in = new StringReader("createCheckerboard -10 40");
    controller = new SimpleController(in, out);
    controller.run();
    assertEquals("dimensions of checkerboard cannot be negative\n", out.toString());
  }

  @Test
  public void testCommandOutOfBounds() throws IOException {
    out = new StringBuilder();
    in = new StringReader("createCheckerboard 40 40 sepia 20");
    controller = new SimpleController(in, out);
    controller.run();
    assertEquals("layer out of bounds\n", out.toString());
  }

  @Test
  public void testExportBadType() throws IOException {
    out = new StringBuilder();
    in = new StringReader("createCheckerboard 40 40 export blah checker.blah");
    controller = new SimpleController(in, out);
    controller.run();
    assertEquals("unsupported file type\n", out.toString());
  }

  @Test
  public void testExportBadExt() throws IOException {
    out = new StringBuilder();
    in = new StringReader("createCheckerboard 40 40 export jpg checker.blah");
    controller = new SimpleController(in, out);
    controller.run();
    assertEquals("extension does not match file type\n", out.toString());
  }

  @Test
  public void testExportAllBadType() throws IOException {
    out = new StringBuilder();
    in = new StringReader("createCheckerboard 40 40 exportAll blah checker");
    controller = new SimpleController(in, out);
    controller.run();
    assertEquals("unsupported file type\n", out.toString());
  }

  @Test
  public void testMakeNothingAfter() throws IOException {
    out = new StringBuilder();
    in = new StringReader("createCheckerboard 40 40 make blah 3");
    controller = new SimpleController(in, out);
    controller.run();
    assertEquals("Make must be followed by either visible or invisible\n", out.toString());
  }

  @Test
  public void testAddFromImageBadType() throws IOException {
    out = new StringBuilder();
    in = new StringReader("createCheckerboard 40 40 addFromImage hg bjsb");
    controller = new SimpleController(in, out);
    controller.run();
    assertEquals("unsupported file type\n", out.toString());
  }

  @Test
  public void testAddFromImageBadExt() throws IOException {
    out = new StringBuilder();
    in = new StringReader("createCheckerboard 40 40 addFromImage jpg something.blah");
    controller = new SimpleController(in, out);
    controller.run();
    assertEquals("extension does not match file type\n", out.toString());
  }

  @Test
  public void testAddFromImageDifferentDimensions() throws IOException {
    out = new StringBuilder();
    in = new StringReader("createCheckerboard 40 40 addFromImage ppm Koala.ppm");
    controller = new SimpleController(in, out);
    controller.run();
    assertEquals("Layers must have the same dimensions as the layered image\n", out.toString());
  }

  @Test
  public void testAddFromCheckerboardDiffDimensions() throws IOException {
    out = new StringBuilder();
    in = new StringReader("createCheckerboard 40 40 addCheckerboard 10 10");
    controller = new SimpleController(in, out);
    controller.run();
    assertEquals("Layers must have the same dimensions as the layered image\n", out.toString());
  }

  @Test
  public void testAddFromCheckerboardNegDimensions() throws IOException {
    out = new StringBuilder();
    in = new StringReader("createCheckerboard 40 40 addCheckerboard -90 10");
    controller = new SimpleController(in, out);
    controller.run();
    assertEquals("dimensions of checkerboard cannot be negative\n", out.toString());
  }

  @Test
  public void testRemoveOnlyLayer() throws IOException {
    out = new StringBuilder();
    in = new StringReader("createCheckerboard 40 40 remove 0");
    controller = new SimpleController(in, out);
    controller.run();
    assertEquals("Cannot remove the only layer in an image\n", out.toString());
  }

  @Test
  public void testValidOperations() throws IOException {
    out = new StringBuilder();
    in = new StringReader("create ppm Koala.ppm "
        + "addCopy "
        + "remove 0 "
        + "make invisible 0 "
        + "createCheckerboard 20 20 "
        + "addCopy "
        + "addCheckerboard 20 20 "
        + "sepia 1 "
        + "sharpen 1 "
        + "greyscale 1 "
        + "export jpg this89.jpg "
        + "exportAll png this "
        + "load png this ");
    controller = new SimpleController(in, out);
    controller.run();
    assertEquals("", out.toString());
  }

  @Test
  public void testExportAll2() throws IOException {
    out = new StringBuilder();
    in = new StringReader("create jpg Koala.jpg export png jdjd/djdj.png");
    controller = new SimpleController(in, out);
    controller.run();
    assertEquals("", out.toString());
  }

}