package Tennyson_T_Bardwell.BasicChessGame.model.pieces;

import Tennyson_T_Bardwell.BasicChessGame.model.Player;

/** @author tbTennyson */
public enum PieceType {
	KING("King", 0), QUEEN("Queen", 9), ROOK("Rook", 5), KNIGHT("Knight", 3),
	BISHOP("Bishop", 3), PAWN("Pawn", 1);

	public final String name;
	/** The value to use when calculation the value of taken pieces and pieces
	 * left on the board. The king has a value of 0 because it's value is not
	 * used when calculating either of those things. */
	public final int value;

	private PieceType(String name, int value) {
		this.name = name;
		this.value = value;
	}
}
