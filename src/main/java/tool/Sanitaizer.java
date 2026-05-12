package tool;

public class Sanitaizer {
	public static String sanitaizing(String text) {
		text = text.replace("<", "&lt;");
		text = text.replace(">", "&gt;");
		return text;
	}
	
}
