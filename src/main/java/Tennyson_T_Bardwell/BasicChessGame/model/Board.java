package Tennyson_T_Bardwell.BasicChessGame.model;

import java.util.ArrayList;
import java.util.List;

/** A class for managing the board of a chess game. This includes the history of
 * the board and all the logic of a chess game, and the current state of the
 * board.
 * 
 * @author tbTennyson */
public class Board {
	/** Internal representation of the board. Empty locations are represented by
	 * null. */
	private Piece[][] board = new Piece[8][8];
	private List<Turn> history = new ArrayList<>();
	private Player currentTurn;

	/** @return the condition of the board if there is a special condition
	 *         defined in {@link BoardCondition}. Otherwise returns null. */
	public BoardCondition boardCondition() {
		// TODO
		return null;
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

	/** @return The player who is currently able to make a move. */
	public Player currentTurn() {
		return currentTurn;
	}

	/** @return Creates: An exact copy of this board which shares no
	 *         dependencies with this instance. */
	public Board deepClone() {
		// TODO
		return null;
	}

	/** All moves that all the pieces of <code>Player player</code> can make,
	 * assuming it is currently their turn, WITHOUT checking for CHECK
	 * conditions. This can be used to calculate legal moves.
	 * 
	 * @param player
	 *            The player to consider. Does not require it to be their turn.
	 * @return The set of <code>moves</code> that <code>Player player</code> can
	 *         make. If the set is empty than returns <code>null</code>. */
	private Move[] moves(Player player) {
		// TODO
		return null;
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

	/** Gets the type of piece at a particular location on the board.
	 * 
	 * @param location
	 *            The coordinate on the board to check.
	 * @return PieceType of the piece, or null if the location is empty. */
	public PieceType pieceAt(Coordinate location) {
		// TODO
		return null;
	}

	/** Gets the move on which a piece was last moved.
	 * <p>
	 * Requires: a piece exists at <code>Coordinate location</code>
	 * 
	 * @param location
	 *            The location of the piece to lookup.
	 * @return turn number that the piece was last moved */
	public int lastMoveOfPieceAt(Coordinate location) {
		// TODO
		return -1;
	}
}
