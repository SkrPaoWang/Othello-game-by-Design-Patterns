package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.OthelloBoard;
import ca.utoronto.utm.othello.model.Player;
import ca.utoronto.utm.othello.model.PlayerAlpha;
import ca.utoronto.utm.othello.model.PlayerGreedy;
import ca.utoronto.utm.othello.model.PlayerHuman;
import ca.utoronto.utm.othello.model.PlayerRandom;

public class OppenentFactory {

	public static Player createPlayer(String product, Othello othello) {
		switch (product) {
		case "Greedy":
			return new PlayerGreedy(othello, OthelloBoard.otherPlayer(othello.getWhosTurn()));
		case "Random":
			return new PlayerRandom(othello,  OthelloBoard.otherPlayer(othello.getWhosTurn()));
		case "Human":
			return new PlayerHuman(othello,  OthelloBoard.otherPlayer(othello.getWhosTurn()));
		case "Alpha":
			return new PlayerAlpha(othello,  OthelloBoard.otherPlayer(othello.getWhosTurn()));
		}
		return null;
	}

}
