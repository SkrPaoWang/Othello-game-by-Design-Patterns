
package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Move;
import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.Player;
import ca.utoronto.utm.othello.model.PlayerGreedy;
import ca.utoronto.utm.othello.model.PlayerHuman;
import ca.utoronto.utm.othello.model.PlayerOppenent;
import ca.utoronto.utm.othello.model.PlayerRandom;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class GameController implements EventHandler<ActionEvent> {
	private Othello othello;
	public PlayerOppenent opponent = null;

	public GameController(Othello othello) {
		this.othello = othello;
	}

	public void handle(ActionEvent event) {
		Button source = (Button) event.getSource();
		Integer rowIndex = GridPane.getRowIndex(source);
		Integer colIndex = GridPane.getColumnIndex(source);
		Move move = new Move(rowIndex, colIndex);
		if ((this.opponent.oppenent_strategy instanceof PlayerHuman)) {
			this.othello.move(move.getRow(), move.getCol());
		} else {
			this.othello.move(move.getRow(), move.getCol());
			Move move2 = this.opponent.getMove();
			this.othello.move(move2.getRow(), move2.getCol());
		}
	}

	
	public void change_oppenent(String s) {
		this.opponent.setOpponent(OppenentFactory.createPlayer(s, othello));
	}
	
	public void oppenent_move() {
		if (othello.getWhosTurn() == this.opponent.getToken()) {
			this.othello.mo
		}
	}

}
