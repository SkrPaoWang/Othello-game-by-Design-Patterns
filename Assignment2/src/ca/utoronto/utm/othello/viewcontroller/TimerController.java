package ca.utoronto.utm.othello.viewcontroller;
import ca.utoronto.utm.othello.model.Othello;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;

public class TimerController implements EventHandler<ActionEvent> {

	private int seconds;
	private int minutes;

	private TextField txt;
	private Othello game;

	public TimerController(TextField txt, Othello game) {
		this.game = game;
		this.txt = txt;
	}

	@Override
	public void handle(ActionEvent event) {
		String s = txt.getText();
		try {
			String num1 = s.split(":")[0];
			String num2 = s.split(":")[1];
			minutes = Integer.parseInt(num1);
			seconds = Integer.parseInt(num2);
			this.txt.setText(minutes + ":" + seconds);
		} catch (Exception e) {
			this.txt.setText("minutes:seconds");
		}
		if (minutes == 0 && seconds == 0) {
			game.restart_game();
		}

		if (seconds == 0) {
			minutes--;
			seconds = 60;
		}
		seconds--;
		this.txt.setText(minutes + ":" + seconds);
	}
}
