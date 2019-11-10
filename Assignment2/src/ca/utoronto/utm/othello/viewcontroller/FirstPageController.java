package ca.utoronto.utm.othello.viewcontroller;

import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FirstPageController implements EventHandler<ActionEvent> {
	private Stage stage;
	private Timeline timer;
	private FirstPage firstPage;
	private Scene scene;
	public FirstPageController(Stage stage, Scene s, Timeline timer) {
		this.setStage(stage);
		this.timer = timer;
		this.setFirstPage(firstPage);
		this.scene = s;
		
	}
	@Override
	public void handle(ActionEvent event) {
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
