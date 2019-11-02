package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.*;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
	Stage firstwin;
	Scene option, game;

	public static void main(String[] args) {
		//OthelloApplication view = new OthelloApplication();
		launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {
		// Create and hook up the Model, View and the controller
		// MODEL
		Othello othello = new Othello();
		
		// CONTROLLER
		// CONTROLLER->MODEL hookup
		
		OthelloController controller = new OthelloController(othello);
		GameModeController controller2 = new GameModeController(othello);
		// VIEW
		// VIEW->CONTROLLER hookup
		
		OthelloView view = new OthelloView(controller, controller2);
		// MODEL->VIEW hookup
		
		othello.attach(view);
		// SCENE
		
		Scene game = new Scene(view.grid,400,400);
		// The window contains 3 buttons
		firstwin = stage;
		Button button1 = new Button("Human vs Human");
		Button button2 = new Button("Human vs Greedy");
		Button button3 = new Button("Human vs Random");
		button1.setOnAction(e -> firstwin.setScene(game));
		button2.setOnAction(e -> firstwin.setScene(game));
		button3.setOnAction(e -> firstwin.setScene(game));
		VBox layout1 = new VBox(20);
		layout1.getChildren().addAll(button1,button2,button3);
		option = new Scene(layout1, 200, 200);
		firstwin.setTitle("Othello");
		firstwin.setScene(option);
		// LAUNCH THE GUI
		firstwin.show();
	}
}