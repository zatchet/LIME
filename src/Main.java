import controller.GraphicController;
import controller.ImageEditorController;
import controller.SimpleController;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ModuleLayer.Controller;
import java.util.Scanner;
import view.GraphicalView;

public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    if (sc.hasNext()) {
      switch (sc.next()) {
        case "-script":
          String path = sc.next();
          try {
            new SimpleController(new FileReader(path), System.out).run();
          } catch (IOException e) {
            System.out.println(e.getMessage());
          }
          break;
        case "-text":
          System.out.println("Begin!");
          try {
            new SimpleController(new InputStreamReader(System.in), System.out).run();
          } catch (IOException e) {
            System.out.println(e.getMessage());
          }
        case "-interactive":
          GraphicalView view = new GraphicalView();
          view.setVisible(true);
          new GraphicController(view).run();
          break;
        default:
          System.out.println("Unknown command");
      }
    }
  }
}