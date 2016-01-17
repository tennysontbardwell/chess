package Tennyson_T_Bardwell.BasicChessGame.model;

public class Turn extends Move {
	class PawnPromotion {
		public Coordinate location;
		public PieceType promotedTo;
	}

	public Move move;
	public PawnPromotion promotion;
}
