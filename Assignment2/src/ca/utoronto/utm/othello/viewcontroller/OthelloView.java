package ca.utoronto.utm.othello.viewcontroller;

import java.util.ArrayList;
import ca.utoronto.utm.othello.model.Move;
import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.othello.model.OthelloBoard;
import ca.utoronto.utm.util.Observable;
import ca.utoronto.utm.util.Observer;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;

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
	protected UndoController controller3;
	private Image black = new Image("file:black.png");
	private Image white = new Image("file:white.png");
	private Image move1 = new Image("file:move1.png");
	public BorderPane pane;
	private GridPane grid;
	private Timeline timer1, timer2;
	private Label timerLabel1, timerLabel2;

	protected TextField timerDisplay1;
	protected TextField timerDisplay2;

	public OthelloView(GameController controller, MenuController controller2, UndoController controller3) {
		this.controller = controller;
		this.controller2 = controller2;
		this.controller3 = controller3;
		this.init_choicebox();
		this.init_game_layout();

	}

	public void addTimer(Timeline t1, Timeline t2) {
		timer1 = t1;
		timer2 = t2;
		timer1.setCycleCount(Animation.INDEFINITE);
		timer2.setCycleCount(Animation.INDEFINITE);
	}

	private void init_choicebox() {
		this.choicebox = new ChoiceBox<>();
		this.choicebox.getItems().addAll("Human VS Human", "Human VS Greedy", "Human VS Alpha", "Change Game Mode");
		this.choicebox.setValue("Change Game Mode");
		this.choicebox.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
			if (newValue == "Human VS Human") {
				this.P1.setText("P1: Human");
				this.P2.setText("P2: Human");
				this.controller.change_oppenent("Human");
			} else if (newValue == "Human VS Greedy") {
				this.P2.setText("P2: Greedy");
				this.P1.setText("P1: Human");
				this.controller.change_oppenent("Greedy");
			} else if (newValue == "Human VS Alpha") {
				this.P2.setText("P2: Alpha");
				this.P1.setText("P1: Human");
				this.controller.change_oppenent("Alpha");
			}
			if (this.controller.othello.getWhosTurn() == 'O') {
				this.controller.oppenent_move();
			}
		});
	}

	private void init_game_layout() {
		this.pane = new BorderPane();
		this.pane.setTop(this.set_menu());
		this.labelwhoturns = new Label("X moves Next");
		this.labelcountX = new Label("X : 2");
		this.labelcountO = new Label("O : 2");
		this.game_status = new Label("Game in Progress");
		this.P1 = new Label("P1:Human");
		this.P2 = new Label("P2:Human");
		this.timerLabel1 = new Label("Timer1:");
		this.timerLabel2 = new Label("Timer2:");
		this.timerDisplay1 = new TextField("minutes:seconds");
		this.timerDisplay2 = new TextField("minutes:seconds");
		this.grid = new GridPane();
		this.grid.add(choicebox, 9, 0);
		this.grid.add(labelwhoturns, 9, 1);
		this.grid.add(labelcountX, 9, 2);
		this.grid.add(labelcountO, 9, 3);
		this.grid.add(game_status, 9, 4);
		this.grid.add(P1, 9, 5);
		this.grid.add(P2, 9, 6);
		this.grid.add(timerLabel1, 9, 7);
		this.grid.add(timerDisplay1, 10, 7);
		this.grid.add(timerLabel2, 9, 8);
		this.grid.add(timerDisplay2, 10, 8);
		grid.setPadding(new Insets(10, 50, 50, 50));
		grid.setVgap(2);
		grid.setHgap(2);
		this.init_chessboard();
		this.pane.setCenter(this.grid);
	}

	private void init_chessboard() {
		for (byte i = 0; i < 8; i++) {
			for (byte j = 0; j < 8; j++) {
				this.grid.getChildren().remove(this.getNode(i, j, grid));
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
	}

	private MenuBar set_menu() {
		MenuBar menuBar = new MenuBar();
		Menu help = new Menu("Help");
		Menu regret = new Menu("regret");
		Menu restart = new Menu("Restart");
		menuBar.getMenus().addAll(help, regret, restart);
		MenuItem greedy = new MenuItem("Greedy Hint");
		MenuItem random = new MenuItem("Random Hint");
		MenuItem alpha = new MenuItem("Alpha Hint");
		MenuItem restart_game = new MenuItem("Restart Game");
		MenuItem undo = new MenuItem("Undo");
		help.getItems().addAll(greedy, random, alpha);
		restart.getItems().addAll(restart_game);
		regret.getItems().addAll(undo);
		undo.setOnAction(controller3);
		greedy.setOnAction(this.controller2);
		random.setOnAction(this.controller2);
		alpha.setOnAction(controller2);
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

	private void update_label(Observable o) {
		Othello othello = (Othello) o;
		if (this.labelwhoturns.getText().charAt(0) != othello.getWhosTurn()) {
			this.labelwhoturns.setText(othello.getWhosTurn() + " moves Next");
			if (othello.getWhosTurn() == OthelloBoard.P2) {
				timer1.pause();
				timer2.play();
			} else if (othello.getWhosTurn() == OthelloBoard.P1) {
				timer2.pause();
				timer1.play();
			}
		}
		this.labelcountX.setText("X : " + String.valueOf(othello.getCount('X')));
		this.labelcountO.setText("O : " + String.valueOf(othello.getCount('O')));
		if (othello.isGameOver()) {
			this.game_status.setText("The game is over and winner is " + othello.getWinner());
		}
	}

	private Button hint_move() {
		if (controller2.hint_move != null) {
			Button b = (Button) this.getNode(controller2.hint_move.getRow(), controller2.hint_move.getCol(), grid);
			b.setStyle("-fx-min-width: 35px; " + "-fx-min-height: 35px; " + "-fx-background-color: PINK");
			return b;
		}
		return null;

	}

	private ArrayList<Move> update_available_move() {
		this.controller.available_move();
		for (Move move : this.controller.moves) {
			Button x = (Button) this.getNode(move.getRow(), move.getCol(), grid);
			ImageView image = new ImageView(this.move1);
			image.setFitHeight(30);
			image.setFitWidth(30);
			x.setGraphic(image);
		}
		return this.controller.moves;
	}

	private boolean check_available_move(ArrayList<Move> Moves, Move move2) {
		for (Move move1 : Moves) {
			if (move1.getRow() == move2.getRow() && move1.getCol() == move2.getCol()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void update(Observable o) {
		Button hint = this.hint_move();
		ArrayList<Move> moves = this.update_available_move();
		if (this.controller2.restart == true) {
			this.init_chessboard();
			this.update_label(o);
			this.controller2.restart = false;
		} else {
			Othello othello = (Othello) o;
			this.update_label(o);
			for (int row = 0; row < Othello.DIMENSION; row++) {
				for (int col = 0; col < Othello.DIMENSION; col++) {
					if (othello.getToken(row, col) != OthelloBoard.EMPTY) {
						this.grid.getChildren().remove(this.getNode(row, col, grid));
						this.grid.add(button_image(othello.getToken(row, col)), col, row);
					} else if (hint != this.getNode(row, col, grid)
							&& (!check_available_move(moves, new Move(row, col)))) {
						this.grid.getChildren().remove(this.getNode(row, col, grid));
						Button button = button_image(OthelloBoard.EMPTY);
						button.setOnAction(controller);
						this.grid.add(button, col, row);
					}

				}
			}
		}
	}
}
