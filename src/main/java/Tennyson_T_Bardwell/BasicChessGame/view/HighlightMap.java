package Tennyson_T_Bardwell.BasicChessGame.view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Tennyson_T_Bardwell.BasicChessGame.model.Coordinate;
import Tennyson_T_Bardwell.BasicChessGame.util.Listener;

/** A class for marking which tiles of the game board need to be highlighted
 * 
 * @author tbTennyson */
public class HighlightMap {
	/** Set of highlighted coordinates */
	Set<Coordinate> h = new HashSet<>();
	List<Listener> list = new ArrayList<>();

	public void clear() {
		h.clear();
		yell();
	}

	public boolean isHighlighted(Coordinate c) {
		return h.contains(c);
	}

	public void highlight(Coordinate c) {
		h.add(c);
		yell();
	}

	public void notifyOnChange(Listener l) {
		list.add(l);
	}
	
	private void yell(){
		list.forEach(e -> {
			e.changed();
		});
	}
}
