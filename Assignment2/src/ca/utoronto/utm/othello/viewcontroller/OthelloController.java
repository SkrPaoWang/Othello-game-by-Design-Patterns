package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Move;
import ca.utoronto.utm.othello.model.Othello;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class OthelloController implements EventHandler<ActionEvent>{
	private Othello othello;
	public OthelloController(Othello othello){
		this.othello = othello;	
	}
	
	public void handle(ActionEvent event) {
		Button source=(Button)event.getSource();
		Integer colIndex = GridPane.getColumnIndex(source);
	    Integer rowIndex = GridPane.getRowIndex(source);
	    Move move = new Move(rowIndex,colIndex);
	    this.othello.move(move.getRow(), move.getCol());
	    System.out.println(othello.getBoardString());
	    //finish 1.012
	    
	
		
		
	}

}
