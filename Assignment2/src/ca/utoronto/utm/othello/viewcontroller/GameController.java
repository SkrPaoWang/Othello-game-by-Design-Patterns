package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Move;
import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.OthelloBoard;
import ca.utoronto.utm.othello.model.PlayerGreedy;
import ca.utoronto.utm.othello.model.PlayerRandom;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class GameController implements EventHandler<ActionEvent> {
	private Othello othello;
	public String robot = "Human";

	public GameController(Othello othello) {
		this.othello = othello;
	}
	public void handle(ActionEvent event) {
		Button source = (Button) event.getSource();
		if (this.robot == "Human") {
			Integer colIndex = GridPane.getColumnIndex(source);
			Integer rowIndex = GridPane.getRowIndex(source);
			Move move = new Move(rowIndex, colIndex);
			this.othello.make_chage(move.getRow(), move.getCol());
		} else if (this.robot == "Greedy") {
			Integer colIndex = GridPane.getColumnIndex(source);
			Integer rowIndex = GridPane.getRowIndex(source);
			Move move1 = new Move(rowIndex, colIndex);
			System.out.println(move1.toString());
			boolean x = this.othello.make_chage(move1.getRow(), move1.getCol());
			if (x) {
				PlayerGreedy greedy = new PlayerGreedy(othello, this.othello.getWhosTurn());
				Move move2 = greedy.getMove();
				this.othello.make_chage(move2.getRow(), move2.getCol());
			}
		} else if (this.robot == "Random"){
			Integer colIndex = GridPane.getColumnIndex(source);
			Integer rowIndex = GridPane.getRowIndex(source);
			Move move3 = new Move(rowIndex, colIndex);
			boolean y = this.othello.make_chage(move3.getRow(), move3.getCol());
			if (y) {
				PlayerRandom random = new PlayerRandom(othello, this.othello.getWhosTurn());
				Move move4 = random.getMove();
				this.othello.make_chage(move4.getRow(), move4.getCol());
			}
		}
	}

}
