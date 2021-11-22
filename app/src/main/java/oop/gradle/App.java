package oop.gradle;

import javafx.application.Application;
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
public class App extends Application {

    @Override
    public void start(final Stage primaryStage) {
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
    
    VBox vbox = new VBox(button1, button2,button3,button4);
    Scene scene = new Scene(vbox, 200, 100);

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
