package ca.utoronto.utm.othello.model;

public class MoveVisitor implements Visitor {



	@Override
	public boolean visit(OthelloBoard gb, int row, int col, char turn) {
		// TODO Auto-generated method stub
		return gb.move(row, col, turn);
	}

	@Override
	public char visit(OthelloBoard gb) {
		// TODO for winner
		return ' ';
	}
	
}
