package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Move;
import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.PlayerGreedy;
import ca.utoronto.utm.othello.model.PlayerRandom;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;

public class MenuController implements EventHandler<ActionEvent> {
	private Othello othello;
    public Move hint_move;
	public MenuController(Othello othello) {
		this.othello = othello;
	}

	public void handle(ActionEvent event) {
		MenuItem item = (MenuItem) event.getSource();
		if (item.getText() == "Greedy Hint") {
			PlayerGreedy greedy = new PlayerGreedy(othello, this.othello.getWhosTurn());
			this.hint_move= greedy.getMove();
			this.othello.notifyObservers();
		}else if  (item.getText() == "Random Hint"){
			System.out.println(1);
			PlayerRandom random = new PlayerRandom(othello, this.othello.getWhosTurn());
			this.hint_move= random.getMove();
			this.othello.notifyObservers();
			
		}

	}
}
