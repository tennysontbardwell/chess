package Tennyson_T_Bardwell.BasicChessGame.model;

class RecordEnPassantPossible implements Move {
	public final Coordinate takablePawnLocation;
	public final Coordinate realPawnLocation;

	public RecordEnPassantPossible(Coordinate takablePawnLocation,
			Coordinate realPawnLocation) {
		this.takablePawnLocation = takablePawnLocation;
		this.realPawnLocation = realPawnLocation;
	}

	@Override
	public void apply(Board b, int turn) {
		b.setEnPassant(takablePawnLocation, realPawnLocation, turn);
	}

	@Override
	public String toString() {
		return "";
	}
}