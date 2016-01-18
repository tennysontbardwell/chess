package Tennyson_T_Bardwell.BasicChessGame.view;

import Tennyson_T_Bardwell.BasicChessGame.model.Board;
import Tennyson_T_Bardwell.BasicChessGame.model.Board.Tile;
import Tennyson_T_Bardwell.BasicChessGame.model.Coordinate;
import javafx.beans.property.Property;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class GamePane {
	private Pane primaryPane;
	private Board board;
	private Pane boardPane;

	private double[] boardSize;
	private double[] tileSize;

	public GamePane(Pane primaryPane, Board board) {
		assert (board != null);
		this.board = board;
		this.primaryPane = primaryPane;
		setUpGame();
		primaryPane.widthProperty().addListener(e -> setUpGame());
		primaryPane.heightProperty().addListener(e -> setUpGame());
	}

	/** Sets up the game board in the {@link #primaryPane} from scratch,
	 * clearing the previous contents if necessary. It adds all drag handlers,
	 * deals with the size of the screen, etc. */
	private void setUpGame() {
		primaryPane.getChildren().clear();
		boardSize = getBoardSize(primaryPane);
		double[] offset = getCenteredBoardCorner(primaryPane, boardSize);
		boardPane = new Pane();
		boardPane.setPrefSize(boardSize[0], boardSize[1]);
		primaryPane.getChildren().add(boardPane);
		boardPane.setLayoutX(offset[0]);
		boardPane.setLayoutY(offset[1]);
		tileSize = tileSize(boardSize);
		drawAllTiles();
	}

	/** Draws all the tiles on the game board */
	private void drawAllTiles() {
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				Coordinate coord = new Coordinate(x, y);
				drawTile(coord, getTileLoc(coord, tileSize));
			}
		}
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

	/** Calculates the size of tiles on the board such when all tiles are drawn
	 * every pixel of the game board is dedicated to exactly one tile.
	 * 
	 * @param boardSize
	 *            Pixels size of the board in form {width, height}
	 * @return Size of each tile in form {width, hight} */
	private static double[] tileSize(double[] boardSize) {
		return new double[] { boardSize[0] / 8, boardSize[1] / 8 };
	}

	/** Gets the location (of the top left corner) of a particular tile on the
	 * game board
	 * 
	 * @param coord
	 *            Tile to find location of
	 * @param tileSize
	 *            Size of every tile in form {width, height}
	 * @return Location of tile on the game board in form {width, height} */
	private static double[] getTileLoc(Coordinate coord, double[] tileSize) {
		double x = coord.x * tileSize[0];
		double y = (7 - coord.y) * tileSize[1];
		return new double[] { x, y };
	}

	/** Draws a tile of the game board and links up drag handelers and update
	 * logic.
	 * 
	 * @param coord
	 *            The
	 * @param location */
	private void drawTile(Coordinate coord, double[] location) {
		Pane p = new Pane();
		p.setPrefSize(tileSize[0], tileSize[1]);
		Rectangle r = new Rectangle(tileSize[0], tileSize[1]);
		p.getChildren().add(r);
		if ((coord.x + coord.y) % 2 == 0) {
			r.setFill(Color.BLACK);
		} else {
			r.setFill(Color.WHITE);
		}
		assert (board != null);
		new TileContent(p, coord, board, tileSize);
		boardPane.getChildren().add(p);
		p.setLayoutX(location[0]);
		p.setLayoutY(location[1]);
	}
}
