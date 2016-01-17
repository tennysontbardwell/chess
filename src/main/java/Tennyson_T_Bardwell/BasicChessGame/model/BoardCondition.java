package Tennyson_T_Bardwell.BasicChessGame.model;

/** A list of notable conditions after a turn is completed
 * 
 * @author tbTennyson */
public enum BoardCondition {
	/** Check occurs when the king of player who's turn it now is is in
	 * danger. */
	CHECK,

	/** Checkmate occurs when the king is in unpreventable danger. */
	CHECKMATE,

	/** Draw occurs when draw conditions are met as defined in the board */
	DRAW,

	/** Promotion occurs when a pawn has reached the opposite side of the board.
	 * This takes precedent over {@link #CHECK}, but not game ending conditions
	 * like {@link #CHECKMATE} and {@link #DRAW} */
	PROMOTION
}
