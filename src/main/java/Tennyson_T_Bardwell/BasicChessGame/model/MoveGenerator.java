package Tennyson_T_Bardwell.BasicChessGame.model;

/** An interface for an object that will generate all legal moves of a piece
 * given the situation.
 * 
 * @author tbTennyson */
public interface MoveGenerator {
	public void setBoard(Board board, Coordinate location);
}
