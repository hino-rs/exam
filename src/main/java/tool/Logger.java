package tool;

public class Logger {
	private static void out(String text) {
		System.out.println(text+"\u001b[00m");
	}
	
	public static void info(String text) {
		out("\u001b[00;32m"+"[INFO!]"+text);
	}
	
	public static void warn(String text) {
		out("\u001b[00;33m"+"[WARN!]"+text);
	}
	
	public static void error(String text) {
		out("\u001b[00;31m"+"[ERROR!]"+text);
	}
	
	public static void debug(String text) {
		out("\u001b[00;36m"+"[DEBUG!]"+text);
	}
	
	public static void execute(String text) {
		out("\u001b[00;46m"+"[EXECUTE!]"+text);
	}
	
	public static void dao(String text) {
		out("\u001b[00;45m"+"[DAO!]"+text);
	}
}