package Tennyson_T_Bardwell.BasicChessGame.util;

public class HelperFunctions {
	// from
	// <href>https://stackoverflow.com/questions/10813154/converting-number-to-letter</href>
	public static String toAlphabetic(int i) {
		if (i < 0) {
			return "-" + toAlphabetic(-i - 1);
		}
		int quot = i / 26;
		int rem = i % 26;
		char letter = (char) ((int) 'A' + rem);
		if (quot == 0) {
			return "" + letter;
		} else {
			return toAlphabetic(quot - 1) + letter;
		}
	}
}
