package oop.gradle;

import javafx.application.Application;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioMenuItem;
import javafx.css.PseudoClass;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.layout.VBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
public class App extends Application {

    @Override
    public void start(final Stage primaryStage) {
        MenuBar menuBar = new MenuBar();
        //menu
        Menu menu = new Menu("Menu");
        MenuItem restart = new MenuItem("Restart Game");
        menu.getItems().add(restart);
        MenuItem reset = new MenuItem("Reset Game");
        menu.getItems().add(reset);
        Menu level = new Menu("Level");
        RadioMenuItem easy = new RadioMenuItem("Easy");
        level.getItems().add(easy);
        RadioMenuItem medium = new RadioMenuItem("Medium");
        level.getItems().add(medium);
        RadioMenuItem hard = new RadioMenuItem("Hard");
        level.getItems().add(hard);
        ToggleGroup toggleGroup = new ToggleGroup();
        toggleGroup.getToggles().add(easy);
        toggleGroup.getToggles().add(medium);
        toggleGroup.getToggles().add(hard);
        menu.getItems().add(level);
        menuBar.getMenus().add(menu);
        //help
        Menu helpMenu = new Menu("Help");
        MenuItem help = new MenuItem("Game Instructions");
        helpMenu.getItems().add(help);
        menuBar.getMenus().add(helpMenu);

        //about
        Menu aboutMenu = new Menu("About");
        menuBar.getMenus().add(aboutMenu);

        Button button1 = new Button();
		button1.setText("Easy");
        Button button2 = new Button();
		button2.setText("Medium");
        Button button3 = new Button();
		button3.setText("Hard");
        Button button4 = new Button();
		button4.setText("Start");

        button4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
			public void handle(ActionEvent event) {

        GridPane board = new GridPane();

        PseudoClass right = PseudoClass.getPseudoClass("right");
        PseudoClass bottom = PseudoClass.getPseudoClass("bottom");

        for (int col = 0; col < 9; col++) {
            for (int row = 0; row < 9; row++) {
                StackPane cell = new StackPane();
                cell.getStyleClass().add("cell");
                cell.pseudoClassStateChanged(right, col == 2 || col == 5);
                cell.pseudoClassStateChanged(bottom, row == 2 || row == 5);

                cell.getChildren().add(createTextField());

                board.add(cell, col, row);
            }
        }


        Scene scene = new Scene(board);
        scene.getStylesheets().add("sudoku.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    });
    
    VBox vbox = new VBox(menuBar,button1, button2,button3,button4);
    Scene scene = new Scene(vbox, 500, 300);

		primaryStage.setTitle("Sudoku");
		primaryStage.setScene(scene);
		primaryStage.show();
}

    private TextField createTextField() {
        TextField textField = new TextField();

        // restrict input to integers:
        textField.setTextFormatter(new TextFormatter<Integer>(c -> {
            if (c.getControlNewText().matches("\\d?")) {
                return c ;
            } else {
                return null ;
            }
        }));
        return textField ;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
