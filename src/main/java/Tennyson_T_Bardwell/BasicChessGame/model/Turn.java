package Tennyson_T_Bardwell.BasicChessGame.model;

import Tennyson_T_Bardwell.BasicChessGame.model.pieces.PieceType;

public class Turn extends Move {
	class PawnPromotion {
		public Coordinate location;
		public PieceType promotedTo;
	}

	public Move move;
	public PawnPromotion promotion;
}
