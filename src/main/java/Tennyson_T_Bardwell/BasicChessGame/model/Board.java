package Tennyson_T_Bardwell.BasicChessGame.model;

import static Tennyson_T_Bardwell.BasicChessGame.model.PieceMoves.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.property.Property;

/** A class for managing the board of a chess game. This includes the history of
 * the board and all the logic of a chess game, and the current state of the
 * board.
 * 
 * @author tbTennyson */
class Board {
	/** Internal representation of the board. Empty locations are represented by
	 * <code>null</code>. */
	private InternalTile[][] board = new InternalTile[8][8];
	private EnPassant previousEnPassant;
	int turn = 0;

	public Board() {
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[0].length; y++) {
				board[x][y] = new InternalTile();
			}
		}
	}

	public void forEachCoord(Consumer<Coordinate> consumer) {
		for (Coordinate c : allCoords()) {
			consumer.accept(c);
		}
	}

	private InternalTile getTile(Coordinate coord) {
		try {
			return board[coord.x][coord.y];
		} catch (ArrayIndexOutOfBoundsException e) {
			return null;
		}
	}

	private boolean isInBoard(Coordinate location) {
		if (getTile(location) == null)
			return false;
		else
			return true;
	}

	private Coordinate[] allCoords() {
		Coordinate[] coords = new Coordinate[64];
		for (int i = 0; i < 64; i++) {
			coords[i] = new Coordinate(i / 8, i % 8);
		}
		return coords;
	}

	/** @return the condition of the board if there is a special condition
	 *         defined in {@link BoardCondition}. Otherwise returns null. */
	public BoardCondition boardCondition() {
		// TODO
		return null;
	}

	/** @return Creates: An exact copy of this board which shares no
	 *         dependencies with this instance. */
	public Board deepClone() {
		Board b = new Board();
		for (Coordinate c : allCoords()) {
			Piece p = getTile(c).piece();
			if (p != null) {
				b.placePiece(c, p.type, p.player, p.lastMoved);
			}
		}
		b.turn = turn;
		b.previousEnPassant = previousEnPassant;
		return b;
	}

	/** All moves that all the pieces of <code>Player player</code> can make,
	 * assuming it is currently their turn, WITHOUT checking for CHECK
	 * conditions. This can be used to calculate legal moves.
	 * 
	 * @param player
	 *            The player to consider. Does not require it to be their turn.
	 * @return The set of <code>move</code> that <code>Player player</code> can
	 *         make. */
	private List<MoveOption> moves(Player player) {
		List<MoveOption> moves = new ArrayList<>();
		for (Coordinate c : allCoords()) {
			InternalTile t = getTile(c);
			if (t.piece != null && t.piece.player == player) {
				moves.addAll(getMoves(this, c, turn));
			}
		}
		return moves;
	}

	/** Sees if a piece can get to a location in a single move based on the
	 * board right now
	 * 
	 * @param p
	 *            The player who can make the move to get to the location
	 * @param c
	 *            The location the player is trying to get to
	 * @return True if a single move can get that player there. */
	public boolean canGetTo(Player p, Coordinate c) {
		for (MoveOption m : moves(p)) {
			if (m.end().equals(c)) {
				return true;
			}
		}
		return false;
	}

	/** Gets the king
	 * 
	 * @param p
	 *            The player's who's king is desired
	 * @return The coordinate of the king, or null if no king existis for player
	 *         <code>p</code>. */
	private Coordinate findKing(Player p) {
		for (Coordinate c : allCoords()) {
			Piece pi = getTile(c).piece;
			if (pi != null && pi.type == PieceType.KING && pi.player == p)
				return c;
		}
		return null;
	}

	/** Sees if a player can get the other's king
	 * 
	 * @param p
	 *            The player who's trying to get the king of the other
	 * @return True if player <code>p</code> can get the other's king */
	public boolean canGetToKing(Player p) {
		Coordinate goal = findKing(p.getOther());
		return canGetTo(p, goal);
	}

	/** Gets the moves that a piece can make.
	 * 
	 * @param location
	 *            The location of the piece.
	 * @return All moves that a piece can make, without considering CHECK
	 *         conditions. */
	public List<MoveOption> moves(Coordinate location) {
		return getMoves(this, location, turn);
	}

	/** Sees if a piece can be theoretically taken if it was at a location
	 * 
	 * @param p
	 *            The player attempting to take the piece
	 * @param c
	 *            The coordinate of the theoretical piece.
	 * @return true if the piece can be taken */
	public boolean canTakeAt(Player p, Coordinate c) {
		return false;
	}

	/** Gets the type of piece at a particular location on the board.
	 * 
	 * @param location
	 *            The coordinate on the board to check. <b>Checks:</b>
	 *            <code>location</code> is a valid tile.
	 * @return The property of the piece, or null if the location is out of the
	 *         board. */
	public Property<Tile> tileProperty(Coordinate location) {
		InternalTile t = getTile(location);
		if (t == null)
			return null;
		else
			return t.getObvservableProperty();
	}

	/** Gets the move on which a piece was last moved.
	 * <p>
	 * Requires: a piece exists at <code>Coordinate location</code>
	 * 
	 * @param location
	 *            The location of the piece to lookup.
	 * @return turn number that the piece was last moved. Returns <code>0</code>
	 *         if the piece has never been moved. */
	public int lastMoveOfPieceAt(Coordinate location) {
		return getTile(location).piece.lastMoved;
	}

	/** Tells the board that a pawn can move to <code>Coordinate c</code> to
	 * perform an en passant against <code>Player p</code>.
	 * 
	 * @param c
	 * @param p
	 * @param turn
	 *            When this move occurred */
	void setEnPassant(Coordinate takablePawnLocation,
			Coordinate realPawnLocation, int turn) {
		previousEnPassant =
				new EnPassant(takablePawnLocation, realPawnLocation, turn);
	}

	/** Checks if <code>Player p</code> can perform an en passant by moving a
	 * pawn to <code>Coordinate c</code>.
	 * 
	 * @param c
	 * @param p
	 * @return the Coordinate of the pawn to take, or null if the enPassant move
	 *         cannot be performed as specified */
	public Coordinate isEnPassant(Coordinate c, Player p, int turn) {
		// no previous pawn moved two spots
		if (previousEnPassant == null)
			return null;
		Coordinate take = previousEnPassant.takableLocation;
		// take is not at the coordinate asked about in args
		if (!take.equals(c))
			return null;
		// the previous pawn moved too long ago
		if (previousEnPassant.turn + 1 < turn)
			return null;
		// the previous pawn to move was of the same color
		if (getTile(previousEnPassant.realPawnLocation).piece.player == p)
			return null;

		// can move there
		return previousEnPassant.realPawnLocation;
	}

	/** Puts a piece on the board without any checks. <b>Requires:</b>
	 * <code>location</code> is in the board.
	 * 
	 * @param location
	 *            Where to place the piece. <b>Requires:</b> is on the board.
	 * @param type
	 *            What kind of piece to place
	 * @param player
	 *            What player to piece belongs to
	 * @param turn
	 *            What value to assign to {@link Piece#lastMoved} */
	void placePiece(Coordinate location, PieceType type, Player player,
			int turn) {
		Piece piece = new Piece(type, player, turn);
		assert (isInBoard(location)) : "Tried to put a " + player.name + " "
				+ type.name + " to " + location.toString();
		getTile(location).piece(piece);
	}

	/** Removes the piece at <code>location</code>, if a piece exists.
	 * 
	 * @param location
	 *            The location to remove the piece from */
	void removePlace(Coordinate location) {
		getTile(location).piece(null);
	}

	private class InternalTile {
		/** If <code>piece == null</code> then this is an empty tile. */
		private Piece piece;
		private Property<Tile> property = null;

		public void piece(Piece piece) {
			this.piece = piece;
			if (property != null) {
				if (piece == null) {
					property.setValue(null);
				} else {
					property.setValue(new Tile(piece.type, piece.player));
				}
			}
		}

		public Piece piece() {
			return piece;
		}

		public Property<Tile> getObvservableProperty() {
			if (property == null) {
				property = new ObjectPropertyBase<Tile>() {

					@Override
					public Object getBean() {
						return Board.this;
					}

					@Override
					public String getName() {
						return "";
					}
				};
				piece(piece());// refreshes to set up observable property
			}
			return property;
		}
	}

	public class EnPassant {
		public final Coordinate takableLocation;
		public final Coordinate realPawnLocation;
		public final int turn;

		public EnPassant(Coordinate takableLocation,
				Coordinate realPawnLocation, int turn) {
			this.takableLocation = takableLocation;
			this.realPawnLocation = realPawnLocation;
			this.turn = turn;
		}
	}
}
