package ca.utoronto.utm.othello.viewcontroller;
import ca.utoronto.utm.othello.model.Othello;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;


public class GameModeController implements EventHandler<ActionEvent>{
	private Othello othello;
	public GameModeController(Othello othello) {
		this.othello = othello;
	}
	public void handle(ActionEvent event) {
		Button source=(Button)event.getSource();
		if (source.getText() == "Human vs Human") {
			OthelloView.P2.setText("P2: Human");
		}else if(source.getText() == "Human vs Random"){
			OthelloView.P2.setText("P2: Random");
		}else if(source.getText() == "Human vs Greedy") {
			OthelloView.P2.setText("P2: Greedy");
	}
}
}