package ca.utoronto.utm.othello.model;

public class PlayerOppenent {
	public Player oppenent_strategy;

	public PlayerOppenent(Player oppenent_strategy) {
		this.oppenent_strategy = oppenent_strategy;
	}

	public void setOpponent(Player oppenent) {
		this.oppenent_strategy = oppenent;
	}

	public Move getMove() {
		return this.oppenent_strategy.getMove();
	}
	
	public char getToken() {
		return this.oppenent_strategy.getPlayer();
	}
}
