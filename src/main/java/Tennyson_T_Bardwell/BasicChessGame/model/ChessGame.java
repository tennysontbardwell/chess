package Tennyson_T_Bardwell.BasicChessGame.model;

import java.util.ArrayList;
import java.util.List;

public class ChessGame {
	private Player currentTurn;

	private List<Turn> history = new ArrayList<>();

	/** @return The player who is currently able to make a move. */
	public Player currentTurn() {
		return currentTurn;
	}
	
	/** All the legal moves that the play who's turn it currently is can make.
	 * 
	 * @return The set of all legal moves, or null if that set is empty */
	public Move[] legalMoves() {
		// TODO
		return null;
	}
	
	/** Attempts to execute <code>move</code>. If it is a legal move
	 * (considering CHECK conditions, current player's turn, and the piece in
	 * question and board conditions) then it is executed and true is returned,
	 * else nothing happens and false is returned.
	 * 
	 * @param move
	 *            Move to attempt to execute.
	 * @return true if <code>move</code> is legal. */
	public boolean makeMove(Move move) {
		// TODO
		return false;
	}
	
	/** Reverts back to the state of the board <code>numberOfTurns</code> ago.
	 * 
	 * <p>
	 * If <code>numberOfTurns == 0</code> then there will be no change.
	 * 
	 * <p>
	 * If <code>numberOfTurns == 1</code> then the last move by one player will
	 * be undo.
	 * 
	 * <p>
	 * If <code>numberOfTurns == 2</code> then the last move of both players
	 * will be undone. etc.
	 * 
	 * @param numberOfTurns
	 *            the number of turns to undo */
	public void undo(int numberOfTurns) {
		// TODO
	}
}
