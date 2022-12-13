package agh.ics.oop.gui;

import agh.ics.oop.*;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class App extends Application{

    private GridPane grid = new GridPane();
    private IWorldMap myMap;
    private final int width = 45;
    private final int height = 45;
    public Stage primaryStage;


    private VBox drawObject(Vector2d position) {
        VBox result = null;
        if (this.myMap.isOccupied(position)) {
            Object object = this.myMap.objectAt(position);
            if (object != null) {
                GuiElementBox newElem = new GuiElementBox((IMapElement) object);
                result = newElem.getBox();

            } else {
                result = new VBox(new Label(""));
            }
        } else {
            result = new VBox(new Label(""));
        }
        return result;
    }
    private void drawMap(){
        grid.setGridLinesVisible(true);
        //grid.setStyle("-fx-margin: auto;");
        grid.setStyle("-fx-padding: 100 100 100 100;");
        GrassField myMap = (GrassField) this.myMap;
        int rangeY = myMap.getUpperRight().y - myMap.getLowerLeft().y;
        int rangeX = myMap.getUpperRight().x - myMap.getLowerLeft().x;
        Label label;
        for (int i = 0; i <= rangeY; i++) {
            Integer value = myMap.getUpperRight().y-i;

            //tworzenie labela dla pierwszej wspolrzednej z lewej w poszczegolnych wierszach
            label = new Label(value.toString());

            grid.getRowConstraints().add(new RowConstraints(height));
            grid.add(label, 0, i+1);

            GridPane.setHalignment(label, HPos.CENTER);
            for (int j = 0; j < rangeX+1; j++) {
                if (i == 0) {
                    //tworzenie labela dla pierwszej wspolrzednej z gory w poszczegolnych kolumnach
                    value = myMap.getLowerLeft().x + j;
                    label = new Label(value.toString());
                    grid.add(label, j+1, 0);
                    grid.getColumnConstraints().add(new ColumnConstraints(width));
                    GridPane.setHalignment(label, HPos.CENTER);
                }
                //rysowanie i stylizowanie kwadratów na mapie
                VBox result = drawObject(new Vector2d(j+myMap.getLowerLeft().x , i+myMap.getLowerLeft().y));
                grid.add(result, j+1, rangeY-i+1);
                GridPane.setHalignment(label, HPos.CENTER);
            }
        }
        //tworzenie dodatkowego labela w lewym gornym rogu
        label = new Label("x/y");
        grid.getColumnConstraints().add(new ColumnConstraints(width));
        grid.getRowConstraints().add(new RowConstraints(height));
        grid.add(label, 0, 0);
        GridPane.setHalignment(label, HPos.CENTER);

        //tworzenie widku w okienku
        Button button = new Button("Close");
        button.setStyle("-fx-background-color: red; -fx-text-fill: white;");
// Set the event handler for the button's ActionEvent
        button.setOnAction(event -> {
            // Close the application
            Platform.exit();
            // Disable all threads
            System.exit(0);
        });
        Scene scene = new Scene(grid, (rangeX+2)*width*45.5, (rangeY+2)*height*45.5);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        //primaryStage.show();

        System.out.println(this.myMap.toString());
        System.out.println();
        //System.out.println("System zakończył działanie");
    }


    public void threadExceptionHandler(){
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("Nieprawidlowo wpisane dane: " + e);
                Platform.exit();
                System.exit(0);
            }
        });
    }

    public void updateMap(){
        Platform.runLater(()->{
            grid.getChildren().clear();
            this.grid = new GridPane();
            drawMap();
        });
    }

    private void init(SimulationEngine engine, String text){
        String[] array = text.split(" ");
        MoveDirection[] directions = new OptionsParser().parse(array);
        engine.setDirections(directions);
        Thread threadEngine = new Thread(engine);
        threadEngine.start();


    }

    public void start(Stage primaryStage) {
        try {

            threadExceptionHandler();


            AbstractWorldMap map = new GrassField(10);
            this.myMap = map;
            this.primaryStage = primaryStage;
            Vector2d[] positions2 = {new Vector2d(2,2), new Vector2d(3,4)};
            SimulationEngine engine = new SimulationEngine(map, positions2, this, 800);
            Button button = new Button("Start");
            button.setPadding(new Insets(20, 100, 20 ,100));
            button.setStyle("-fx-font: 24 arial;");
            TextField text = new TextField("Enter directions");
            text.setPadding(new Insets(20,30,20,30));
            text.setStyle("-fx-font: 24 arial;");
            HBox hbox = new HBox(text, button);

            hbox.setAlignment(Pos.CENTER);
            hbox.setSpacing(20);

            button.setOnAction(actionEvent -> init( engine, text.getText()));
            Scene scene = new Scene(hbox, 400, 400);

            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.show();


        } catch (IllegalArgumentException exception) {
            // kod obsługi wyjątku
            System.out.println(exception.getMessage());

        }
        catch (RuntimeException e){
            System.out.println(e.getMessage());
        }

    }
}