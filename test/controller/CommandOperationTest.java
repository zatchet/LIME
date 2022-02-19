package controller;

import controller.commands.AddCheckerBoardCommand;
import controller.commands.AddCopyCommand;
import controller.commands.AddFromImageCommand;
import controller.commands.CommandOperation;
import controller.commands.ExportAllCommand;
import controller.commands.ExportCommand;
import controller.commands.MakeCommand;
import controller.commands.RemoveCommand;
import controller.commands.SepiaCommand;
import java.io.IOException;
import java.io.StringReader;
import java.util.Scanner;
import model.LayeredImageEditor;
import model.LayeredImageEditorImpl;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Tests the functionality of all the command operations.
 */
public class CommandOperationTest {

  private CommandOperation c;
  LayeredImageEditor model = new LayeredImageEditorImpl(5, 5);
  Scanner sc;
  String s;

  @Test
  public void addCheckerBoardCommand() throws IOException {
    s = "5 5";
    sc = new Scanner(new StringReader(s));
    c = new AddCheckerBoardCommand(model, sc);
    c.apply();
    assertEquals(2, model.getNumLayers());
  }

  @Test
  public void addCopyCommand() throws IOException {
    c = new AddCopyCommand(model);
    c.apply();
    assertEquals(2, model.getNumLayers());
  }

  @Test
  public void addFromImageCommand() throws IOException {
    model = new LayeredImageEditorImpl(new ImageFromPPM().create("res/Koala.ppm"));
    s = "ppm res/Koala.ppm";
    sc = new Scanner(new StringReader(s));
    c = new AddFromImageCommand(model, sc);
    c.apply();
    assertEquals(2, model.getNumLayers());
  }

  @Test (expected = IllegalArgumentException.class)
  public void blurSepiaGreyscaleSharpenCommand() throws IOException {
    s = "1";
    sc = new Scanner(new StringReader(s));
    c = new SepiaCommand(model, sc);
    c.apply();
  }

  @Test (expected = IllegalArgumentException.class)
  public void exportAllCommand() throws IOException {
    s = "blah somename";
    sc = new Scanner(new StringReader(s));
    c = new ExportAllCommand(model, sc);
    c.apply();
  }

  @Test (expected = IllegalArgumentException.class)
  public void exportCommand() throws IOException {
    s = "blah somename";
    sc = new Scanner(new StringReader(s));
    c = new ExportCommand(model, sc);
    c.apply();
  }

  @Test
  public void makeCommand() throws IOException {
    s = "invisible 0";
    sc = new Scanner(new StringReader(s));
    c = new MakeCommand(model, sc);
    c.apply();
    assertEquals(false, model.visible(0));
  }

  @Test (expected = IllegalStateException.class)
  public void removeCommand() throws IOException {
    s = "0";
    sc = new Scanner(new StringReader(s));
    c = new RemoveCommand(model, sc);
    c.apply();
  }

  @Test
  public void removeCommandValid() throws IOException {
    s = "0";
    sc = new Scanner(new StringReader(s));
    c = new RemoveCommand(model, sc);
    model.addLayer();
    c.apply();
    assertEquals(1, model.getNumLayers());
  }
}
