package Tennyson_T_Bardwell.BasicChessGame.model;

/** A representation of a tile of the board for distribution outside of the
 * board class. {@link Tile#player} and {@link Tile#player} are never
 * <code>null</code>, instead an empty tile is represented by a null
 * <code>Tile</code> object.
 * 
 * @author tbTennyson */
public class Tile {
	final public PieceType type;
	final public Player player;

	public Tile(PieceType type, Player player) {
		this.type = type;
		this.player = player;
	}
}