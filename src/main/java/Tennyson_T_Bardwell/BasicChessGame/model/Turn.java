package Tennyson_T_Bardwell.BasicChessGame.model;

public class Turn {
	class PawnPromotion {
		public Coordinate location;
		public PieceType promotedTo;
	}

	public final MoveOption move;
	public final PawnPromotion promotion;
	
	public Turn(MoveOption m){
		this.move = m;
		promotion = null;
	}
}
