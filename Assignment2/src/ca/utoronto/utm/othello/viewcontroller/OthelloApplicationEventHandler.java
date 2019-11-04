package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

class OthelloApplicationEventHandler implements EventHandler<ActionEvent> {
	private int row;
	private int col;
	private Othello o;
	
	
	public OthelloApplicationEventHandler(Othello o, int row, int col) {
		this.row = row;
		this.col = col;
		this.o = o;
	}
	
	public void handle(ActionEvent event) {
		o.move(row, col);
		System.out.println("("+row+","+col+")");
	}
}
