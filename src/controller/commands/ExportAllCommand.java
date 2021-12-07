package controller.commands;

import controller.FileUtil;
import controller.LayeredImageExporter;
import controller.LayeredImageExporterImpl;
import java.io.IOException;
import java.util.Scanner;
import model.LayeredImageEditor;

/**
 * A command object for exporting all layers of the project. Contains just one single apply()
 * method.
 */
public class ExportAllCommand implements CommandOperation {

  private final LayeredImageEditor model;
  private final Scanner scan;

  /**
   * Creates an export all command object.
   *
   * @param model the model.
   * @param scan  the scanner.
   */
  public ExportAllCommand(LayeredImageEditor model, Scanner scan) {
    this.model = model;
    this.scan = scan;
  }

  @Override
  public void apply() throws IOException {
    if (scan.hasNext()) {
      String s1 = scan.next();
      if (scan.hasNext()) {
        String s2 = scan.next();
        processExportAll(s1, s2);
      }
    }
  }

  private void processExportAll(String fileType, String fileName) throws IOException {
    FileUtil.checkNotSupported(fileType);
    LayeredImageExporter exporter = new LayeredImageExporterImpl(model);
    exporter.exportAll(fileName, fileType);
  }
}
