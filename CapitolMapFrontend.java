// --== CS400 Project Two File Header ==--
// Name: Michael Song
// CSL Username: msong
// Email: mmsong@wisc.edu
// Lecture #: <001 @11:00am>
// Notes to Grader: <any optional extra notes to your grader>

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
// import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.NoSuchElementException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import java.util.Iterator;

/**
 * This class provides the user the functionality of the Application interface
 *
 * @author Michael Song
 *
 */
public class CapitolMapFrontend extends Application implements ICapitolMapFrontend {

  public static CapitolBackend<String, Number> backend;

  /**
   * Starts the application
   */
  @Override
  public void start(Stage stage) {
    try {
      startFront(stage);
//      stage.setFullScreen(true);
    } catch (FileNotFoundException e) {
      System.out.println(e.getMessage());
    }

  }

  /**
   * Loads the interface and its necessary components and actions
   *
   * @param stage - Stage
   * @throws FileNotFoundException
   */
  public void startFront(Stage stage) throws FileNotFoundException {
    // Set the border
    BorderPane borderPane = new BorderPane();
    borderPane.setPadding(new Insets(50, 50, 50, 50));

    // Set the scene
    Scene scene = new Scene(borderPane, 1100, 800, Color.BEIGE);
    scene.getStylesheets()
            .add("https://fonts.googleapis.com/css2?family=Barlow+Condensed:wght@300&display=swap");
    stage.setTitle("Welcome to the Capitol Map Application!");

    // Creating the image view
    InputStream stream = new FileInputStream("src/capitol.png");
    Image image = new Image(stream);
    ImageView imageView = new ImageView();
    imageView.setImage(image);
    imageView.setX(20);
    imageView.setY(20);
    imageView.setFitWidth(250);
    imageView.setFitHeight(250);
    Label picture = new Label("", imageView);
    picture.setPadding(new Insets(25, 5, 5, 50));
    borderPane.setRight(picture);


    VBox box = new VBox();
    box.setPadding(new Insets(25, 5, 5, 50));
    box.setSpacing(10);
    borderPane.setLeft(box);

    // Add capitol feature
    Label add = new Label("Add a new capitol: ");
    add.setStyle(
            "-fx-font-weight: bold; -text-fill:BLACK; -fx-font-size: 15; -fx-font-family: Barlow;");

    Label addStart = new Label("Enter the starting capitol: ");
    addStart.setStyle("-fx-text-fill:BLACK; -fx-font-size: 10; -fx-font-family: Barlow;");
    TextField textField1 = new TextField();

    Label addArrival = new Label("Enter the destination capitol: ");
    addArrival.setStyle("-fx-text-fill:BLACK; -fx-font-size: 10; -fx-font-family: Barlow;");
    TextField textField2 = new TextField();

    Label addWeight = new Label("Distance apart: ");
    addWeight.setStyle("-fx-text-fill:BLACK; -fx-font-size: 10; -fx-font-family: Barlow;");
    TextField textField3 = new TextField();

    Button aButton = new Button("Add Capitol");
    Button reset = new Button("Reset");

    box.getChildren().addAll(add, addStart, textField1, addArrival, textField2, addWeight,
            textField3, aButton);

    aButton.setOnAction(e -> {
      String check = textField1.getText().trim();
      String check2 = textField2.getText().trim();
      String check3 = textField3.getText().trim();
      if (!check.matches("[a-zA-Z ]+") || !check2.matches("[a-zA-Z ]+")
              || !check3.matches("[0-9]+")) {
        Label error = new Label(
                "The locations must only contain letters and the\n distance must be a number");
        error.setStyle("-fx-text-fill:BLACK; -fx-font-size: 15; -fx-font-family: Barlow;");
        VBox tempBox = new VBox();
        tempBox.setPadding(new Insets(25, 5, 5, 50));
        tempBox.setSpacing(10);
        borderPane.setCenter(tempBox);
        HBox hbox = new HBox(error);
        borderPane.setTop(hbox);
        hbox.setAlignment(Pos.TOP_CENTER);
        tempBox.getChildren().add(error);
        return;
      }
      String start = textField1.getText().trim();
      System.out.println(start);
      String end = textField2.getText().trim();
      System.out.println(end);
      Double weight = Double.valueOf(textField3.getText().trim());
      System.out.println(weight);



      // String capitolList = String.join(" ", backend.getAllCapitol());
      // String capitolList = "Madison Austin Atlanta";
      // if (capitolList.contains(start) && capitolList.contains(end)) {
      // Label error = new Label("Both locations are already present in the system");
      // error.setStyle("-fx-text-fill:BLACK; -fx-font-size: 15; -fx-font-family: Barlow;");
      // VBox tempBox = new VBox();
      // tempBox.setPadding(new Insets(25, 5, 5, 50));
      // tempBox.setSpacing(10);
      // borderPane.setCenter(tempBox);
      // HBox hbox = new HBox(error);
      // borderPane.setTop(hbox);
      // hbox.setAlignment(Pos.TOP_CENTER);
      // tempBox.getChildren().add(error);
      // return;
      // }

      backend.addCapitol(start, end, weight);
      Label success = new Label("The new capitol path was added to the system");
      success.setStyle("-fx-text-fill:BLACK; -fx-font-size: 15; -fx-font-family: Barlow;");
      VBox tempBox = new VBox();
      tempBox.setPadding(new Insets(25, 5, 5, 50));
      tempBox.setSpacing(10);
      borderPane.setCenter(tempBox);
      HBox hbox = new HBox(success);
      borderPane.setTop(hbox);
      hbox.setAlignment(Pos.TOP_CENTER);
      tempBox.getChildren().add(success);

      box.getChildren().add(reset);
    });


    // Get shortest path feature
    Label pathTitle = new Label("Find the shortest path:");
    pathTitle.setStyle(
            "-fx-font-weight: bold; -text-fill:BLACK; -fx-font-size: 15; -fx-font-family: Barlow;");
    Label pathStart = new Label("Enter the starting capitol: ");
    pathStart.setStyle("-fx-text-fill:BLACK; -fx-font-size: 10; -fx-font-family: Barlow;");
    TextField textField4 = new TextField();

    Label pathArrival = new Label("Enter the destination capitol: ");
    pathArrival.setStyle("-fx-text-fill:BLACK; -fx-font-size: 10; -fx-font-family: Barlow;");
    TextField textField5 = new TextField();

    Button sButton = new Button("Find the shortest path");

    box.getChildren().addAll(pathTitle, pathStart, textField4, pathArrival, textField5, sButton);

    sButton.setOnAction(b -> {
      String check = textField4.getText().trim();
      String check2 = textField5.getText().trim();
      if (!check.matches("[a-zA-Z ]+") || !check2.matches("[a-zA-Z ]+")) {
        Label error = new Label("The locations must only contain letters");
        error.setStyle("-fx-text-fill:BLACK; -fx-font-size: 15; -fx-font-family: Barlow;");
        VBox tempBox = new VBox();
        tempBox.setPadding(new Insets(25, 5, 5, 50));
        tempBox.setSpacing(10);
        borderPane.setCenter(tempBox);
        HBox hbox = new HBox(error);
        borderPane.setTop(hbox);
        hbox.setAlignment(Pos.TOP_CENTER);
        tempBox.getChildren().add(error);
        return;
      }

      String start = textField4.getText().trim();
      String end = textField5.getText().trim();
      String shortestPath = null;
      // boolean pathExist = false;

      // (BD)
      try {
        shortestPath = backend.getShortestPath(start, end).toString();
        // pathExist = true;
        // (BD) get path cost
        double pathCost = backend.getPathCost(start, end);
        // double pathCost = 40.0;

        // String path = String.join(" ", backend.shortestPath(start, end));
        String path = shortestPath;
        Label success = new Label(
                "The path goes as follows:\n " + path + "\n with a resulting distance of " + pathCost);
        success.setStyle("-fx-text-fill:BLACK; -fx-font-size: 15; -fx-font-family: Barlow;");
        VBox tempBox = new VBox();
        tempBox.setPadding(new Insets(25, 5, 5, 50));
        tempBox.setSpacing(10);
        borderPane.setCenter(tempBox);
        HBox hbox = new HBox(success);
        borderPane.setTop(hbox);
        hbox.setAlignment(Pos.TOP_CENTER);
        tempBox.getChildren().add(success);

        box.getChildren().add(reset);
      } catch (Exception e) {
        shortestPath = "Path do not exist";
        Label fail = new Label(shortestPath);
        fail.setStyle("-fx-text-fill:BLACK; -fx-font-size: 15; -fx-font-family: Barlow;");
        VBox tempBox = new VBox();
        tempBox.setPadding(new Insets(25, 5, 5, 50));
        tempBox.setSpacing(10);
        borderPane.setCenter(tempBox);
        HBox hbox = new HBox(fail);
        borderPane.setTop(hbox);
        hbox.setAlignment(Pos.TOP_CENTER);
        tempBox.getChildren().add(fail);

        box.getChildren().add(reset);
      }

      // String capitolList = String.join(" ", backend.getAllCapitol());
      // String capitolList = "Madison Austin Atlanta";
      // if (!capitolList.contains(start) || !capitolList.contains(end)) {
      // Label error = new Label("One or both locations are not present in the system");
      // error.setStyle("-fx-text-fill:BLACK; -fx-font-size: 15; -fx-font-family: Barlow;");
      // VBox tempBox = new VBox();
      // tempBox.setPadding(new Insets(25, 5, 5, 50));
      // tempBox.setSpacing(10);
      // borderPane.setCenter(tempBox);
      // HBox hbox = new HBox(error);
      // borderPane.setTop(hbox);
      // hbox.setAlignment(Pos.TOP_CENTER);
      // tempBox.getChildren().add(error);
      // return;
      // }


    });



    // Get all shortest paths feature
    Label allTitle = new Label("Find all shortest paths");
    allTitle.setStyle(
            "-fx-font-weight: bold; -text-fill:BLACK; -fx-font-size: 15; -fx-font-family: Barlow;");
    Label allStart = new Label("Enter a capitol: ");
    allStart.setStyle("-fx-text-fill:BLACK; -fx-font-size: 10; -fx-font-family: Barlow;");
    TextField textField6 = new TextField();

    Button allButton = new Button("Find all shortest paths");

    box.getChildren().addAll(allTitle, allStart, textField6, allButton);

    allButton.setOnAction(c -> {
      String check = textField6.getText().trim();
      if (!check.matches("[a-zA-Z ]+")) {
        Label error = new Label("The locations must only contain letters");
        error.setStyle("-fx-text-fill:BLACK; -fx-font-size: 15; -fx-font-family: Barlow;");
        VBox tempBox = new VBox();
        tempBox.setPadding(new Insets(25, 5, 5, 50));
        tempBox.setSpacing(10);
        borderPane.setCenter(tempBox);
        HBox hbox = new HBox(error);
        borderPane.setTop(hbox);
        hbox.setAlignment(Pos.TOP_CENTER);
        tempBox.getChildren().add(error);
        return;
      }
      // BD
      String start = textField6.getText().trim();
      List<List<String>> allShortestPaths = backend.getAllShortestPath(start);
      // String capitolList = String.join(" ", backend.getAllCapitol());
      // BD
      // String capitolList = "";
      // capitolList += "Shortest Paths List:" + "\n";
      // for (int path = 0; path < allShortestPaths.size(); path++) {
      // capitolList += allShortestPaths.get(path).toString() + "\n";
      // }
      // BD
      List<String> capitolListAll = backend.getAllCapitol();
      String[] capitolString = new String[capitolListAll.size()];
      for (int i = 0; i < capitolListAll.size(); i++) {
        capitolString[i] = capitolListAll.get(i);
      }
      PathsDistance pathDist = new PathsDistance();
      String out = "Shortest Paths List from " + start + " :\n";
      boolean isEmpty = false;
      pathDist.distanceList(start, capitolString, (CapitolMapBackend) backend);
      if (pathDist.toStringDistanceList(start, capitolString, (CapitolMapBackend) backend)
              .isEmpty()) {
        isEmpty = true;
      } else {
        out += pathDist.toStringDistanceList(start, capitolString, (CapitolMapBackend) backend);
      }
      // System.out.println(aeOut);
      // System.out.println(capitolList);
      if (isEmpty) {
        Label error = new Label("The location is not present in the system");
        error.setStyle("-fx-text-fill:BLACK; -fx-font-size: 15; -fx-font-family: Barlow;");
        VBox tempBox = new VBox();
        tempBox.setPadding(new Insets(25, 5, 5, 50));
        tempBox.setSpacing(10);
        borderPane.setCenter(tempBox);
        HBox hbox = new HBox(error);
        borderPane.setTop(hbox);
        hbox.setAlignment(Pos.TOP_CENTER);
        tempBox.getChildren().add(error);
        return;
      }

      Label success = new Label(out);
      VBox tempBox = new VBox();
      tempBox.setPadding(new Insets(25, 5, 5, 50));
      tempBox.setSpacing(10);
      borderPane.setCenter(tempBox);
      HBox hbox = new HBox(success);
      borderPane.setTop(hbox);
      hbox.setAlignment(Pos.TOP_CENTER);
      tempBox.getChildren().add(success);

      box.getChildren().add(reset);


      // TableView<List<String>> table = new TableView<>();
      // for (int i = 0; i < backend.getAllShortestPath(start).get(0).size(); i++) {
      // final int index = i;
      // TableColumn<List<String>, String> column = new TableColumn<>("Column " + i);
      // column.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(index)));
      // table.getColumns().add(column);
      // }
      //
      // table.setItems(FXCollections.observableArrayList(backend.getAllShortestPath(start)));
      // scene.setRoot(table);
    });


    // Get all capitol names feature

    Label allCapitols = new Label("Get all capitol names:");
    allCapitols.setStyle(
            "-fx-font-weight: bold; -text-fill:BLACK; -fx-font-size: 15; -fx-font-family: Barlow;");
    Label filter = new Label("Optional: Enter a prefix:");
    filter.setStyle("-fx-text-fill:BLACK; -fx-font-size: 10; -fx-font-family: Barlow;");
    TextField textField7 = new TextField();

    Button getList = new Button("Search");

    box.getChildren().addAll(allCapitols, filter, textField7, getList);

    getList.setOnAction(l -> {
      String input = textField7.getText().trim();
      if (!input.isEmpty() || !input.equals("")) {
        String check = textField7.getText().trim();
        if (!check.matches("[a-zA-Z]+")) {
          Label error = new Label("The prefix must only contain letters");
          error.setStyle("-fx-text-fill:BLACK; -fx-font-size: 15; -fx-font-family: Barlow;");
          VBox tempBox = new VBox();
          tempBox.setPadding(new Insets(25, 5, 5, 50));
          tempBox.setSpacing(10);
          borderPane.setCenter(tempBox);
          HBox hbox = new HBox(error);
          borderPane.setTop(hbox);
          hbox.setAlignment(Pos.TOP_CENTER);
          tempBox.getChildren().add(error);
          return;
        }

        String start = textField7.getText().trim();
        // BD
        Stage newstage = new Stage();
        ListView<String> listView = new ListView<>();
        listView.getItems().addAll(backend.getAllCapitol(start));
        listView.setPrefSize(1000, 300);
        Scene listScene = new Scene(listView);
        newstage.setScene(listScene);
        newstage.show();
      }

      else {
        ListView<String> listView = new ListView<>();
        Stage newstage = new Stage();
        listView.getItems().addAll(backend.getAllCapitol());
        listView.setPrefSize(1000, 300);
        Scene listScene = new Scene(listView);
        // BD
        // stage.setScene(listScene);
        newstage.setScene(listScene);
        newstage.show();
      }
    });



    reset.setOnAction(r -> {
      restart(stage);
    });

    stage.setScene(scene);
    stage.show();
  }

  /**
   * Resets the interface to blank
   *
   * @param stage - Stage
   */
  public void restart(Stage stage) {
    try {
      startFront(stage);
//      stage.setFullScreen(true);
    } catch (FileNotFoundException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Returns the cost for a certain path
   *
   * @param start - starting capitol
   * @param end   - destination capitol
   * @return path cost
   */
  public double pathCost(String start, String end) {
    return backend.getPathCost(start, end);
  }

  /**
   * String method that formats the starting to destination output
   *
   * @param start - starting capitol
   * @param end   - destination capitol
   * @return string format of result
   */
  public String result(String start, String end) {
    return "Start: " + start + "\n End: " + end;
  }


  /**
   * Forwards input of starting and destination capitols and returns the shortest path
   *
   * @param start       - starting capitol
   * @param end - destination capitol
   * @return - List pertaining to the shortest path
   */
  @Override
  public List<String> sendInputToBack(String start, String end) {
    return backend.shortestPath(start, end);
  }


  public static void main(String args[]) {
    launch(args);
  }
}