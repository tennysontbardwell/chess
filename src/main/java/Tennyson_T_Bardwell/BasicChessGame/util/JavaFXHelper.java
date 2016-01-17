package Tennyson_T_Bardwell.BasicChessGame.util;

import javafx.scene.layout.Region;

public class JavaFXHelper {
	static public void setToParentDimensions(Region child, Region parent){
		child.prefHeightProperty().bind(parent.heightProperty());
		child.prefWidthProperty().bind(parent.widthProperty());
	}
}
