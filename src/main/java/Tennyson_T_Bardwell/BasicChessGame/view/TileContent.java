package Tennyson_T_Bardwell.BasicChessGame.view;

import java.util.List;

import Tennyson_T_Bardwell.BasicChessGame.model.Board;
import Tennyson_T_Bardwell.BasicChessGame.model.Board.Tile;
import Tennyson_T_Bardwell.BasicChessGame.model.Coordinate;
import Tennyson_T_Bardwell.BasicChessGame.model.Move;
import Tennyson_T_Bardwell.BasicChessGame.model.Player;
import javafx.beans.property.Property;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class TileContent {
	private final Pane p;
	private final Coordinate tile;
	private final double[] size;
	private final Board board;

	public TileContent(Pane p, Coordinate tile, Board board, double[] size) {
		this.p = p;
		this.tile = tile;
		this.board = board;
		this.size = size;
		board.tileProperty(tile).addListener(e -> drawContent());
		drawContent();
		p.setOnMouseClicked(e -> {
			List<Move> moves = board.moves(tile);
			System.out.println("***********************");
			for (Move m : moves) {
				System.out.println(m.toString());
			}
		});
	}

	private void drawContent() {
		Tile t = board.tileProperty(tile).getValue();
		if (t == null)
			return;
		PieceImageFactory set = new StandardPieceIcons();
		Image i;
		if (t.player == Player.WHITE) {
			switch (t.type) {
			case BISHOP:
				i = set.whiteBishop();
				break;
			case KING:
				i = set.whiteKing();
				break;
			case KNIGHT:
				i = set.whiteKnight();
				break;
			case PAWN:
				i = set.whitePawn();
				break;
			case QUEEN:
				i = set.whiteQueen();
				break;
			case ROOK:
				i = set.whiteRook();
				break;
			default:
				throw new Error();
			}
		} else {
			switch (t.type) {
			case BISHOP:
				i = set.blackBishop();
				break;
			case KING:
				i = set.blackBishop();
				break;
			case KNIGHT:
				i = set.blackKnight();
				break;
			case PAWN:
				i = set.blackPawn();
				break;
			case QUEEN:
				i = set.blackQueen();
				break;
			case ROOK:
				i = set.blackRook();
				break;
			default:
				throw new Error();
			}
		}
		ImageView v = new ImageView();
		v.setImage(i);
		v.setFitHeight(size[1]);
		v.setFitWidth(size[0]);
		p.getChildren().add(v);
	}
}
