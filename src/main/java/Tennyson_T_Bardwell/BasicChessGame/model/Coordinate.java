package Tennyson_T_Bardwell.BasicChessGame.model;

import static Tennyson_T_Bardwell.BasicChessGame.model.Direction.*;

import java.util.HashMap;

import Tennyson_T_Bardwell.BasicChessGame.model.Board.EnPassant;
import Tennyson_T_Bardwell.BasicChessGame.util.HelperFunctions;

/** Holds a location on the board, and is the standard way to reference a
 * location on the board
 * 
 * @author tbTennyson */
public class Coordinate {
	final public int x;
	final public int y;

	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/** <b>Creates:</b> the coordinates of relative to this object after
	 * performing a movement in a direction.
	 * 
	 * @param d
	 *            Direction in which to move
	 * @param p
	 *            Player from who's perspective the direction is relative to.
	 * 
	 * @param dist
	 *            Distance; Number of steps to take in that direction. 0 does
	 *            nothing, 1 moves to an adjacent tile.
	 * @return new Coordinate in the direction and distance specified */
	public Coordinate performMovement(Direction d, Player p, int dist) {
		// Changes perspective to that of white's
		if (p == Player.WHITE) {
			return new Coordinate(x + dist * d.x, y + dist * d.y);
		} else {
			return new Coordinate(x - dist * d.x, y - dist * d.y);
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Coordinate) {
			Coordinate other = (Coordinate) obj;
			if (other.x == x && other.y == y)
				return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return x * 1001 + y;
	}

	@Override
	public String toString() {
		return HelperFunctions.toAlphabetic(x) + ", " + (1 + y);
	}
}
