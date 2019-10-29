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
import javafx.scene.layout.GridPane;

public class OthelloView implements Observer {
	public GridPane grid;
	public OthelloView(OthelloController controller) {
		GridPane grid = new GridPane();
		this.grid = grid;
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

//finish userstory1.03
