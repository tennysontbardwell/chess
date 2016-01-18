package Tennyson_T_Bardwell.BasicChessGame.model;

import java.util.ArrayList;
import java.util.List;

import Tennyson_T_Bardwell.BasicChessGame.model.Board.Tile;
import Tennyson_T_Bardwell.BasicChessGame.model.pieces.PieceType;

/** Represents all the changes to the board in a single turn, excluding pawn
 * promotions.
 * 
 * @author tbTennyson */
public class Move {
	private List<SingleMove> moves;

	public Move(Coordinate... c) {
		moves = new ArrayList<>(1);
		if (c.length % 2 == 1 || c.length == 0)
			throw new IllegalArgumentException();
		for (int i = 0; i < c.length; i += 2) {
			SingleMove sm = new SingleMove(c[i], c[i + 1]);
			moves.add(sm);
		}
	}

	/** <b>Modifies:</b> the board as though this move was applied to it.
	 * 
	 * @param b */
	public void apply(Board b) {
		for (SingleMove sm : moves) {
			Player p;
			PieceType ty;
			if (sm.start != null) {
				Tile t = b.tileProperty(sm.start).getValue();
				if (t == null) {
					throw new IllegalArgumentException();
				}
				p = t.player;
				ty = t.type;
				b.removePlace(sm.start);
			}
			else {
				p = sm.player;
				ty = sm.type;
			}
			b.placePiece(sm.end, ty, p);
		}
	}

	public SingleMove[] moves() {
		return moves.toArray(new SingleMove[1]);
	}

	/** Representation of a single move on the board where either a piece moves
	 * from <code>start</code> to <code>end</code> (in which case
	 * <code>type == null</code>) or a new piece of <code>PieceType piece</code>
	 * is placed on <code>end</code> (in which case <code>start == null</code>).
	 * 
	 * @author tbTennyson */
	public class SingleMove {
		public final Coordinate start;
		public final Coordinate end;
		public final PieceType type;
		public final Player player;

		private SingleMove(Coordinate start, Coordinate end) {
			this.start = start;
			this.end = end;
			this.type = null;
			this.player = null;
		}

		private SingleMove(Coordinate end, PieceType type, Player player) {
			this.end = end;
			this.start = null;
			this.type = type;
			this.player = player;
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{ ");
		boolean isFirst = true;
		assert (moves != null);
		for (SingleMove sm : moves) {
			if (isFirst) {
				isFirst = false;
			} else {
				sb.append(",\n");
			}
			if (sm.start != null) {
				sb.append(sm.start.toString() + " to " + sm.end.toString());
			} else {
				sb.append(sm.type.name + " to " + sm.end.toString());
			}
		}
		sb.append(" }");
		return sb.toString();
	}
}
