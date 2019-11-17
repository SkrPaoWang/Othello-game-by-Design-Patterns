package ca.utoronto.utm.othello.model;

public class WinnerVisitor implements Visitor {



	@Override
	public boolean visit(OthelloBoard gb, int row, int col, char turn) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public char visit(OthelloBoard gb) {
		// TODO Auto-generated method stub
		if(gb.getCount(OthelloBoard.P1)> gb.getCount(OthelloBoard.P2))return gb.P1;
		if(gb.getCount(OthelloBoard.P1)< gb.getCount(OthelloBoard.P2))return gb.P2;
		return gb.EMPTY;
		
	}
}
	