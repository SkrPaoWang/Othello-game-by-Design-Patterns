
package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Move;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.OthelloBoard;
import ca.utoronto.utm.othello.model.PlayerHuman;
import ca.utoronto.utm.othello.model.PlayerOppenent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class GameController implements EventHandler<ActionEvent> {
	public Othello othello;
	public PlayerOppenent opponent = null;

	public GameController(Othello othello) {
		this.othello = othello;
	}

	public void handle(ActionEvent event) {
		Integer rowIndex = GridPane.getRowIndex((Button) event.getSource());
		Integer colIndex = GridPane.getColumnIndex((Button) event.getSource());
		Move move = new Move(rowIndex, colIndex);
		if ((this.opponent.oppenent_strategy instanceof PlayerHuman)) {
			this.othello.move(move.getRow(), move.getCol());
		} else {
			this.othello.move(move.getRow(), move.getCol());
			this.oppenent_move();
		}
	}

	public void change_oppenent(String s) {
		if (othello.getWhosTurn() == 'O') {
			this.opponent.setOpponent(OppenentFactory.createPlayer(s, othello, othello.getWhosTurn()));
		} else {
			this.opponent.setOpponent(
					OppenentFactory.createPlayer(s, othello, OthelloBoard.otherPlayer(othello.getWhosTurn())));
		}
	}

	public void oppenent_move() {
		if (othello.getWhosTurn() == this.opponent.getToken()) {
			this.othello.move(opponent.getMove().getRow(), opponent.getMove().getCol());
		}
	}

}
