package Tennyson_T_Bardwell.BasicChessGame.model;

/** Represents all the changes to the board in a single turn, excluding pawn
 * promotions. It represents the smallest choice a player can make, a move
 * option.
 * 
 * @author tbTennyson */
public class MoveOption implements Move {
	private final SingleMove main;
	private Move[] aux;// Auxiliary moves

	/** Creates a new move comprised only of start ==> end movements.
	 *
	 * @param start
	 *            Movement from
	 * @param end
	 *            Movement to */
	public MoveOption(Coordinate start, Coordinate end) {
		main = new SingleMove(start, end);
	}

	/** <b>Creates</b> a new move option that represents an en passant move
	 * 
	 * @param start
	 *            Same as in {@link #MoveOption(Coordinate, Coordinate)}
	 * @param end
	 *            Same as in {@link #MoveOption(Coordinate, Coordinate)}
	 * @param pawnToRemove
	 *            To location of the pawn that was captured.
	 * @return A new instance of <code>MoveOption</code>. */
	public static MoveOption moveWithEnPassant(Coordinate start, Coordinate end,
			Coordinate pawnToRemove) {
		MoveOption m = new MoveOption(start, end);
		m.aux = new Move[] { new RemoveMove(pawnToRemove) };
		return m;
	}

	/** <b>Creates</b> a new <code>MoveOption</code> that represents a pawn
	 * which takes two steps.
	 * 
	 * @param start
	 *            Same as in {@link #MoveOption(Coordinate, Coordinate)}
	 * @param end
	 *            Same as in {@link #MoveOption(Coordinate, Coordinate)}
	 * @param between
	 *            To coordinate as though the pawn had moved one spot.
	 * @return A new instance of <code>MoveOption</code>. */
	public static MoveOption bigPawnMove(Coordinate start, Coordinate end,
			Coordinate between) {
		MoveOption m = new MoveOption(start, end);
		m.aux = new Move[] { new RecordEnPassantPossible(between, end) };
		return m;
	}

	/** Gets the (start) primary coordinate associated with this move option,
	 * from which the user would initiate picking this move option.
	 * 
	 * @return The start coordinate */
	public Coordinate start() {
		return main.start;
	}

	/** Gets the (end) primary coordinate associated with this move option, from
	 * which the user would complete picking this move option.
	 * 
	 * @return The start coordinate */
	public Coordinate end() {
		return main.end;
	}

	@Override
	public void apply(Board b, int turn) {
		b.turn++;
		main.apply(b, turn);
		if (aux != null) {
			for (Move m : aux) {
				m.apply(b, turn);
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(main.toString());
		if (aux != null) {
			for (Move m : aux) {
				sb.append(",\n");
				sb.append(m.toString());
			}
		}
		return sb.toString();
	}
}
