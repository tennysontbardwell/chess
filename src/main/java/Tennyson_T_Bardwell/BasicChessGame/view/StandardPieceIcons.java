package Tennyson_T_Bardwell.BasicChessGame.view;

import javafx.scene.image.Image;

public class StandardPieceIcons implements PieceImageFactory {
	protected String prefix =
			"/Tennyson_T_Bardwell/BasicChessGame/view/resources/PieceIcons/";

	@Override
	public Image blackKing() {
		return new Image(getClass().getResourceAsStream(prefix + "BkKing.png"));
	}

	@Override
	public Image blackQueen() {
		return new Image(
				getClass().getResourceAsStream(prefix + "BkQueen.png"));
	}

	@Override
	public Image blackRook() {
		return new Image(getClass().getResourceAsStream(prefix + "BkRook.png"));
	}

	@Override
	public Image blackBishop() {
		return new Image(
				getClass().getResourceAsStream(prefix + "BkBishop.png"));
	}

	@Override
	public Image blackKnight() {
		return new Image(
				getClass().getResourceAsStream(prefix + "BkKight.png"));
	}

	@Override
	public Image blackPawn() {
		return new Image(getClass().getResourceAsStream(prefix + "BkPawn.png"));
	}

	@Override
	public Image whiteKing() {
		return new Image(getClass().getResourceAsStream(prefix + "WtKing.png"));
	}

	@Override
	public Image whiteQueen() {
		return new Image(
				getClass().getResourceAsStream(prefix + "WtQueen.png"));
	}

	@Override
	public Image whiteRook() {
		return new Image(getClass().getResourceAsStream(prefix + "WtRook.png"));
	}

	@Override
	public Image whiteBishop() {
		return new Image(
				getClass().getResourceAsStream(prefix + "WtBishop.png"));
	}

	@Override
	public Image whiteKnight() {
		return new Image(
				getClass().getResourceAsStream(prefix + "WtKnight.png"));
	}

	@Override
	public Image whitePawn() {
		return new Image(getClass().getResourceAsStream(prefix + "WtPawn.png"));
	}
}