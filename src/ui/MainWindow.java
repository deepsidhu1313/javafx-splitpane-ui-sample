/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;
import org.controlsfx.dialog.Dialog;
import org.controlsfx.dialog.Dialogs;


public class MainWindow extends Application implements Runnable {

//    static CustomTree AllNodes = new CustomTree();
    public static Tab tab21, tab22, tab23, tab24, schedulerSettingsTab, chunkCalcTab;
    static Tab tab41 = new Tab("Files");
    static TabPane centerTabPane;
    public static Tab textTab[] = new Tab[100];
    public static ProgressIndicator pi = new ProgressBar();
    public static ArrayList<String> projectLibs = new ArrayList<>();
    public static ArrayList<String> projectAttachments = new ArrayList<>();
    public static ArrayList<String> projectArgs = new ArrayList<>();
    public static String projectMain = "";
    public static ArrayList<String> jvmargs = new ArrayList<>();

    public static String selectedProject = "", lastSelectedProject = "";
    public static ArrayList<String> listOpenedFiles = new ArrayList<>();
    public static ArrayList<String> listOpenedProjects = new ArrayList<>();
   
    static SyntaxTextArea ta[] = new SyntaxTextArea[1000];
    
    public static TreeView tree = new TreeView();
    
    public static CheckBoxTreeItem[] root = new CheckBoxTreeItem[1000];
    public static boolean ideStarted = false;
    static int tabcounter = 0;
    public static BorderPane bp;
    public static HBox statusBar;

    Stage stage = new Stage();
    public static SplitPane LeftSplitPane;
    public static int selectedtab = 0;
    public static Tab outputTab[] = new Tab[1000];
    public static ListView outputTabTextArea[] = new ListView[1000];
    public static ObservableList<String> outputTabText[] = new ObservableList[1000];
    public static TextArea consoleArea = new TextArea();
    public static TabPane bottomTabPane;
    public static Tab tab32;
    public static int simdbcounter[] = new int[1000];

    @Override
    public void start(final Stage stage) throws Exception {
        loadOpenProjects();
        this.stage = stage;
        this.stage.setTitle("IDE");
        
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        
//VBox vb1= new VBox();
        GridPane vb1 = new GridPane();

        MenuBar menuBar = new MenuBar();

        // --- Menu File
        Menu menuFile = new Menu("File");

        MenuItem newProject = new MenuItem("\tNew Project\t\t");
        newProject.setOnAction(new EventHandler() {
            public void handle(Event t) {
                newProject();
            }
        });
        menuFile.getItems().add(newProject);

        MenuItem newFile = new MenuItem("\tNew File\t\t");
        newFile.setOnAction(new EventHandler() {
            public void handle(Event t) {
                doNew();
            }
        });
        menuFile.getItems().add(newFile);

        MenuItem openProject = new MenuItem("\tOpen Project\t\t");
        openProject.setOnAction(new EventHandler() {
            public void handle(Event t) {
                openProject();
            }
        });
        menuFile.getItems().add(openProject);

        MenuItem open = new MenuItem("\tOpen\t\t");
        open.setOnAction(new EventHandler() {
            public void handle(Event t) {
                doOpen();
            }
        });
        menuFile.getItems().add(open);

        MenuItem sync = new MenuItem("\tSync\t\t");
        sync.setOnAction(new EventHandler() {
            public void handle(Event t) {

                synchroniseUi();
            }
        });
        menuFile.getItems().add(sync);

        MenuItem save = new MenuItem("\tSave\t\t");
        save.setOnAction(new EventHandler() {
            public void handle(Event t) {
                doSave();
            }
        });
        menuFile.getItems().add(save);

        MenuItem saveAs = new MenuItem("\tSave As\t\t");
        saveAs.setOnAction(new EventHandler() {
            public void handle(Event t) {
                doSaveAs();
            }
        });
        menuFile.getItems().add(saveAs);

        MenuItem exit = new MenuItem("\tExit\t\t");
        exit.setOnAction(new EventHandler() {
            public void handle(Event t) {
                doExit(t);

            }
        });
        menuFile.getItems().add(exit);

// --- Menu Edit
        Menu menuEdit = new Menu("Edit");

        // --- Menu View
        Menu menuTools = new Menu("Tools");
        MenuItem nodeMan = new MenuItem("\tNode Manager\t\t");
        nodeMan.setOnAction(new EventHandler() {
            public void handle(Event t) {
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
                   }
                });

            }
        });
        menuTools.getItems().add(nodeMan);
        MenuItem cp = new MenuItem("\tControl Panel\t\t");
        cp.setOnAction(new EventHandler() {
            public void handle(Event t) {
                Platform.runLater(new Runnable() {

                    @Override
                    public void run() {
         //               controlpanel.CPanel cpan = new CPanel();
            //            cpan.start(new Stage());
                    }
                });

            }
        });
        menuTools.getItems().add(cp);

        Menu menuRun = new Menu("Run");

        MenuItem itemExec = new MenuItem("Execute");
        itemExec.setOnAction(new EventHandler() {
            public void handle(Event t) {
                  }
        });
        menuRun.getItems().addAll(itemExec);
  
        Menu menuScheduling = new Menu("Scheduling");
        Menu menuScheduler = new Menu("Scheduler");
        //ToggleGroup schgrp= new  ToggleGroup();
        CheckMenuItem schedulingtech[] = new CheckMenuItem[5];
        schedulingtech[0] = new CheckMenuItem("Chunk");
        schedulingtech[0].setId("000");
        schedulingtech[0].setSelected(true);
        schedulingtech[1] = new CheckMenuItem("GSS");
        schedulingtech[1].setId("001");
        schedulingtech[2] = new CheckMenuItem("Factoring");
        schedulingtech[2].setId("002");
        schedulingtech[3] = new CheckMenuItem("TSS");
        schedulingtech[3].setId("003");
        schedulingtech[4] = new CheckMenuItem("QSS");
        schedulingtech[4].setId("004");

        EventHandler eh = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (event.getSource() instanceof CheckMenuItem) {
                    CheckMenuItem chk = (CheckMenuItem) event.getSource();
                    System.out.println("Action performed on checkbox " + chk.getText());
                    int i = Integer.parseInt(chk.getId());
                    if (chk.isSelected()) {
                        for (int j = 0; j <= 4; j++) {
                            if (Integer.parseInt(schedulingtech[j].getId()) == i) {
                                schedulingtech[j].setSelected(true);
                            } else {
                                schedulingtech[j].setSelected(false);

                            }

                        }
                    }
                }
            }
        };

        for (int i = 0; i <= 4; i++) {
            if (i == 0) { // Use your variable to check the right option
                schedulingtech[i].isSelected();
            }
            schedulingtech[i].setOnAction(eh);
            menuScheduler.getItems().addAll(schedulingtech[i]);

        }
        menuScheduling.getItems().add(menuScheduler);
        menuBar.getMenus().addAll(menuFile, menuEdit, menuTools, menuRun,  menuScheduling);

        //Setup Center and Right
        // TabPaneWrapper wrapper = new TabPaneWrapper(Orientation.HORIZONTAL, .9);
        centerTabPane = new TabPane();

        Tab tab11 = new Tab("Start Tab");
        tab11.setContent(new TextArea("\tWelCome To FrameWork For Parallel Programming !!\n\t This is a Sample Tab !!"));
        tab11.setId("7675586");
        tab11.setTooltip(new Tooltip("Start Tab"));
        centerTabPane.getTabs().addAll(tab11);
        this.stage.setTitle(centerTabPane.getSelectionModel().getSelectedItem().getText());

        TabPane rightTabPane = new TabPane();

        tab21 = new Tab("Livenodes");
        tab21.setClosable(false);
        tab21.setContent(new TextArea());

        tab22 = new Tab("All");
        tab22.setClosable(false);
        // tab22.setContent(new TextArea());
        tab23 = new Tab("Efficent");
        tab23.setClosable(false);
        tab23.setContent(new TextArea());

        tab24 = new Tab("Offline");
        tab24.setClosable(false);
        tab24.setContent(new TextArea());

        // AllNodes.setSimpleRoot("All Nodes");
        rightTabPane.getTabs().addAll(tab21, tab22);
        SplitPane.setResizableWithParent(rightTabPane, false);
        rightTabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override
            public void changed(ObservableValue<? extends Tab> tab, Tab oldTab, final Tab newTab) {

                synchroniseUi();
            }
        });
       //wrapper.addNodes(centerTabPane);

        //Add bottom
        bottomTabPane = new TabPane();

        Tab tab31 = new Tab("Console");
        tab31.setClosable(false);
        tab31.setContent(consoleArea);

        tab32 = new Tab("Results");
        tab32.setClosable(false);
        // tab32.setContent(new TextArea());

        Tab tab33 = new Tab("All results");
        tab33.setClosable(false);

        Tab tab34 = new Tab("Data");
        tab34.setClosable(false);
        tab34.setContent(new TextArea());

        bottomTabPane.getTabs().addAll(tab31, tab32);
        bottomTabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override
            public void changed(ObservableValue<? extends Tab> tab, Tab oldTab, final Tab newTab) {

                synchroniseUi();
            }
        });
        SplitPane centerSplitPane = new SplitPane();
        centerSplitPane.setDividerPositions(0.7f);
        centerSplitPane.getItems().addAll(centerTabPane, bottomTabPane);
        centerSplitPane.setOrientation(Orientation.VERTICAL);
        //Add left
        LeftSplitPane = new SplitPane();
        //VBox LeftVbox= new VBox(10);
        TabPane leftTabPane = new TabPane();

        tab41.setClosable(false);
        tab41.setContent(new TextArea());

        leftTabPane.getTabs().addAll(tab41);
        TabPaneWrapper wrapperleft = new TabPaneWrapper(Orientation.HORIZONTAL, .1);

        LeftSplitPane.getItems().add(leftTabPane);
        LeftSplitPane.getItems().add(tree);

        centerTabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override
            public void changed(ObservableValue<? extends Tab> tab, Tab oldTab, final Tab newTab) {
                if (newTab.getText() == null) {
                    stage.setTitle("");

                } else {
                    stage.setTitle("Parallel Framework - " + newTab.getText());
                    selectedtab = Integer.parseInt(newTab.getId());
                    if (newTab.getTooltip().getText().contains(".java") || newTab.getTooltip().getText().contains(".JAVA")) {
                        
                        
                        tree = new TreeView(root[selectedtab]);
                        tree.setEditable(true);
                        tree.setCellFactory(CheckBoxTreeCell.<String>forTreeView());
                        String tempFileLoc = newTab.getTooltip().getText();
                        String tempFileLoc2 = tempFileLoc.substring(tempFileLoc.indexOf("workspace") + 9);
                        Platform.runLater(() -> {
                            //LeftSplitPane.getItems().remove(1);
                            LeftSplitPane.getItems().set(1, tree);
                            
                            synchroniseUi();
                        });
                    }
                    selectedProject = FilesTree.getProjectName(new File(newTab.getTooltip().getText()));
                    if (!lastSelectedProject.trim().equalsIgnoreCase(selectedProject.trim())) {
                        loadManifest(new File("workspace/" + selectedProject));
                    }

                }

            }
        });

        LeftSplitPane.setOrientation(Orientation.VERTICAL);
        wrapperleft.addNodes(LeftSplitPane);
        LeftSplitPane.prefHeight(100);
        LeftSplitPane.prefWidth(100);
        //wrapperleft.addNodes(LeftSplitPane, wrapperBottom.getNode());

        // wrapperleft.getNode()
        bp = new BorderPane();
        bp.setTop(menuBar);
        //bp.setCenter(centerTabPane);
        //bp.setBottom(wrapperBottom.getNode());
        //bp.setLeft(wrapperleft.getNode());
        pi.maxHeight(10);
        statusBar = new HBox();
        statusBar.setSpacing(5);
        statusBar.maxHeight(10);

        statusBar.setAlignment(Pos.CENTER_RIGHT);
        statusBar.getChildren().add(pi);
        // bp.setBottom(statusBar);
        SplitPane t1 = new SplitPane();
        t1.prefHeight(0);
        t1.maxWidth(0);

        TabPaneWrapper wrapperRight = new TabPaneWrapper(Orientation.VERTICAL, .7);
        rightTabPane.prefWidth(100);

        schedulerSettingsTab = new Tab("Scheduler Settings");
        schedulerSettingsTab.setClosable(false);

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(15);

        Text title = new Text("Scheduler Settings");
        title.setFont(Font.font("", FontWeight.BOLD, 16));

        vbox.getChildren().add(title);

        Text tss = new Text("Trapezoid Self Scheduling: ");
        tss.setFont(Font.font("", FontWeight.BOLD, 12));
        // tss.setPadding(new Insets(15, 12, 15, 12));

        HBox hbox2 = new HBox();
        hbox2.setSpacing(20);
        hbox2.setAlignment(Pos.CENTER);
        Text fcfText = new Text("First Chunk Size");
        fcfText.setFont(Font.font(12));

        TextField fcfTField = new TextField("");
        hbox2.getChildren().addAll(fcfText, fcfTField);

        HBox hbox3 = new HBox();
        hbox3.setSpacing(20);
        hbox3.setAlignment(Pos.CENTER);
        Text lcfText = new Text("Last Chunk Size");
        lcfText.setFont(Font.font(12));

        TextField lcfTField = new TextField("" );
        hbox3.getChildren().addAll(lcfText, lcfTField);

        Text qss = new Text("Quadretic Self Scheduling: ");
        qss.setFont(Font.font("", FontWeight.BOLD, 12));
        // tss.setPadding(new Insets(15, 12, 15, 12));

        HBox hbox4 = new HBox();
        hbox4.setSpacing(20);
        hbox4.setAlignment(Pos.CENTER);
        Text deltaText = new Text("Delta");
        deltaText.setFont(Font.font(12));

        TextField deltaTField = new TextField("");
        hbox4.getChildren().addAll(deltaText, deltaTField);

        HBox hbox5 = new HBox();
        hbox5.setSpacing(20);
        hbox5.setAlignment(Pos.CENTER);
        Text qlcfText = new Text("Last Chunk Size");
        qlcfText.setFont(Font.font(12));

        TextField qlcfTField = new TextField("" );
        hbox5.getChildren().addAll(qlcfText, qlcfTField);

        Button btn = new Button();
        btn.setText("Apply");
        btn.setOnAction((ActionEvent event) -> {
       

        });

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(15, 12, 15, 12));
        hbox.setSpacing(50);
        //hbox.setStyle("-fx-background-color: #336699;");
        hbox.setAlignment(Pos.CENTER);

        hbox.getChildren().addAll(btn);

        vbox.getChildren().addAll(tss, hbox2, hbox3, qss, hbox4, hbox5, hbox);
        ScrollPane sssp = new ScrollPane(vbox);
        schedulerSettingsTab.setContent(sssp);

        chunkCalcTab = new Tab("Chunk Calculator");
        chunkCalcTab.setClosable(false);

        TabPane rightBottomTabPane = new TabPane();
        rightBottomTabPane.getTabs().addAll(schedulerSettingsTab, chunkCalcTab);

        SplitPane.setResizableWithParent(rightBottomTabPane, false);
        rightBottomTabPane.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() {
            @Override
            public void changed(ObservableValue<? extends Tab> tab, Tab oldTab, final Tab newTab) {

                synchroniseUi();
            }
        });

        SplitPane rsplitpane = new SplitPane();
        rsplitpane.setOrientation(Orientation.VERTICAL);
        rsplitpane.setDividerPositions(0.6);
        rsplitpane.getItems().add(rightTabPane);
        rsplitpane.getItems().add(rightBottomTabPane);
        wrapperRight.addNodes(rsplitpane);

        SplitPane bigTabPane = new SplitPane();

        bigTabPane.getItems().add(LeftSplitPane);
        // bigTabPane.getItems().add(centerTabPane);
        bigTabPane.getItems().add(centerSplitPane);
        bigTabPane.getItems().add(rsplitpane);
        bigTabPane.setDividerPositions(0.1f, 0.8f);
        bp.setCenter(bigTabPane);
        //bp.setRight(wrapperRight.getNode());
        Scene myScene = new Scene(bp);
        //    myScene.fillProperty();
     
        this.stage.setScene(myScene);
        this.stage.sizeToScene();
        this.stage.setWidth(primaryScreenBounds.getWidth() - 100);
        this.stage.setHeight(primaryScreenBounds.getHeight() - 100);
        this.stage.setX(100);
        this.stage.setY(100);
        this.stage.show();
        loadOpenFiles();
     
       
        ideStarted = true;
        this.stage.setOnCloseRequest(new EventHandler() {
            public void handle(Event t) {
                doExit(t);
            }
        });

    }

    /*public Tab generateTab(String name) {
     Tab result = new Tab(name);
     BorderPane content = new BorderPane();
     TextArea text = new TextArea();
     content.setCenter(text);
     result.setContent(content);
     return result;
     }*/
    public static void addoutput(int pid, String content) {
        //     outputTabTextArea[pid].appendText(content);
        //    outputTabTextArea[pid].positionCaret(outputTabTextArea[pid].getLength());
    }

    public void synchroniseUi() {
        Thread t4 = new Thread(new FilesTree());
        t4.start();

        Platform.runLater(new Runnable() {

            @Override
            public void run() {

                tab41.setContent(FilesTree.tv);
                // tab41.setContent(FilesTree.tv);
                //           settings.outPrintln("" + Scanner.NetScanner.livehosts);

             
            }
        });

    }

    

   
    public void newProject() {

        synchroniseUi();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("New  Project: Select Destination Directory");
        String initialDir = "workspace";
        fileChooser.setInitialDirectory(new File(initialDir));
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("All Files", "*.*"));
        File selectedFile = fileChooser.showSaveDialog(stage);
        if (selectedFile != null) {
            selectedFile.mkdir();
            listOpenedProjects.add(selectedFile.getAbsolutePath());
            boolean mkdir = new File(selectedFile.getAbsolutePath() + "/libs").mkdirs();
            boolean mkdir2 = new File(selectedFile.getAbsolutePath() + "/src").mkdirs();
           String content = "<PROJECT>" + selectedFile.getName() + "</PROJECT>\n<MAIN></MAIN>\n<LIB></LIB>\n<ARGS></ARGS>";
            try {
                write(new File(selectedFile.getAbsolutePath() + "/manifest.xml"), content);

                addTab(new File(selectedFile.getAbsolutePath() + "/manifest.xml"), tabcounter);
            } catch (IOException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(selectedFile.getAbsolutePath() + " added to Open Project List");
        }
    }

    public void openProject() {

        synchroniseUi();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Project: Select Manifest (Manifest.xml) File");
        String initialDir = "workspace";
        fileChooser.setInitialDirectory(new File(initialDir));
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("XML Manifest", "*.xml"));
        File selectedFile = fileChooser.showSaveDialog(stage);
        if (selectedFile != null) {
            listOpenedProjects.add(selectedFile.getParentFile().getAbsolutePath());
            System.out.println(selectedFile.getParentFile().getAbsolutePath() + " added to Open Project List");
            addTab(selectedFile, tabcounter);
        }
    }

  
    public static void loadManifest(File f2) {
        Thread lmfThread = new Thread(new Runnable() {

            @Override
            public void run() {
                FileInputStream fstream = null;
                try {
                    String content = "";
                    File f = new File(f2.getAbsolutePath() + "/manifest.xml");
                    if (!f.exists()) {
                        String content2 = "<PROJECT>" + f2.getName() + "</PROJECT>\n<MAIN></MAIN>\n<LIB></LIB>\n<ARGS></ARGS>\n<JVMARGS></JVMARGS>\n<ATTCH></ATTCH>\n<OUTPUTFREQUENCY></OUTPUTFREQUENCY>";
                        write(f, content2);

                    }
                    fstream = new FileInputStream(f);
                    BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
                    String strLine;
                    //Read File Line By Line
                    projectLibs.clear();
                    projectAttachments.clear();
                    projectArgs.clear();
                    jvmargs.clear();
                    while ((strLine = br.readLine()) != null) {
                        // Print the content on the console

                        System.out.println(strLine);
                        if (strLine.contains("<LIB>")) {
                            String lib = strLine.substring(strLine.indexOf("<LIB>") + 5, strLine.indexOf("</LIB>"));
                            if (!(lib.trim().equalsIgnoreCase(""))) {
                                projectLibs.add(lib);
                            }
                        }
                        if (strLine.contains("<ARGS>")) {
                            projectArgs.add(strLine.substring(strLine.indexOf("<ARGS>") + 6, strLine.indexOf("</ARGS>")));
                        }

                        if (strLine.contains("<JVMARGS>")) {
                            String tmp = strLine.substring(strLine.indexOf("<JVMARGS>") + 9, strLine.indexOf("</JVMARGS>"));
                            jvmargs.add(tmp);
                        }

                        if (strLine.contains("<ATTCH>")) {
                            String att = strLine.substring(strLine.indexOf("<ATTCH>") + 7, strLine.indexOf("</ATTCH>"));
                            if (!(att.trim().equalsIgnoreCase(""))) {
                                projectAttachments.add(att);
                            }
                        }

                        if (strLine.contains("<MAIN>")) {
                            projectMain = " " + (strLine.substring(strLine.indexOf("<MAIN>") + 6, strLine.indexOf("</MAIN>")));
                        }

                    }   //Close the input stream
                    br.close();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        fstream.close();
                    } catch (IOException ex) {
                        Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        lmfThread.start();

    }

  

    public static String read(File f) throws IOException {
        String content = "";
        if (!f.exists()) {
            return "";
        }
        FileInputStream fstream = new FileInputStream(f);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;

//Read File Line By Line
        while ((strLine = br.readLine()) != null) {
            // Print the content on the console
            content += strLine;
            content += "\n";
            System.out.println(strLine);
        }

//Close the input stream
        br.close();
        return content;
    }

    public static void loadOpenProjects() throws IOException {
        String content = "";
        File f = new File("appdb/projects.xml");
        if (!f.exists()) {
f.getParentFile().mkdirs();
            f.createNewFile();
        }
        FileInputStream fstream = new FileInputStream(f);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;

//Read File Line By Line
        while ((strLine = br.readLine()) != null) {
            // Print the content on the console
            File tf = new File(strLine);
            if (tf.exists()) {
                listOpenedProjects.add(strLine);
                System.out.println(strLine);
            }
        }

//Close the input stream
        br.close();
    }

    public static void saveOpenProjects() {
        try {
            try (FileWriter fw = new FileWriter("appdb/projects.xml"); PrintWriter pw = new PrintWriter(fw)) {
                listOpenedProjects.stream().forEach((get) -> {
                    pw.println(get);
                });
            }
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

   
    public static void loadOpenFiles() throws IOException {
        String content = "";
        File f = new File("appdb/files.xml");
        if (!f.exists()) {
            f.createNewFile();
        }
        FileInputStream fstream = new FileInputStream(f);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;

//Read File Line By Line
        while ((strLine = br.readLine()) != null) {
            // Print the content on the console
            File tf = new File(strLine);
            if (tf.exists() && tf.isFile()) {
                addTab(tf, tabcounter);
                if (!listOpenedFiles.contains(strLine)) {
                    listOpenedFiles.add(strLine);
                }

                System.out.println(strLine);
            }
        }

//Close the input stream
        br.close();
    }

    public static void saveOpenFiles() {
        try {
            if (new File("appdb/files.xml").exists()) {
                new File("appdb/files.xml").delete();
            }
            FileWriter fw = new FileWriter("appdb/files.xml");
            PrintWriter pw = new PrintWriter(fw);
            for (String get : listOpenedFiles) {
                pw.println(get);
                System.out.println("Printing " + get + " to appdb/files.xml");
            }
            pw.close();
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void write(File f, String text) throws IOException {
        try (FileWriter fw = new FileWriter(f);
                PrintWriter pw = new PrintWriter(fw)) {
            pw.print(text);
            pw.close();
            fw.close();
        }

    }

    private void doExit(Event e) {
        e.consume();
        saveOpenFiles();
        saveOpenProjects();
        Action response = Dialogs.create()
                .owner(stage)
                .title("Exit ??")
                .masthead("Do you want to Exit ??")
                .actions(Dialog.ACTION_YES, Dialog.ACTION_NO)
                .showConfirm();
        if (response == Dialog.ACTION_YES) {
            stage.close();

            System.exit(0);

        }

    }

    private void doNew() {
        synchroniseUi();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("New  File: Select Destination Directory");
        String initialDir = "workspace";
        if (selectedProject != null) {
            initialDir += ("/" + selectedProject + "/src");
        }
        File initFile = new File(initialDir);
        if (!initFile.exists()) {
            initFile.mkdirs();
        }
        fileChooser.setInitialDirectory(initFile);
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("All Files", "*.*"),
                new ExtensionFilter("Java Files", "*.java"),
                new ExtensionFilter("SIPS XML MANIFEST Files", "*.xml"));
        File selectedFile = fileChooser.showSaveDialog(stage);
        if (selectedFile != null) {

            if (!selectedFile.exists()) {
                try {
                    selectedFile.createNewFile();
                } catch (IOException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            addTab(selectedFile, tabcounter);

        }

    }

    private void doOpen() {
        {

            synchroniseUi();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open  File");
            fileChooser.setInitialDirectory(new File("workspace"));
            fileChooser.getExtensionFilters().addAll(
                    new ExtensionFilter("All Files", "*.*"),
                    new ExtensionFilter("Java Files", "*.java"),
                    new ExtensionFilter("SIPS XML MANIFEST Files", "*.xml"));
            File selectedFile = fileChooser.showOpenDialog(stage);
            if (selectedFile != null) {
                addTab(selectedFile, tabcounter);
            }

        }
    }

    private boolean doSave() {
        File file2 = new File(textTab[selectedtab].getTooltip().getText());
        if (file2.exists()) {
            file2.delete();
        }
        PrintStream out = null;
        try {
            out = new PrintStream(textTab[selectedtab].getTooltip().getText()); //new AppendFileStream
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        out.print(ta[selectedtab].getText());
        out.close();
        return true;
        // return doSaveAs();
    }

    public static void addTab(File selectedFile, int tabcounter) {
        if (!listOpenedFiles.isEmpty() && listOpenedFiles.contains(selectedFile.getAbsolutePath())) {
            ObservableList<Tab> templs = centerTabPane.getTabs();
            System.out.println("" + templs);
            for (int i = 0; i < templs.size(); i++) {
                System.out.println("" + templs.get(i).getTooltip());
                if ((templs.get(i).getTooltip() != null) && templs.get(i).getTooltip().getText().equalsIgnoreCase(selectedFile.getAbsolutePath())) {
                    final int r = i;
                    Platform.runLater(() -> {
                        centerTabPane.getSelectionModel().select(r);
                    });
                }
            }
        } else {
            textTab[tabcounter] = new Tab(selectedFile.getName());

            textTab[tabcounter].setId("" + tabcounter);
            textTab[tabcounter].setTooltip(new Tooltip(selectedFile.getAbsolutePath()));
            textTab[tabcounter].setOnClosed((Event e) -> {
                listOpenedFiles.clear();
                ObservableList<Tab> tl = centerTabPane.getTabs();
                tl.stream().forEach((tl1) -> {
                    listOpenedFiles.add(tl1.getTooltip().getText());
                });
            });
            listOpenedFiles.add(selectedFile.getAbsolutePath().trim());
            ta[tabcounter] = new SyntaxTextArea();
            String line = null;
            String text = "";
            try {
                String temp = read(selectedFile);
                if (temp != null) {
                    text += temp;
                }
            } catch (IOException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
            ta[tabcounter].setText(text);
            PrintStream out = null;
            try {
                out = new PrintStream(selectedFile.getAbsolutePath()); //new AppendFileStream
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.print(text);
            out.close();

            textTab[tabcounter].setContent(ta[tabcounter].getNode());
            textTab[tabcounter].getStyleClass().add(org.fxmisc.richtext.demo.JavaKeywordsAsync.class.getResource("java-keywords.css").toExternalForm());
            centerTabPane.getTabs().add(textTab[tabcounter]);
            IncrementTabcounter();
        }
    }

    public static void IncrementTabcounter() {
        tabcounter++;
    }

    private boolean doSaveAs() {
        synchroniseUi();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save As File: Select Destination Directory");
        String initialDir = "workspace";
        if (selectedProject != null) {
            initialDir += ("/" + selectedProject);
        }
        fileChooser.setInitialDirectory(new File(initialDir));
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("All Files", "*.*"),
                new ExtensionFilter("Java Files", "*.java"),
                new ExtensionFilter("SIPS XML MANIFEST Files", "*.xml"));
        File selectedFile = fileChooser.showSaveDialog(stage);
        if (selectedFile != null) {

            if (!selectedFile.exists()) {
                try {
                    selectedFile.createNewFile();
                } catch (IOException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                write(selectedFile, ta[selectedtab].getText());
            } catch (IOException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

   
    public static void main(String[] args) {
        MainWindow.launch(args);
    }

    @Override
    public void run() {
        try {

        } catch (Exception ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   public static class TabPaneWrapper {

        SplitPane split;

        public TabPaneWrapper(Orientation o, double splitLocation) {
            split = new SplitPane();

            //Change the CSS (uncomment if using an external css)
            //split.getStylesheets().add("test.css");
            split.setOrientation(o);
            split.setDividerPosition(0, splitLocation);
        }

        public void addNodes(final Node node1, final Node node2) {
            //Add to the split pane
            split.getItems().addAll(node1, node2);
        }

        public void addNodes(final Node node1) {
            //Add to the split pane
            split.getItems().add(node1);
        }

        public Parent getNode() {
            // split.setResizableWithParent(split, Boolean.TRUE);
            return split;
        }

    }

}
