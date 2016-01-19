package Tennyson_T_Bardwell.BasicChessGame.view;

import Tennyson_T_Bardwell.BasicChessGame.model.Tile;
import Tennyson_T_Bardwell.BasicChessGame.model.Player;
import javafx.beans.property.Property;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class TileContent {
	private final Pane p;
	private final Property<Tile> tile;
	private final double[] size;
	private ImageView v;

	public TileContent(Pane p, Property<Tile> tile, double[] size) {
		this.p = p;
		this.tile = tile;
		this.size = size;
		v = new ImageView();
		v.setFitHeight(this.size[1]);
		v.setFitWidth(this.size[0]);
		this.p.getChildren().add(v);
		tile.addListener(e -> drawContent());
		drawContent();
	}

	private void drawContent() {
		Tile t = tile.getValue();
		if (t == null) {
			v.setImage(null);
			return;
		}
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
				i = set.blackKing();
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
		v.setImage(i);

	}
}
