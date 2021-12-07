package controller.commands;

import controller.FileUtil;
import controller.LayeredImageExporter;
import controller.LayeredImageExporterImpl;
import java.io.IOException;
import java.util.Scanner;
import model.LayeredImageEditor;

/**
 * A command object for exporting the top visible of the project. Contains just one single apply()
 * method.
 */
public class ExportCommand implements CommandOperation {

  private final LayeredImageEditor model;
  private final Scanner scan;

  /**
   * Creates an export command object.
   *
   * @param model the model.
   * @param scan  the scanner.
   */
  public ExportCommand(LayeredImageEditor model, Scanner scan) {
    this.model = model;
    this.scan = scan;
  }

  @Override
  public void apply() throws IOException {
    if (scan.hasNext()) {
      String s1 = scan.next();
      if (scan.hasNext()) {
        String s2 = scan.next();
        processExport(s1, s2);
      }
    }
  }

  private void processExport(String fileType, String fileName)  {
    LayeredImageExporter exporter = new LayeredImageExporterImpl(model);
    FileUtil.checkNotSupported(fileType);
    FileUtil.checkNotMatches(fileType, fileName);
    exporter.exportTop(fileName, fileType);
  }
}
