package Tennyson_T_Bardwell.BasicChessGame.model;

public class BoardBuilder {
	public static Board getDefaultBoard() {
		Board b = new Board();
		addPlayerPieces(b, Player.WHITE);
		addPlayerPieces(b, Player.BLACK);
		return b;
	}

	public static void addPlayerPieces(Board b, Player player) {
		// pawns
		for (int i = 0; i < 8; i++) {
			Coordinate coord = new Coordinate(i, 1);
			place(b, PieceType.PAWN, player, coord);
		}
		// rooks
		Coordinate leftRook = new Coordinate(0, 0);
		placeWithMirror(b, PieceType.ROOK, player, leftRook);
		// knight
		Coordinate knightLeft = new Coordinate(1, 0);
		placeWithMirror(b, PieceType.KNIGHT, player, knightLeft);
		// bishop
		Coordinate bishopLeft = new Coordinate(2, 0);
		placeWithMirror(b, PieceType.BISHOP, player, bishopLeft);
		// king
		Coordinate king;
		if (player == Player.WHITE) {
			king = new Coordinate(4, 0);
		} else {
			king = new Coordinate(4, 7);
		}
		b.placePiece(king, PieceType.KING, player, 0);
		// queen
		Coordinate queen;
		if (player == Player.WHITE) {
			queen = new Coordinate(3, 0);
		} else {
			queen = new Coordinate(3, 7);
		}
		b.placePiece(queen, PieceType.QUEEN, player, 0);
	}

	private static void place(Board b, PieceType type, Player p, Coordinate c) {
		if (p == Player.BLACK)
			c = flipCoordiante(c);
		b.placePiece(c, type, p, 0);
	}

	private static void placeWithMirror(Board b, PieceType type, Player p,
			Coordinate left) {
		Coordinate right = new Coordinate(7 - left.x, left.y);
		place(b, type, p, left);
		place(b, type, p, right);
	}

	private static Coordinate flipCoordiante(Coordinate coord) {
		return new Coordinate(7 - coord.x, 7 - coord.y);
	}
}