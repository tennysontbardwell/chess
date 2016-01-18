package Tennyson_T_Bardwell.BasicChessGame.view;

import static Tennyson_T_Bardwell.BasicChessGame.util.JavaFXHelper.setToParentDimensions;

import Tennyson_T_Bardwell.BasicChessGame.model.Board;
import Tennyson_T_Bardwell.BasicChessGame.model.BoardBuilder;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MainView {
	public static void setUpPane(Pane primaryPane) {
		// Here brackets are used to show organization of UI
		primaryPane.setPrefSize(1000, 800);
		{
			VBox v = new VBox();
			primaryPane.getChildren().add(v);
			setToParentDimensions(v, primaryPane);
			MenuBar bar = new MenuBar();
			{
				bar.prefWidthProperty().bind(v.widthProperty());
				Menu file = new Menu("File");
				MenuItem newGame = new MenuItem("New Game");
				file.getItems().add(newGame);
				bar.getMenus().add(file);
				v.getChildren().add(bar);
			}
			Pane game = new Pane();
			game.prefWidthProperty().bind(v.widthProperty());
			game.prefHeightProperty()
					.bind(v.heightProperty().subtract(bar.heightProperty()));
			v.getChildren().add(game);
			Board b = BoardBuilder.getDefaultBoard();
			new GamePane(game, b);
		}
	}
}
