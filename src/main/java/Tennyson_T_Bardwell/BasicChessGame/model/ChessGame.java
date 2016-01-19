package Tennyson_T_Bardwell.BasicChessGame.model;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.Property;

public class ChessGame {
	private Player turnPlayer;
	private final Board board;
	private final List<Turn> history = new ArrayList<>();

	public ChessGame() {
		board = BoardBuilder.getDefaultBoard();
		turnPlayer = Player.WHITE;
	}

	/** @return The player who is currently able to make a move. */
	public Player currentTurn() {
		return turnPlayer;
	}

	/** All legal moves for a piece, considering CHECK conditions and who's turn
	 * it currently is.
	 * 
	 * @param loc
	 *            The location of the piece
	 * @return all moves that piece can immediately do */
	public List<MoveOption> legalMoves(Coordinate loc) {
		Property<Tile> tp = board.tileProperty(loc);
		if (tp == null || tp.getValue() == null)
			return new ArrayList<>(0);
		Tile t = tp.getValue();
		if (t.player != currentTurn())
			return new ArrayList<>(0);
		List<MoveOption> uCk = board.moves(loc); // unchecked
		List<MoveOption> legal = new ArrayList<>(uCk.size());
		uCk.forEach(e -> {
			if (kingSafe(e))
				legal.add(e);
		});
		return legal;
	}

	/** All the legal moves that the play who's turn it currently is can make.
	 * 
	 * @return The set of all legal moves, or null if that set is empty */
	public List<MoveOption> legalMoves() {
		List<MoveOption> legal = new ArrayList<>();
		board.forEachCoord(e -> {
			legal.addAll(legalMoves(e));
		});
		return legal;
	}

	/** Does no checks. Simple applies a move to the board.
	 * 
	 * @param move
	 *            Move to attempt to execute.
	 * @return true if <code>move</code> is legal. */
	public void makeMove(MoveOption move) {
		move.apply(board, history.size() + 1);
		history.add(new Turn(move));
		turnPlayer = turnPlayer.getOther();
		if (board.canGetToKing(turnPlayer.getOther()))
			System.out.println(turnPlayer.getOther().name + " puts "
					+ turnPlayer.name + " in Check");
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

	/** Sees if a move can be done by player p without putting player p in check
	 * (which is illegal)
	 * 
	 * @param m
	 *            Move to consider
	 * @return false if m puts the player in check, and thus is illegal. True if
	 *         it is legal. */
	private boolean kingSafe(MoveOption m) {
		Board alt = board.deepClone();
		m.apply(alt, history.size() + 1);
		if (alt.canGetToKing(turnPlayer.getOther()))
			return false;
		else
			return true;
	}

	/** Does the same as {@link Board#tileProperty(Coordinate)} */
	public Property<Tile> tileProperty(Coordinate c) {
		return board.tileProperty(c);
	}
}
