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
		MenuController menu_control = new MenuController(othello);
		// VIEW
		// VIEW->CONTROLLER hookup
		
		OthelloView view = new OthelloView(controller,menu_control);
		// MODEL->VIEW hookup
	
		othello.attach(view);
        
		
		// SCENE
		First_page first_page = new First_page();
		Scene scene1 = new Scene(first_page.pane);
		Scene scene2 = new Scene(view.pane,500,500);
		first_page.x1.setOnAction(e -> stage.setScene(scene2));
		stage.setTitle("Othello");
		stage.setScene(scene1);

		// LAUNCH THE GUI
		stage.show();
	}
}
