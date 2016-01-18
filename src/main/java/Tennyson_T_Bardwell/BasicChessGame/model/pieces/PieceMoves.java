package Tennyson_T_Bardwell.BasicChessGame.model.pieces;

import java.util.ArrayList;
import java.util.List;

import Tennyson_T_Bardwell.BasicChessGame.model.Board;
import Tennyson_T_Bardwell.BasicChessGame.model.Board.Tile;
import Tennyson_T_Bardwell.BasicChessGame.model.Coordinate;
import Tennyson_T_Bardwell.BasicChessGame.model.Direction;
import Tennyson_T_Bardwell.BasicChessGame.model.Move;
import Tennyson_T_Bardwell.BasicChessGame.model.Player;
import javafx.beans.property.Property;
import Tennyson_T_Bardwell.BasicChessGame.model.Direction.MapDirection;
import static Tennyson_T_Bardwell.BasicChessGame.model.Direction.DirectionKind.*;

import static Tennyson_T_Bardwell.BasicChessGame.model.Direction.*;

public class PieceMoves {
	private List<Move> moves;
	private Player p;
	private Board b;
	private Coordinate c;

	private boolean classInvariant() {
		if (moves.size() > 0) {
			for (Move m : moves) {
				assert (m != null);
			}
		}
		return true;
	}

	private PieceMoves() {
		moves = new ArrayList<>();
	}

	/** Gets every legal move (not considering CHECK conditions) for the piece
	 * at <code>Coordinate c</code> on <code>Board b</code>.
	 * 
	 * @param b
	 * @param c
	 *            <b>Checks:</b> a piece exists at <code>c</code>. If it is not
	 *            it returns an empty list.
	 * @return */
	public static List<Move> getMoves(Board b, Coordinate c) {
		if (b.tileProperty(c) == null
				|| b.tileProperty(c).getValue().player == null) {
			return new ArrayList<>(0);
		}
		Tile t = b.tileProperty(c).getValue();
		PieceMoves pm = new PieceMoves();
		pm.b = b;
		pm.p = t.player;
		pm.c = c;
		switch (t.type) {
		case BISHOP:
			pm.bishopMoves();
			break;
		case KING:
			pm.kingMoves();
			break;
		case KNIGHT:
			pm.knightMoves();
			break;
		case PAWN:
			pm.pawnMoves();
			break;
		case QUEEN:
			pm.queenMoves();
			break;
		case ROOK:
			pm.rookMoves();
			break;
		default:
			throw new Error();
		}
		return pm.moves;
	}

	private void kingMoves() {
		assert(classInvariant());
		for (Direction d : MapDirection.getAll(null)) {
			Coordinate newC = c.performMovement(d, p, 1);
			Property<Tile> t = b.tileProperty(newC);
			if (t == null) {
				continue;
			} else if (t.getValue() == null) {
				moves.add(new Move(c, newC));
			} else if (t.getValue().player != p) {
				moves.add(new Move(c, newC));
			}
		}

		// TODO castling
		assert(classInvariant());
	}

	private void queenMoves() {
		assert(classInvariant());
		rookMoves();
		bishopMoves();
		assert(classInvariant());
	}

	private void rookMoves() {
		assert(classInvariant());
		for (Direction d : MapDirection.getAll(ROOKLIKE)) {
			getMovesInDir(d);
		}
		assert(classInvariant());
	}

	private void knightMoves() {
		assert(classInvariant());
		// TODO
		assert(classInvariant());
	}

	private void bishopMoves() {
		assert(classInvariant());
		for (Direction d : MapDirection.getAll(BISHOPLIKE)) {
			getMovesInDir(d);
		}
		assert(classInvariant());
	}

	private void pawnMoves() {
		assert(classInvariant());
		// ****forward march
		Direction dForw = Direction.get(MapDirection.NORTH);
		Coordinate forw = c.performMovement(dForw, p, 1);
		Property<Tile> t = b.tileProperty(forw);
		if (t != null && t.getValue() == null) {
			moves.add(new Move(c, forw));
			// ****double move initial
			if (b.lastMoveOfPieceAt(c) == 0) {
				Coordinate forw2 = c.performMovement(dForw, p, 2);
				Property<Tile> tt = b.tileProperty(forw2);
				if (tt != null && tt.getValue() == null) {
					moves.add(new Move(c, forw2));
				}
			}

		}
		// ****diagonal take
		Direction[] takes =
				new Direction[] { Direction.get(MapDirection.NORTHEAST),
						Direction.get(MapDirection.NORTHWEST) };
		for (Direction d : takes) {
			Coordinate newC = c.performMovement(d, p, 1);
			Property<Tile> tt = b.tileProperty(newC);
			// check in bounds
			if (tt != null) {
				// check isn't empty
				if (tt.getValue() != null) {
					if (tt.getValue().player != p)
						moves.add(new Move(c, newC));
				} else {
					// ****en passant
					if (b.isEnPassant(newC, p)) {
						moves.add(new Move(c, newC));
					}
				}
			}
		}
		assert(classInvariant());
	}

	private void getMovesInDir(Direction d) {
		assert(classInvariant());
		for (int i = 1;; i++) {
			Coordinate coord = c.performMovement(d, p, i);
			Property<Tile> t = b.tileProperty(coord);
			if (t == null) {
				// out of bounds
				break;
			} else if (t.getValue() == null) {
				// empty tile
				moves.add(new Move(c, coord));
			} else if (t.getValue().player == p) {
				// player's own piece
				break;
			} else {
				// enemey piece
				moves.add(new Move(c, coord));
				break;
			}
		}
		assert(classInvariant());
	}
}
