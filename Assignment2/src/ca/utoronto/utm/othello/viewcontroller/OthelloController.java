package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Move;
import ca.utoronto.utm.othello.model.Othello;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class OthelloController implements EventHandler<ActionEvent>{
	private Othello othello;
	private String Oppoent;
	public OthelloController(Othello othello){
		this.othello = othello;	
	}
	//aaaa
	public void handle(ActionEvent event) {
		Button source=(Button)event.getSource();
		if (source.getText() == "Human vs Human") {
			System.out.println("hello");
		}else if(source.getText() == "Human vs Random"){
			System.out.println("hello");
		}else if(source.getText() == "Human vs Greedy") {
			System.out.println("hello");
		}else {
			Integer colIndex = GridPane.getColumnIndex(source);
			Integer rowIndex = GridPane.getRowIndex(source);
			Move move = new Move(rowIndex,colIndex);
			this.othello.make_chage(move.getRow(), move.getCol());}
	    
	}

}
