package Tennyson_T_Bardwell.BasicChessGame.model;

/** Representation of a single move on the board where either a piece moves from
 * <code>start</code> to <code>end</code> (in which case
 * <code>type == null</code>) or a new piece of <code>PieceType piece</code> is
 * placed on <code>end</code> (in which case <code>start == null</code>).
 * 
 * @author tbTennyson */
public class SingleMove implements Move {
	public final Coordinate start;
	public final Coordinate end;

	public SingleMove(Coordinate start, Coordinate end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public void apply(Board b, int turn) {
		Tile mainT = b.tileProperty(start).getValue();
		b.removePlace(start);
		b.placePiece(end, mainT.type, mainT.player, turn);
	}

	@Override
	public String toString() {
		return start.toString() + " to " + end.toString();
	}
}