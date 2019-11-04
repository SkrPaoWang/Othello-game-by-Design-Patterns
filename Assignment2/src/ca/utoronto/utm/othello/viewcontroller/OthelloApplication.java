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
	// --module-path "/usr/share/openjfx/lib" --add-modules javafx.controls,javafx.fxml
	// in the run configuration under VM arguments.
	// You can import the JavaFX.prototype launch configuration and use it as well.
	
	@Override
	public void start(Stage stage) throws Exception {
		// Create and hook up the Model, View and the controller
		
		// MODEL
		Othello othello=new Othello();
		
		// CONTROLLER
		// CONTROLLER->MODEL hookup
		// added by linfeng
		OthelloEventHandler controller = new OthelloEventHandler(othello);
		
		// VIEW
		// VIEW->CONTROLLER hookup
		// MODEL->VIEW hookup
		VOthelloBoard v = new VOthelloBoard(othello, controller);
		othello.attach(v);
		
		VWhosTurn w = new VWhosTurn();
		othello.attach(w);
		
		// SCENE
		FirstPage first_page = new FirstPage();
		Scene scene1 = new Scene(first_page.pane);
		Scene scene2 = new Scene(v.grid, 500, 500);
		first_page.x1.setOnAction(e -> stage.setScene(scene2));
		stage.setTitle("Othello");
		stage.setScene(scene1);
				
		// LAUNCH THE GUI
		stage.show();
	}

	public static void main(String[] args) {
		OthelloApplication view = new OthelloApplication();
		launch(args);
	}
}
