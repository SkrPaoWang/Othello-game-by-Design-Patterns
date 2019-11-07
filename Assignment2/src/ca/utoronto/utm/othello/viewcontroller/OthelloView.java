package ca.utoronto.utm.othello.viewcontroller;

import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.OthelloBoard;
import ca.utoronto.utm.util.Observable;
import ca.utoronto.utm.util.Observer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class OthelloView implements Observer {
	private Label labelwhoturns;
	Label labelcountX;
	Label labelcountO;
	private Label game_status;
	private Label P1;
	Label P2;
	ChoiceBox<String> choicebox;
	private GameController controller;
	private MenuController controller2;
	private Image black = new Image("file:black.png");
	private Image white = new Image("file:white.png");
	public BorderPane pane;
	private GridPane grid;

	public OthelloView(GameController controller, MenuController controller2) {	
		this.controller = controller;
		this.controller2 = controller2;
		this.init_choicebox();
		this.init_chessboard();
	}
	
	private void init_choicebox() {
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
	}
	
	private void init_chessboard() {
		this.pane = new BorderPane();
		this.pane.setTop(this.set_menu());
		this.labelwhoturns = new Label("X moves Next");
		this.labelcountX = new Label("X : 2");
		this.labelcountO = new Label("O : 2");
		this.game_status = new Label("Game in Progress");
		this.P1 = new Label("P1:Human");
		this.P2 = new Label("P2:Human");
		this.grid = new GridPane();
		this.grid.add(choicebox, 9, 0);
		this.grid.add(labelwhoturns, 9, 1);
		this.grid.add(labelcountX, 9, 2);
		this.grid.add(labelcountO, 9, 3);
		this.grid.add(game_status, 9, 4);
		this.grid.add(P1, 9, 5);
		this.grid.add(P2, 9, 6);
		grid.setPadding(new Insets(10, 50, 50, 50));
		grid.setVgap(2);
		grid.setHgap(2);
		for (byte i = 0; i < 8; i++) {
			for (byte j = 0; j < 8; j++) {
				if ((i == 3 && j == 3) || (i == 4 && j == 4)) {
					grid.add(button_image(OthelloBoard.P1), j, i);
				} else if ((i == 3 && j == 4) || (i == 4 && j == 3)) {
					grid.add(button_image(OthelloBoard.P2), j, i);
				} else {
					Button x = button_image(OthelloBoard.EMPTY);
					grid.add(x, j, i);
					x.setOnAction(controller);
				}
			}
		}
		this.pane.setCenter(this.grid);
		
	}

	private MenuBar set_menu() {
		MenuBar menuBar = new MenuBar();
		Menu help = new Menu("Help");
		Menu caonima = new Menu("wannima");
		Menu restart = new Menu("Restart");
		menuBar.getMenus().addAll(help, caonima, restart);
		MenuItem greedy = new MenuItem("Greedy Hint");
		MenuItem random = new MenuItem("Random Hint");
		MenuItem restart_game = new MenuItem("Restart Game");
		help.getItems().addAll(greedy, random);
		restart.getItems().addAll(restart_game);
		greedy.setOnAction(this.controller2);
		random.setOnAction(this.controller2);
		restart_game.setOnAction(this.controller2);
		return menuBar;
	}

	private Node getNode(int row, int column, GridPane gridPane) {
		for (Node node : gridPane.getChildren()) {
			if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
				return node;
			}
		}
		return null;
	}

	private Button button_image(char token) {
		Button button = new Button("");
		button.setStyle("-fx-min-width: 35px; " + "-fx-min-height: 35px; ");
		if (token != OthelloBoard.EMPTY) {
			ImageView image = (token == OthelloBoard.P1) ? new ImageView(black) : new ImageView(white);
			image.setFitHeight(30);
			image.setFitWidth(30);
			button.setGraphic(image);
		}
		return button;
	}

	@Override
	public void update(Observable o) {
		if (controller2.hint_move != null) {
			Button b = (Button) this.getNode(controller2.hint_move.getRow(), controller2.hint_move.getCol(), grid);
			b.setStyle("-fx-min-width: 35px; " + "-fx-min-height: 35px; " + "-fx-background-color: PINK");};
		Othello othello = (Othello) o;
		System.out.println(othello.getCount('X'));
		this.labelwhoturns.setText(othello.getWhosTurn() + " moves Next");
		this.labelcountX.setText("X : " + String.valueOf(othello.getCount('X')));
		this.labelcountO.setText("O : " + String.valueOf(othello.getCount('O')));
		if (othello.isGameOver()) {
			this.game_status.setText("The game is over and winner is " + othello.getWinner());
		}
		for (int row = 0; row < Othello.DIMENSION; row++) {
			for (int col = 0; col < Othello.DIMENSION; col++) {
				//if (othello.getToken(row, col) != OthelloBoard.EMPTY) {
					System.out.print(1);
					this.grid.getChildren().remove(this.getNode(row, col, this.grid));
					this.grid.add(button_image(othello.getToken(row, col)), col, row);
//				}

			}
		}
	}
}
//}
