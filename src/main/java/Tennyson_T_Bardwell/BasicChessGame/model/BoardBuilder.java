package Tennyson_T_Bardwell.BasicChessGame.model;

import Tennyson_T_Bardwell.BasicChessGame.model.pieces.PieceType;

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
		
		// TODO
	}

	private static void place(Board b, PieceType type, Player p, Coordinate c) {
		if (p == Player.BLACK)
			c = flipCoordiante(c);
		b.placePiece(c, type, p);
	}

	private static Coordinate flipCoordiante(Coordinate coord) {
		return new Coordinate(7 - coord.x, 7 - coord.y);
	}
}