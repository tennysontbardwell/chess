package Tennyson_T_Bardwell.BasicChessGame.model;

/** The superclass of all piece classes.
 * 
 * @author tbTennyson */
public abstract class Piece {
	public final PieceType type;
	/** The last turn on which this piece was moved. If 0 then the piece has
	 * never been moved */
	public int lastMoved = 0;

	public Piece(PieceType type) {
		this.type = type;
	}

	/** Gets a move generator specialized to this specific piece.
	 * 
	 * @return A move generator for this piece */
	public abstract MoveGenerator getGenerator();
}
