package Tennyson_T_Bardwell.BasicChessGame.model;

import java.util.ArrayList;
import java.util.List;

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
			SingleMove sm = new SingleMove();
			sm.start = c[i];
			sm.end = c[i + 1];
			moves.add(sm);
		}
	}

	/** Representation of a single move on the board where either a piece moves
	 * from <code>start</code> to <code>end</code> (in which case
	 * <code>type == null</code>) or a new piece of <code>PieceType piece</code>
	 * is placed on <code>end</code> (in which case <code>start == null</code>).
	 * 
	 * @author tbTennyson */
	public class SingleMove {
		Coordinate start;
		Coordinate end;
		PieceType type;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{ ");
		boolean isFirst = true;
		assert(moves != null);
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
