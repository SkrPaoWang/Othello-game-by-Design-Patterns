package ca.utoronto.utm.othello.viewcontroller;

//import java.awt.event.ActionListener;
//import javax.swing.JButton;
import ca.utoronto.utm.othello.model.Othello;
import ca.utoronto.utm.util.Observable;
import ca.utoronto.utm.util.Observer;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class OthelloView implements Observer {
	public GridPane grid;
	private Label labelwhoturns; 
	private Label labelcountX; Label labelcountO;
	private Label game_status;
	protected static Label P1;
	protected static Label P2;
	public OthelloView(OthelloController controller, GameModeController controller2) {
		Button button1 = new Button("Human vs Human");
		Button button2 = new Button("Human vs Random");
		Button button3 = new Button("Human vs Greedy");
		this.labelwhoturns = new Label("X moves Next");
		this.labelcountX = new Label("X : 2");
		this.labelcountO = new Label("O : 2");
		this.P1 = new Label("P1: Human");
		this.P2 = new Label("P2:");
		this.game_status = new Label("Game in Progress");
		this.grid =  new GridPane();
		this.grid.add(labelwhoturns, 10, 10);
		this.grid.add(labelcountX, 10, 11);
		this.grid.add(labelcountO, 10, 12);
		this.grid.add(game_status, 10, 13);
		this.grid.add(P1,10,14);
		this.grid.add(P2,10,15);
		this.grid.add(button1, 10,1);
		this.grid.add(button2, 10,2);
		this.grid.add(button3, 10,3);
		button1.setOnAction(controller2);
		button2.setOnAction(e -> this.P2.setText("P2: Random"));
		button3.setOnAction(e -> this.P2.setText("P2: Greedy"));
		grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(8);
		grid.setHgap(10);
		for (byte i = 0; i < 8; i++) {
			for (byte j = 0; j < 8; j++) {
				if ((i == 3 && j == 3) || (i == 4 && j == 4)) {
					grid.add(new Label("X"), j, i);
				} else if ((i == 3 && j == 4) || (i == 4 && j == 3)) {
					grid.add(new Label("O"), j, i);
				} else {
					Button x = new Button();
					grid.add(x, j, i);
					x.setOnAction(controller);
				}
			}

		}
	}

	private Node getNode(int row, int column, GridPane gridPane) {
		Node result = null;
		ObservableList<Node> childrens = gridPane.getChildren();
		for (Node node : childrens) {
			if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == column) {
				result = node;
				break;
			}
		}
		return result;
	}

	@Override
	public void update(Observable o) {
		Othello othello = (Othello) o;
		this.labelwhoturns.setText(othello.getWhosTurn() + " moves Next");
		this.labelcountX.setText("X : " + String.valueOf(othello.getCount('X')));
		this.labelcountO.setText("O : " + String.valueOf(othello.getCount('O')));
		if (othello.isGameOver()) {
			this.game_status.setText("The game is over and winner is " + othello.getWinner());}
		for (int row = 0; row < Othello.DIMENSION; row++) {
			for (int col = 0; col < Othello.DIMENSION; col++) {
				Object element = this.getNode(row, col, this.grid);
				char token = othello.getToken(row, col);
				if (!(element instanceof Button) && token != ' ') {
					Label label = (Label) element;
					char c = label.getText().charAt(0);
					if (c != token) {
						label.setText("" + token);
					}
				}
				if ((token == 'X'||token =='O') && element instanceof Button) {
					grid.getChildren().remove(element);
					grid.add((token == 'X')? new Label("X"):new Label("O"), col, row);
				}
			}
		}
	}
}

//finish userstory1.05
