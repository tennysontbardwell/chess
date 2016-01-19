package Tennyson_T_Bardwell.BasicChessGame.model;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.Property;

import static Tennyson_T_Bardwell.BasicChessGame.model.Direction.DirectionKind.*;
import static Tennyson_T_Bardwell.BasicChessGame.model.Direction.*;
import static Tennyson_T_Bardwell.BasicChessGame.model.Direction.MapDirection.*;
import Tennyson_T_Bardwell.BasicChessGame.model.MoveOption;

public class PieceMoves {
	private List<MoveOption> moves;
	private Player p;
	private Board b;
	private Coordinate c;
	private int turn;

	private boolean classInvariant() {
		if (moves.size() > 0) {
			for (MoveOption m : moves) {
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
	public static List<MoveOption> getMoves(Board b, Coordinate c, int turn) {
		if (b.tileProperty(c) == null || b.tileProperty(c).getValue() == null) {
			return new ArrayList<>(0);
		}
		Tile t = b.tileProperty(c).getValue();
		PieceMoves pm = new PieceMoves();
		pm.b = b;
		pm.p = t.player;
		pm.c = c;
		pm.turn = turn;
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
		assert (classInvariant());
		for (Direction d : MapDirection.getAll(null)) {
			Coordinate newC = c.performMovement(d, p, 1);
			Property<Tile> t = b.tileProperty(newC);
			if (t == null) {
				continue;
			} else if (t.getValue() == null) {
				moves.add(new MoveOption(c, newC));
			} else if (t.getValue().player != p) {
				moves.add(new MoveOption(c, newC));
			}
		}
		// castling
		if (b.lastMoveOfPieceAt(c) == 0) {
			Direction l = Direction.get(WEST);
			Coordinate lRook = castleGetRookLoc(l);
			if (b.lastMoveOfPieceAt(c) == 0) {
				if (checkCastlingIntermediateSpace(c, lRook, l)) {
					Coordinate kingEnd = c.performMovement(l, p, 2);
					Coordinate rookEnd = c.performMovement(l, p, 1);
					moves.add(MoveOption.castle(c, kingEnd, lRook, rookEnd));
				}
			}
			Direction r = Direction.get(EAST);
			Coordinate rRook = castleGetRookLoc(r);
			if (b.lastMoveOfPieceAt(rRook) == 0) {
				if (checkCastlingIntermediateSpace(c, rRook, r)) {
					Coordinate kingEnd = c.performMovement(r, p, 2);
					Coordinate rookEnd = c.performMovement(r, p, 1);
					moves.add(MoveOption.castle(c, kingEnd, rRook, rookEnd));
				}
			}
		}
		assert (classInvariant());
	}

	private Coordinate castleGetRookLoc(Direction d) {
		if (p == Player.WHITE ^ d.equals(Direction.get(WEST))) {
			return new Coordinate(7, c.y);
		} else {
			return new Coordinate(0, c.y);
		}
	}

	private boolean checkCastlingIntermediateSpace(Coordinate start,
			Coordinate end, Direction d) {
		for (Coordinate c = start.performMovement(d, p, 1); !c.equals(end); c =
				c.performMovement(d, p, 1)) {
			if (b.tileProperty(c).getValue() != null)
				return false;
		}
		return true;
	}

	private void queenMoves() {
		assert (classInvariant());
		rookMoves();
		bishopMoves();
		assert (classInvariant());
	}

	private void rookMoves() {
		assert (classInvariant());
		for (Direction d : MapDirection.getAll(ROOKLIKE)) {
			getMovesInDir(d);
		}
		assert (classInvariant());
	}

	private void knightMoves() {
		assert (classInvariant());
		for (int x = -2; x <= 2; x++) {
			for (int y = -2; y <= 2; y++) {
				if (x == 0 || y == 0)
					continue;
				if (Math.abs(y) == Math.abs(x))
					continue;
				Direction d = new Direction(x, y);
				Coordinate target = c.performMovement(d, this.p, 1);
				Property<Tile> p = b.tileProperty(target);
				if (p == null)
					continue;
				if (p.getValue() != null && p.getValue().player == this.p)
					continue;
				moves.add(new MoveOption(c, target));
			}
		}
		assert (classInvariant());
	}

	private void bishopMoves() {
		assert (classInvariant());
		for (Direction d : MapDirection.getAll(BISHOPLIKE)) {
			getMovesInDir(d);
		}
		assert (classInvariant());
	}

	private void pawnMoves() {
		assert (classInvariant());
		// ****forward march
		Direction dForw = Direction.get(MapDirection.NORTH);
		Coordinate forw = c.performMovement(dForw, p, 1);
		Property<Tile> t = b.tileProperty(forw);
		if (t != null && t.getValue() == null) {
			moves.add(new MoveOption(c, forw));
			// *****double move initial
			if (b.lastMoveOfPieceAt(c) == 0) {
				Coordinate forw2 = c.performMovement(dForw, p, 2);
				Property<Tile> tt = b.tileProperty(forw2);
				if (tt != null && tt.getValue() == null) {
					moves.add(MoveOption.bigPawnMove(c, forw2, forw));
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
						moves.add(new MoveOption(c, newC));
				} else {
					// ****en passant
					Coordinate takeLoc = b.isEnPassant(newC, p, turn);
					if (takeLoc != null) {
						moves.add(
								MoveOption.moveWithEnPassant(c, newC, takeLoc));
					}
				}
			}
		}
		assert (classInvariant());
	}

	private void getMovesInDir(Direction d) {
		assert (classInvariant());
		for (int i = 1;; i++) {
			Coordinate coord = c.performMovement(d, p, i);
			Property<Tile> t = b.tileProperty(coord);
			if (t == null) {
				// out of bounds
				break;
			} else if (t.getValue() == null) {
				// empty tile
				moves.add(new MoveOption(c, coord));
			} else if (t.getValue().player == p) {
				// player's own piece
				break;
			} else {
				// enemey piece
				moves.add(new MoveOption(c, coord));
				break;
			}
		}
		assert (classInvariant());
	}
}
