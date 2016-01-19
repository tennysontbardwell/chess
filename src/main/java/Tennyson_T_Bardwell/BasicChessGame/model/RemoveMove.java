package Tennyson_T_Bardwell.BasicChessGame.model;

public class RemoveMove implements Move {
	public final Coordinate loc;// location to take piece from

	/** Creates a new representation of taking a piece from the board.
	 * 
	 * @param locationToTake
	 *            The location of the piece to take from the board. */
	public RemoveMove(Coordinate locationToTake) {
		loc = locationToTake;
	}

	@Override
	public void apply(Board b, int turn) {
		b.removePlace(loc);
	}

	@Override
	public String toString() {
		return "Take " + loc.toString();
	}

}
