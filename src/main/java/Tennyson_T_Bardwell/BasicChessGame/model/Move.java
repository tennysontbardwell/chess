package Tennyson_T_Bardwell.BasicChessGame.model;

/** Representation of a small change to the chess board.
 * 
 * @author tbTennyson */
public interface Move {
	/** Applies this change to the board
	 * 
	 * @param b
	 *            <b>Modifies:</b> the board to reflect this change.
	 * @param turn
	 *            Turn number to log this change as */
	public void apply(Board b, int turn);

	/** Gets human readable representation of this move.
	 * 
	 * @return Gets a human readable representation of this move */
	@Override
	public String toString();
}
