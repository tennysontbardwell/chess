package Tennyson_T_Bardwell.BasicChessGame.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class GamePane {
	public GamePane(Pane primaryPane) {
		setUpGame(primaryPane);
		primaryPane.widthProperty().addListener(e -> setUpGame(primaryPane));
		primaryPane.heightProperty().addListener(e -> setUpGame(primaryPane));
	}

	private void setUpGame(Pane primaryPane) {
		primaryPane.getChildren().clear();
		double boardSize[] = getBoardSize(primaryPane);
		double[] offset = getCenteredBoardCorner(primaryPane, boardSize);
		Canvas c = new Canvas(boardSize[0], boardSize[1]);
		primaryPane.getChildren().add(c);
		c.setLayoutX(offset[0]);
		c.setLayoutY(offset[1]);
		GraphicsContext gc = c.getGraphicsContext2D();
		gc.setFill(Color.RED);
		gc.fillRect(0, 0, boardSize[0], boardSize[1]);
	}

	/** Gets the size of the game board based on the size of the pane dedicated
	 * to it
	 * 
	 * @param p
	 *            Pane dedicated to displaying the board
	 * @return {board width, board hight} */
	private static double[] getBoardSize(Pane p) {
		double minSize = Math.min(p.getHeight(), p.getWidth());
		return new double[] { minSize, minSize };
	}

	/** Gets the location of the top leftmost pixel of the game board in
	 * relation to <code>p</code> if a square with dimensions
	 * <code>boardSize</code> were to be centered in <code>p</code>.
	 * 
	 * @param p
	 *            Pane dedicated to displaying the board
	 * @param boardSize
	 *            Decided upon size of the board in form {board width, board
	 *            height}
	 * @return Location of game board in <code>p</code> in form {x offset, y
	 *         offset} (both positive values) */
	private static double[] getCenteredBoardCorner(Pane p, double[] boardSize) {
		double extraWidth = p.getWidth() - boardSize[0];
		double extraHeight = p.getHeight() - boardSize[1];
		return new double[] { extraWidth / 2, extraHeight / 2 };
	}
}
