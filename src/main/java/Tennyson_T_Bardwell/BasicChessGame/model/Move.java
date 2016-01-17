package Tennyson_T_Bardwell.BasicChessGame.model;

import java.util.List;

/**
 * Represents all the changes to the board in a single turn, excluding pawn promotions.
 * @author tbTennyson
 *
 */
public class Move {
	public class SingleMove{
		Coordinate start;
		Coordinate end;
	}
	
	List<SingleMove> moves;
}
