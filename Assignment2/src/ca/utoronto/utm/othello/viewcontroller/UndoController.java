package ca.utoronto.utm.othello.viewcontroller;
import java.util.ArrayList;

import ca.utoronto.utm.othello.model.Move;
import ca.utoronto.utm.othello.model.Othello;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

class UndoController implements EventHandler<ActionEvent>{
	protected Othello o;
	public UndoController(Othello o) {
		this.o = o;
	}

	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
		if(!(o.moves.size()==0)) {	
			o.moves.remove(o.moves.size()-1);
			o.restart_game();
			ArrayList<Move> temp = o.moves;
			o.moves = new ArrayList();
			for (Move m:temp) {
				o.move(m.getRow(),m.getCol());
			}
			System.out.println(o.getBoardString());
		}
	}

}
