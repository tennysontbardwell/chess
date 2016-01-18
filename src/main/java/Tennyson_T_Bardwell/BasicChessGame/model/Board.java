package Tennyson_T_Bardwell.BasicChessGame.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import Tennyson_T_Bardwell.BasicChessGame.model.pieces.Piece;
import Tennyson_T_Bardwell.BasicChessGame.model.pieces.PieceType;
import static Tennyson_T_Bardwell.BasicChessGame.model.pieces.PieceMoves.*;
import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.property.Property;

/** A class for managing the board of a chess game. This includes the history of
 * the board and all the logic of a chess game, and the current state of the
 * board.
 * 
 * @author tbTennyson */
public class Board {
	/** Internal representation of the board. Empty locations are represented by
	 * <code>null</code>. */
	private InternalTile[][] board = new InternalTile[8][8];

	private EnPassant previousEnPassant;

	public Board() {
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[0].length; y++) {
				board[x][y] = new InternalTile();
			}
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
				b.placePiece(c, p.type, p.player);
			}
		}
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
	private List<Move> moves(Player player) {
		List<Move> moves = new ArrayList<>();
		for (Coordinate c : allCoords()) {
			if (getTile(c).piece.player == player) {
				moves.addAll(getMoves(this, c));
			}
		}
		return moves;
	}

	/** Gets the moves that a piece can make.
	 * 
	 * @param location
	 *            The location of the piece.
	 * @return All moves that a piece can make, without considering CHECK
	 *         conditions. */
	public List<Move> moves(Coordinate location) {
		return getMoves(this, location);
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
	 * @param p */
	public void setEnPassant(Coordinate c, Player p) {
		assert (previousEnPassant == null);
		previousEnPassant = new EnPassant(c, p);
	}

	/** Checks if <code>Player p</code> can perform an en passant by moving a
	 * pawn to <code>Coordinate c</code>.
	 * 
	 * @param c
	 * @param p
	 * @return True if the player can */
	public boolean isEnPassant(Coordinate c, Player p) {
		if (previousEnPassant != null
				&& previousEnPassant.takableLocation.equals(c)
				&& previousEnPassant.takablePlayer != p)
			return true;
		else
			return false;
	}

	/** Puts a piece on the board without any checks. <b>Requires:</b>
	 * <code>location</code> is in the board.
	 * 
	 * @param location
	 *            <b>Requires:</b> is on the board.
	 * @param type
	 * @param player */
	public void placePiece(Coordinate location, PieceType type, Player player) {
		Piece piece = new Piece(type, player);
		assert (isInBoard(location)) : "Tried to put a " + player.name + " "
				+ type.name + " to " + location.toString();
		getTile(location).piece(piece);
	}

	/** Removes the piece at <code>location</code>, if a piece exists.
	 * 
	 * @param location
	 *            The location to remove the piece from */
	public void removePlace(Coordinate location) {
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

	/** A representation of a tile of the board for distribution outside of the
	 * board class. {@link Tile#player} and {@link Tile#player} are never
	 * <code>null</code>, instead an empty tile is represented by a null
	 * <code>Tile</code> object.
	 * 
	 * @author tbTennyson */
	public class Tile {
		final public PieceType type;
		final public Player player;

		public Tile(PieceType type, Player player) {
			this.type = type;
			this.player = player;
		}
	}

	public class EnPassant {
		public final Coordinate takableLocation;
		public final Player takablePlayer;

		public EnPassant(Coordinate takableLocation, Player takablePlayer) {
			this.takableLocation = takableLocation;
			this.takablePlayer = takablePlayer;
		}
	}
}
