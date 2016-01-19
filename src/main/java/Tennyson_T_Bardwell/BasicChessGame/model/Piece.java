package Tennyson_T_Bardwell.BasicChessGame.model;

/** The superclass of all piece classes.
 * 
 * @author tbTennyson */
public class Piece {
	public final PieceType type;
	public final Player player;
	/** The last turn on which this piece was moved. If 0 then the piece has
	 * never been moved */
	public final int lastMoved;

	public Piece(PieceType type, Player player, int lastMoved) {
		this.type = type;
		this.player = player;
		this.lastMoved = lastMoved;
	}

	public Piece(PieceType type, Player player) {
		this(type, player, 0);
	}
}
