package ca.utoronto.utm.othello.model;

public abstract class Visitable {
	abstract boolean accept(MoveVisitor visitor, int row, int col, char turn);// for move
	abstract char accept(WinnerVisitor visitor);

	
}
