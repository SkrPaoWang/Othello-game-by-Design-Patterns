package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.PlayerOppenent;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class FirstPageController implements EventHandler<ActionEvent> {
	private Stage stage;
	private Timeline timer;
	private FirstPage firstPage;
	private Scene scene;
	private Othello othello;
	private GameController gamecontroller;
	public FirstPageController(Othello othello, Stage stage, Scene s, Timeline timer,GameController gamecontroller) {
		this.setStage(stage);
		this.timer = timer;
		this.setFirstPage(firstPage);
		this.scene = s;
		this.othello = othello;
		this.gamecontroller = gamecontroller;
		
	}
	@Override
	public void handle(ActionEvent event) {
		Button source = (Button) event.getSource();
		if (source.getText() == "VS Greedy:") {
			this.gamecontroller.opponent = new PlayerOppenent(OppenentFactory.createPlayer("Greedy", othello));
		}else if (source.getText() == "VS Random") {
			this.gamecontroller.opponent = new PlayerOppenent(OppenentFactory.createPlayer("Random", othello));
		}else if (source.getText() == "VS Human") {
			this.gamecontroller.opponent = new PlayerOppenent(OppenentFactory.createPlayer("Human", othello));
		}
		stage.setScene(scene);
		timer.setCycleCount(Animation.INDEFINITE);
		timer.play();
		
	}
	public Stage getStage() {
		return stage;
	}
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	public Timeline getTimer() {
		return timer;
	}
	public void setTimer(Timeline timer) {
		this.timer = timer;
	}
	public FirstPage getFirstPage() {
		return firstPage;
	}
	public void setFirstPage(FirstPage firstPage) {
		this.firstPage = firstPage;
	}

}
