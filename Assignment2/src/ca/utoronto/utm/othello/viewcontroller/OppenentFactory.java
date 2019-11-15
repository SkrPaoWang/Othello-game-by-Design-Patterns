package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.OthelloBoard;
import ca.utoronto.utm.othello.model.Player;
import ca.utoronto.utm.othello.model.PlayerAlpha;
import ca.utoronto.utm.othello.model.PlayerGreedy;
import ca.utoronto.utm.othello.model.PlayerHuman;
import ca.utoronto.utm.othello.model.PlayerRandom;

public class OppenentFactory {

	public static Player createPlayer(String product, Othello othello, char c) {
		switch (product) {
		case "Greedy":
			return new PlayerGreedy(othello, c);
		case "Random":
			return new PlayerRandom(othello, c);
		case "Human":
			return new PlayerHuman(othello, c);
		case "Alpha":
			return new PlayerAlpha(othello, c);
		}
		return null;
	}

}
