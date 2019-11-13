package ca.utoronto.utm.othello.viewcontroller;

import com.sun.glass.ui.Menu;

import ca.utoronto.utm.othello.model.Othello;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;

public class TimerController implements EventHandler<ActionEvent> {

	private int seconds = 10;
	private int minutes = 0;
	private String name;
	private Label label;
	private Othello game;

	public TimerController(String name, Label label, Othello game) {
		this.name = name;
		this.game = game;
		this.label = label;
		this.label.setText(name + ": " + + minutes + ":" + seconds);
	}

	@Override
	public void handle(ActionEvent event) {
		if (minutes == 0 && seconds == 0) {
			game.restart_game();
			
		}
		
		if (seconds == 0) {
			minutes--;
			seconds = 60;
		}
		seconds--;
		
//		System.out.println(this.name + ":   " + minutes + ":" + seconds);
		this.label.setText(this.name + ":   " + minutes + ":" + seconds);
	}

}
