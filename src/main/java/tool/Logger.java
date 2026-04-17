package tool;

public class Logger {
	private static void out(String text) {
		System.out.println(text+"\u001b[00m");
	}
	
	public static void info(String text) {
		out("\u001b[00;32m"+text);
	}
	
	public static void warn(String text) {
		out("\u001b[00;33m"+text);
	}
	
	public static void error(String text) {
		out("\u001b[00;31m"+text);
	}
	
	public static void debug(String text) {
		out("\u001b[00;36m"+text);
	}
}