package Tennyson_T_Bardwell.BasicChessGame.model;

public enum Player {
	WHITE("White"), BLACK("Black");

	final public String name;

	private Player(String name) {
		this.name = name;
	}

	public Player getOther() {
		if (this == WHITE)
			return BLACK;
		else
			return WHITE;
	}
}
