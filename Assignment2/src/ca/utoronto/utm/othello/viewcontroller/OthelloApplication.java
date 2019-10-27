package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.*;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class OthelloApplication extends Application {

	// REMEMBER: To run this in the lab put
	// --module-path "/usr/share/openjfx/lib" --add-modules
	// javafx.controls,javafx.fxml
	// in the run configuration under VM arguments.
	// You can import the JavaFX.prototype launch configuration and use it as well.

	public static void main(String[] args) {
		OthelloApplication view = new OthelloApplication();
		launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {
		// Create and hook up the Model, View and the controller

		// MODEL
		Othello othello = new Othello();

		// CONTROLLER
		// CONTROLLER->MODEL hookup

		// VIEW
		// VIEW->CONTROLLER hookup
		// MODEL->VIEW hookup

		GridPane grid = new GridPane();
		// SCENE
		 
	                
		grid.setPadding(new Insets(10,10,10,10));
		grid.setVgap(8);
		grid.setHgap(10);
		for (byte i =1; i<9; i++) {
			for (byte j = 1; j < 9; j++) {
				Button x = new Button();
				grid.add(x, j, i);;
				byte row = i; byte col = j;
				x.setOnAction(e ->System.out.println(new Move(row,col)));
			}
		}
		Scene scene = new Scene(grid,400,400);
		stage.setTitle("Othello");
		stage.setScene(scene);

		// LAUNCH THE GUI
		stage.show();
	}
}
