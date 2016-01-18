package Tennyson_T_Bardwell.BasicChessGame.model;

import java.util.ArrayList;
import java.util.List;

/** A representation of directions on the board relative to a particular player.
 * 
 * @author tbTennyson */
public class Direction {
	public final int x;
	public final int y;

	/** Creates a new object with specific transformations.
	 * 
	 * @param x
	 *            Transformation to the left, relative to a particular player.
	 *            Can be negative to represent rightward movement.
	 * @param y
	 *            Transformation forward, relative to a particular player. Can
	 *            be negative to represent backwards movement. */
	public Direction(int x, int y) {
		// x and y are relative to the white player, they can be reversed by
		// coordinate
		this.x = x;
		this.y = y;
	}

	public static Direction get(MapDirection m) {
		switch (m) {
		case EAST:
			return new Direction(1, 0);
		case NORTH:
			return new Direction(0, 1);
		case NORTHEAST:
			return new Direction(1, 1);
		case NORTHWEST:
			return new Direction(-1, 1);
		case SOUTH:
			return new Direction(0, -1);
		case SOUTHEAST:
			return new Direction(1, -1);
		case SOUTHWEST:
			return new Direction(-1, -1);
		case WEST:
			return new Direction(-1, 0);
		default:
			throw new Error();
		}
	}

	public enum MapDirection {
		//@formatter:off
		NORTH(DirectionKind.ROOKLIKE), 
		EAST(DirectionKind.ROOKLIKE),
		SOUTH(DirectionKind.ROOKLIKE), 
		WEST(DirectionKind.ROOKLIKE),
		NORTHEAST(DirectionKind.BISHOPLIKE),
		SOUTHEAST(DirectionKind.BISHOPLIKE),
		SOUTHWEST(DirectionKind.BISHOPLIKE),
		NORTHWEST(DirectionKind.BISHOPLIKE);
		//@formatter:on

		public final DirectionKind kind;

		private MapDirection(DirectionKind kind) {
			this.kind = kind;
		}

		/** @param kind
		 *            a particular kind of direction to match. Can be null.
		 * @return all MapDirections that match that kind. If null than it
		 *         returns the directions of all MapDirections */
		public static Direction[] getAll(DirectionKind kind) {
			List<Direction> d = new ArrayList<>();
			for (MapDirection dir : MapDirection.values()) {
				if (kind == null || dir.kind == kind)
					d.add(Direction.get(dir));
			}
			return d.toArray(new Direction[1]);
		}
	}

	public enum DirectionKind {
		ROOKLIKE, BISHOPLIKE
	}
}