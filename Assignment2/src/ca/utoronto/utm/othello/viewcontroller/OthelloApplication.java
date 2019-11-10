
package ca.utoronto.utm.othello.viewcontroller;
import ca.utoronto.utm.othello.model.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

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
		
	
		GameController controller = new GameController(othello);
		MenuController menu_control = new MenuController(othello);
		UndoController controller3 = new UndoController(othello);
		// VIEW
		// VIEW->CONTROLLER hookup
		
		OthelloView view = new OthelloView(controller,menu_control, controller3);
		
		
		// MODEL->VIEW hookup
	
		othello.attach(view);
        
		// Set Timer
		TimerController timerController = new TimerController ("Timer", view.timerDisplay);
		Timeline timer = new Timeline(new KeyFrame(Duration.millis(1000), timerController));
		
		// SCENE
		FirstPage firstPage = new FirstPage();
		Scene scene1 = new Scene(firstPage.pane);
		Scene scene2 = new Scene(view.pane,500,500);
		// Set timer
		
		FirstPageController fpController = new FirstPageController(stage, scene2, timer);
		firstPage.x1.setOnAction(fpController);
		stage.setTitle("Othello");
		stage.setScene(scene1);
		

		// LAUNCH THE GUI
		stage.show();
	
}
}
