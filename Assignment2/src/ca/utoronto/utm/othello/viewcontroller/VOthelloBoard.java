package ca.utoronto.utm.othello.viewcontroller;
import ca.utoronto.utm.util.*;
import javafx.scene.layout.GridPane;
import ca.utoronto.utm.othello.model.*;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

class VOthelloBoard implements Observer {
	protected Othello othello;
	protected GridPane grid;
	protected TextField tf;
	
	
	protected OthelloEventHandler controller;
	protected Image black;
	protected Image white;
	private Label labelwhoturns;Label game_status;
	Label labelcountX; Label labelcountO;
	private Label P1; Label P2;
	ChoiceBox<String> choicebox;
	

	public VOthelloBoard(Othello othello,OthelloEventHandler controller) {
		//initializer
		this.othello = othello;
		this.controller = controller;
		this.grid = new GridPane();
		//this.tf = new TextField("    "+othello.getWhosTurn()+" moves next");
		//this.tf.setPrefColumnCount(10);
		//this.tf.setDisable(true);
		//this.grid.add(tf, 8, 0);
		// grid format
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);
		// images
		black = new Image(getClass().getResourceAsStream("/resources/black.PNG"));
		white = new Image(getClass().getResourceAsStream("/resources/white.PNG"));
		//any way to not update here?
		
		
		//skr's staff
		this.labelwhoturns = new Label("X moves Next");
		this.labelcountX = new Label("X : 2");
		this.labelcountO = new Label("O : 2");
		this.P1 = new Label("P1: Human");
		this.P2 = new Label("P2:");
		this.game_status = new Label("Game in Progress");
		this.P1 = new Label("P1:Human");
		this.P2 = new Label("P2:Human");
		this.grid.add(labelwhoturns, 9, 1);
		this.grid.add(labelcountX, 9, 2);
		this.grid.add(labelcountO, 9, 3);
		this.grid.add(game_status, 9, 4);
		update(othello);
		
		
		
	}

	@Override
	public void update(Observable o) {
		// TODO Auto-generated method stub
		othello = (Othello)o;
		// skr staff
		this.labelwhoturns.setText(othello.getWhosTurn() + " moves Next");
		this.labelcountX.setText("X : " + String.valueOf(othello.getCount('X')));
		this.labelcountO.setText("O : " + String.valueOf(othello.getCount('O')));
		
		//original
		for(int i=0; i<8; i++) {
			for (int j=0; j<8; j++) {
				Button button = new Button("");
				if (othello.getToken(i, j) == 'X') {
						button.setGraphic(new ImageView(black));
				}
				else if (othello.getToken(i, j) == 'O') {
						button.setGraphic(new ImageView(white));
				}
				button.setMinSize(30,30);
				OthelloApplicationEventHandler e = new OthelloApplicationEventHandler(othello, i, j);
				button.setOnAction(e);
				grid.add(button, j, i);
			}
		}
		//tf.setText("    "+othello.getWhosTurn()+" moves next");
		
	}
}
