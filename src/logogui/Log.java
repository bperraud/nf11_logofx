package logogui;

import javafx.scene.control.TextArea;


public class Log {
	private static Log instance;
	private TextArea logZone;
	static Log getInstance() {
		if (instance == null)
			instance = new Log();
		return instance;
	}
	void setLogZone(TextArea zone) {
		logZone = zone;
	}
	public static void append(String s) {
		getInstance().getLogZone().appendText(s);
	}
	public static void appendnl(String s) {
		getInstance().getLogZone().appendText(s + "\n");
	}
	private TextArea getLogZone() {
		return logZone;
	}
}
