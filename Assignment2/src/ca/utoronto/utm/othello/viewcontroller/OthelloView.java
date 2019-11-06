package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.OthelloBoard;
import ca.utoronto.utm.util.Observable;
import ca.utoronto.utm.util.Observer;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class OthelloView implements Observer {
	public GridPane grid;
	private Label labelwhoturns;
	Label labelcountX;
	Label labelcountO;
	private Label game_status;
	private Label P1;Label P2;
	ChoiceBox<String> choicebox;
	private OthelloController controller;
	private Image black = new Image("file:black.png"); 
	private Image white = new Image("file:white.png");
	
	public OthelloView(OthelloController controller) {
		
		this.controller = controller;
		this.choicebox = new ChoiceBox<>();
		this.choicebox.getItems().addAll("Human VS Human", "Human VS Greedy", "Human VS Random");
		this.choicebox.setValue("Human VS Human");
		this.choicebox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
			if (newValue == "Human VS Human") {
				this.P1.setText("P1: Human");
				this.P1.setText("P2: Human");
				this.controller.robot = "Human";

			} else if (newValue == "Human VS Greedy") {
				this.P2.setText("P2: Greedy");
				this.controller.robot = "Greedy";
			} else {
				this.P2.setText("P2: Random");
				this.controller.robot = "Random";
			}
		});
		this.labelwhoturns = new Label("X moves Next");
		this.labelcountX = new Label("X : 2");this.labelcountO = new Label("O : 2");
		this.game_status = new Label("Game in Progress");
		this.P1 = new Label("P1:Human");this.P2 = new Label("P2:Human");
		this.grid = new GridPane();
		this.grid.add(choicebox, 9, 0);
		this.grid.add(labelwhoturns, 9, 1);
		this.grid.add(labelcountX, 9, 2);
		this.grid.add(labelcountO, 9, 3);
		this.grid.add(game_status, 9, 4);
		this.grid.add(P1, 9, 5);this.grid.add(P2, 9, 6);
		grid.setPadding(new Insets(10, 50, 50, 50));
		grid.setVgap(2);grid.setHgap(2);
		for (byte i = 0; i < 8; i++) {
			for (byte j = 0; j < 8; j++) {
				Button button = new Button("");
				button.setStyle("-fx-background-radius: 5em; " +"-fx-min-width: 30px; " +
				        "-fx-min-height: 30px; " +"-fx-max-width: 30px; " +"-fx-max-height: 30px;");
				if ((i == 3 && j == 3) || (i == 4 && j == 4)) {
					ImageView image = new ImageView(black);
					image.setFitHeight(30);image.setFitWidth(30);
					button.setGraphic(image);
					grid.add(button, j, i);
				} else if ((i == 3 && j == 4) || (i == 4 && j == 3)) {
					ImageView image = new ImageView(white);
					image.setFitHeight(30);image.setFitWidth(30);
					button.setGraphic(image);
					grid.add(button, j, i);
				} else {
					grid.add(button, j, i);
					button.setOnAction(controller);
				}
			}
		}
	}
	

	private Node getNode(int row, int column, GridPane gridPane) {
		for (Node node : gridPane.getChildren()) {
			if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
		        return node;}}
		return null;}

	@Override
	public void update(Observable o) {
		Othello othello = (Othello) o;
		this.labelwhoturns.setText(othello.getWhosTurn() + " moves Next");
		this.labelcountX.setText("X : " + String.valueOf(othello.getCount('X')));
		this.labelcountO.setText("O : " + String.valueOf(othello.getCount('O')));
		if (othello.isGameOver()) {
			this.game_status.setText("The game is over and winner is " + othello.getWinner());
		}
		for (int row = 0; row < Othello.DIMENSION; row++) {
			for (int col = 0; col < Othello.DIMENSION; col++) {
				if (othello.getToken(row, col) != OthelloBoard.EMPTY) {
					ImageView image = (othello.getToken(row, col) == 'X') ? new ImageView(black) : new ImageView(white);
					image.setFitHeight(30);image.setFitWidth(30);
					Button button = new Button("");
					button.setStyle("-fx-background-radius: 5em; " +"-fx-min-width: 30px; " +
			        "-fx-min-height: 30px; " +"-fx-max-width: 30px; " +"-fx-max-height: 30px;");
					button.setGraphic(image);
					this.grid.getChildren().remove(this.getNode(row, col, this.grid));
					this.grid.add(button, col, row);
				}
			}
		}
	}
}
