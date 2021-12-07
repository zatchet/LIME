package view;

import controller.FileUtil;
import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

public class GraphicalView extends JFrame implements InteractiveView {

  //private JPanel topPanel;
  private final JButton saveButton;
  private final JButton loadButton;
  private final JButton executeButton;
  private final JButton checkerButton;
  private final JButton addCheckButton;


  private final JButton blurButton;
  private final JButton sharpenButton;
  private final JButton sepiaButton;
  private final JButton greyButton;
  private final JButton revertButton;
  private final JButton addCopyButton;
  private final JButton addImageButton;
  private final JButton visibleButton;
  private final JButton invisibleButton;
  private final JButton removeButton;
  private final JLabel imageLabel;

  private final JMenuItem loadMenu;
  private final JMenuItem saveMenu;
  private final JMenuItem blurMenu;
  private final JMenuItem sharpenMenu;
  private final JMenuItem sepiaMenu;
  private final JMenuItem greyMenu;
  private final JMenuItem revertMenu;
  private final JMenuItem addCopyMenu;
  private final JMenuItem addImageMenu;
  private final JMenuItem visibleMenu;
  private final JMenuItem invisibleMenu;
  private final JMenuItem removeMenu;
  private final JMenuItem executeMenu;
  private final JMenuItem checkerMenu;
  private final JMenuItem addCheckMenu;

  private final JLabel messageLabel;

  public GraphicalView() {
    super();

    //Set up main panel
    setTitle("LIME");
    setSize(1050, 1000);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    setLayout(new BorderLayout());

    loadButton = new JButton("Load");
    saveButton = new JButton("Save");
    executeButton = new JButton("Execute");
    addCopyButton = new JButton("Add copy");
    addImageButton = new JButton("Add from image");
    checkerButton = new JButton("Create checkerboard");
    addCheckButton = new JButton("Add checkerboard");

    JPanel topPanel = new JPanel();
    topPanel.setLayout(new FlowLayout());
    topPanel.add(loadButton);
    topPanel.add(saveButton);
    topPanel.add(executeButton);
    topPanel.add(addImageButton);
    topPanel.add(addCopyButton);
    topPanel.add(checkerButton);
    topPanel.add(addCheckButton);

    add(topPanel, BorderLayout.NORTH);

    blurButton = new JButton("Blur");
    sepiaButton = new JButton("Sepia");
    greyButton = new JButton("Greyscale");
    sharpenButton = new JButton("Sharpen");
    revertButton = new JButton("Revert");

    invisibleButton = new JButton("Make invisible");
    visibleButton = new JButton("Make visible");
    removeButton = new JButton("Remove");

    JPanel operationPanel = new JPanel();
    operationPanel.setLayout(new FlowLayout());
    operationPanel.add(blurButton);
    operationPanel.add(sepiaButton);
    operationPanel.add(sharpenButton);
    operationPanel.add(greyButton);
    operationPanel.add(removeButton);
    operationPanel.add(visibleButton);
    operationPanel.add(invisibleButton);
    operationPanel.add(revertButton);

    JPanel bottomPanel = new JPanel();
    bottomPanel.setLayout(new BorderLayout());

    messageLabel = new JLabel("Currently viewing and operating on layer" + 10 + "of" + 10);
    messageLabel.setText("");
    messageLabel.setVerticalAlignment(JLabel.CENTER);
    messageLabel.setHorizontalAlignment(JLabel.CENTER);

    bottomPanel.add(operationPanel, BorderLayout.NORTH);
    bottomPanel.add(messageLabel, BorderLayout.SOUTH);

    add(bottomPanel, BorderLayout.SOUTH);

    imageLabel = new JLabel();
    imageLabel.setIcon(new ImageIcon("pleaseUpload.png"));
    imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
    imageLabel.setVerticalAlignment(SwingConstants.CENTER);
    imageLabel.setVisible(true);

    //menu
    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("Menu");
    menu.setMnemonic(KeyEvent.VK_A);
    loadMenu = new JMenuItem("load");
    saveMenu = new JMenuItem("save");
    blurMenu = new JMenuItem("blur");
    sharpenMenu = new JMenuItem("sharpen");
    sepiaMenu = new JMenuItem("sepia");
    greyMenu = new JMenuItem("greyscale");
    revertMenu = new JMenuItem("revert");

    menu.addSeparator();
    JMenu addLayerMenu = new JMenu("add layer");
    addCopyMenu = new JMenuItem("copy of top");
    addImageMenu = new JMenuItem("from image");
    addCheckMenu = new JMenuItem("from checkerboard");
    addLayerMenu.add(addCopyMenu);
    addLayerMenu.add(addImageMenu);
    addLayerMenu.add(addCheckMenu);

    visibleMenu = new JMenuItem("make layer visible");
    invisibleMenu = new JMenuItem("make layer invisible");
    removeMenu = new JMenuItem("remove layer");
    executeMenu = new JMenuItem("execute from script");
    checkerMenu = new JMenuItem("Create checkerboard");

    menu.add(loadMenu);
    menu.add(saveMenu);
    menu.add(blurMenu);
    menu.add(sharpenMenu);
    menu.add(sepiaMenu);
    menu.add(greyMenu);
    menu.add(revertMenu);
    menu.add(addLayerMenu);
    menu.add(visibleMenu);
    menu.add(invisibleMenu);
    menu.add(removeMenu);
    menu.add(executeMenu);
    menu.add(checkerMenu);
    menuBar.add(menu);
    setJMenuBar(menuBar);

    JScrollPane imagePane = new JScrollPane(imageLabel);
    add(imagePane, BorderLayout.CENTER);

    setActionCommands();

    setFocusable(true);
    requestFocus();
    pack();
  }

  @Override
  public void registerViewEventListener(ActionListener a) {
    saveButton.addActionListener(a);
    loadButton.addActionListener(a);
    executeButton.addActionListener(a);
    blurButton.addActionListener(a);
    sharpenButton.addActionListener(a);
    sepiaButton.addActionListener(a);
    greyButton.addActionListener(a);
    addImageButton.addActionListener(a);
    addCopyButton.addActionListener(a);
    removeButton.addActionListener(a);
    visibleButton.addActionListener(a);
    invisibleButton.addActionListener(a);
    revertButton.addActionListener(a);
    checkerButton.addActionListener(a);
    addCheckButton.addActionListener(a);

    loadMenu.addActionListener(a);
    saveMenu.addActionListener(a);
    blurMenu.addActionListener(a);
    sharpenMenu.addActionListener(a);
    sepiaMenu.addActionListener(a);
    greyMenu.addActionListener(a);
    revertMenu.addActionListener(a);
    addCopyMenu.addActionListener(a);
    addImageMenu.addActionListener(a);
    visibleMenu.addActionListener(a);
    invisibleMenu.addActionListener(a);
    removeMenu.addActionListener(a);
    executeMenu.addActionListener(a);
    checkerMenu.addActionListener(a);
    addCheckMenu.addActionListener(a);
  }

  //sets all the action commands
  private void setActionCommands() {
    saveButton.setActionCommand("save");
    saveMenu.setActionCommand("save");
    loadButton.setActionCommand("load");
    loadMenu.setActionCommand("load");
    executeButton.setActionCommand("execute");
    executeMenu.setActionCommand("execute");
    blurButton.setActionCommand("blur");
    blurMenu.setActionCommand("blur");
    sharpenButton.setActionCommand("sharpen");
    sharpenMenu.setActionCommand("sharpen");
    sepiaButton.setActionCommand("sepia");
    sepiaMenu.setActionCommand("sepia");
    greyButton.setActionCommand("grey");

    greyMenu.setActionCommand("grey");
    addImageButton.setActionCommand("addImage");
    addImageMenu.setActionCommand("addImage");
    addCopyButton.setActionCommand("addCopy");
    addCopyMenu.setActionCommand("addCopy");
    removeButton.setActionCommand("remove");
    removeMenu.setActionCommand("remove");
    visibleButton.setActionCommand("visible");
    visibleMenu.setActionCommand("visible");
    invisibleButton.setActionCommand("invisible");
    invisibleMenu.setActionCommand("invisible");
    revertButton.setActionCommand("revert");
    revertMenu.setActionCommand("revert");
    checkerButton.setActionCommand("checker");
    checkerMenu.setActionCommand("checker");
    addCheckButton.setActionCommand("addCheck");
    addCheckMenu.setActionCommand("addCheck");
  }

  @Override
  public File chooseFile() {
    final JFileChooser fchooser = new JFileChooser(".");
    fchooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    int retvalue = fchooser.showOpenDialog(GraphicalView.this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      return fchooser.getSelectedFile();
    }
    return null;
  }

  @Override
  public void refreshImage(BufferedImage buff) {
    imageLabel.setIcon(new ImageIcon(buff));
    imageLabel.repaint();
  }

  @Override
  public void renderMessage(String message) {
    JOptionPane.showMessageDialog(this, message);
  }

  @Override
  public String askForExt() {
    Object[] choices = FileUtil.getSupportedFileTypes().toArray();
    String s = (String) JOptionPane.showInputDialog(
        null,
        "Select file type",
        "Select file type",
        JOptionPane.PLAIN_MESSAGE,
        null,
        choices,
        null);
    return s;
  }

  @Override
  public String askForProjectName() {
    return JOptionPane.showInputDialog("What do you want to call this project?");
  }

  @Override
  public int askForSaveMethod() {
    Object[] options = {"top most visible layer only", "All layers"};
    int n = JOptionPane.showOptionDialog(this,//parent container of JOptionPane
        "", "Select save method",
        JOptionPane.YES_NO_CANCEL_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null,//do not use a custom Icon
        options,//the titles of buttons
        null);//default button title
    return n;
  }

  @Override
  public int askForLayer(int numLayers) {

    Object[] choices = new Object[numLayers];
    for (int i = 0; i < choices.length; i++) {
      choices[i] = i + 1;
    }
    int n = (Integer) JOptionPane.showInputDialog(
        null,
        "Select layer you wish to operate on",
        "Select layer",
        JOptionPane.PLAIN_MESSAGE,
        null,
        choices,
        null);
    return n - 1;
  }

  @Override
  public int[] getCheckDimensions() {
    JOptionPane pane = new JOptionPane("Enter dimensions");
    pane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    JSpinner spinner1 = new JSpinner(new SpinnerNumberModel(10, 0, null, 1));
    pane.add(spinner1);
    JSpinner spinner2 = new JSpinner(new SpinnerNumberModel(10, 0, null, 1));
    pane.add(spinner2);
    JDialog dialog = pane.createDialog(pane, "Enter dimensions");
    dialog.show();
    if (pane.getValue() == null) {
      return null;
    }
    return new int[]{(int) spinner1.getValue(), (int) spinner2.getValue()};
  }

  @Override
  public void refreshMessageBar(int x, int y) {
    messageLabel.setText("Currently viewing and operating on layer " + x + " of " + y);
  }
}
