package ca.utoronto.utm.othello.viewcontroller;
import ca.utoronto.utm.util.*;
import ca.utoronto.utm.othello.model.*;
import javafx.scene.control.Label;

class VWhosTurn extends Label implements Observer{

	@Override
	public void update(Observable o) {
		// TODO Auto-generated method stub
		Othello othello = (Othello)o;
		this.setText(othello.getWhosTurn()+"moves next.");
	}

}
