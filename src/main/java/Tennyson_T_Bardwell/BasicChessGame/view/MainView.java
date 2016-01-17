package Tennyson_T_Bardwell.BasicChessGame.view;

import static Tennyson_T_Bardwell.BasicChessGame.util.JavaFXHelper.setToParentDimensions;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class MainView {
	public static void setUpPane(Pane primaryPane) {
		// Here brackets are used to show organization of UI
		primaryPane.setPrefSize(1000, 800);
		{
			HBox h = new HBox();
			primaryPane.getChildren().add(h);
			setToParentDimensions(h, primaryPane);
			MenuBar bar = new MenuBar();
			{
				bar.prefWidthProperty().bind(h.widthProperty());
				Menu file = new Menu("File");
				MenuItem newGame = new MenuItem("New Game");
				file.getItems().add(newGame);
				bar.getMenus().add(file);
				h.getChildren().add(bar);
			}
			Pane game = new Pane();
			game.prefWidthProperty().bind(h.widthProperty());
			game.prefHeightProperty()
					.bind(h.heightProperty().subtract(bar.heightProperty()));
			new GamePane(game);
		}
	}
}
