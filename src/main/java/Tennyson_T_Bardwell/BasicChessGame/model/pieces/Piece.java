package Tennyson_T_Bardwell.BasicChessGame.model.pieces;

import Tennyson_T_Bardwell.BasicChessGame.model.Player;

/** The superclass of all piece classes.
 * 
 * @author tbTennyson */
public class Piece {
	public final PieceType type;
	public final Player player;
	/** The last turn on which this piece was moved. If 0 then the piece has
	 * never been moved */
	public int lastMoved = 0;

	public Piece(PieceType type, Player player) {
		this.type = type;
		this.player = player;
	}
}
