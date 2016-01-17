package Tennyson_T_Bardwell.BasicChessGame;

import Tennyson_T_Bardwell.BasicChessGame.view.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {
	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane p = new Pane();
		MainView.setUpPane(p);
		Scene scene = new Scene(p);
		primaryStage.setScene(scene);
		primaryStage.show();
		p.prefHeightProperty().bind(scene.heightProperty());
		p.prefWidthProperty().bind(scene.widthProperty());
	}
}
