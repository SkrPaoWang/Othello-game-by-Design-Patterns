package ca.utoronto.utm.othello.model;

public interface Visitor {
	public boolean visit(OthelloBoard gb, int row, int col, char turn); // for move
	public char visit(OthelloBoard gb);
}
