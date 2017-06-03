package logogui;

import javafx.scene.control.TextArea;

public class Log {
    private static Log      instance;
    private        TextArea logZone;
    private StringBuilder sb = new StringBuilder();

    static Log getInstance() {
        if (instance == null) {
            instance = new Log();
        }
        return instance;
    }

    void setLogZone(TextArea zone) {
        logZone = zone;
    }

    public static void append(String s) {
        getInstance().getStringBuilder().append(s);
    }

    public static void appendnl(String s) {
        getInstance().getStringBuilder().append(s).append("\n");
    }

    public static void flush() {
        getInstance().getLogZone().appendText(getInstance().getStringBuilder().toString());
    }

    private TextArea getLogZone() {
        return logZone;
    }

    private StringBuilder getStringBuilder() {
        return sb;
    }
}
