package logogui;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Traceur {
    private Group g;
    private double initx = 300, inity = 300;   // position initiale
    private double posx = initx, posy = inity; // position courante
    private int angle = 90;
    private double teta;
    private boolean pencilIsDown = true;
    private Color currentColor = Color.BLACK;

    public Traceur() {
        setTeta();
    }

    public void setGraphics(Group g) {
        this.g = g;
    }

    private int toInt(double a) {
        return (int) Math.round(a);
    }

    private void setTeta() {
        teta = Math.toRadians(angle);
    }

    public void avance(double r) {
        double a = posx + r * Math.cos(teta);
        double b = posy - r * Math.sin(teta);
        int x1 = toInt(posx);
        int y1 = toInt(posy);
        int x2 = toInt(a);
        int y2 = toInt(b);
        if (pencilIsDown) {
            Line newLine = new Line(x1, y1, x2, y2);
            newLine.setStroke(currentColor);
            g.getChildren().add(newLine);
        }
        posx = a;
        posy = b;
    }

    public void recule(double r) {
        double a = posx - r * Math.cos(teta);
        double b = posy + r * Math.sin(teta);
        int x1 = toInt(posx);
        int y1 = toInt(posy);
        int x2 = toInt(a);
        int y2 = toInt(b);
        if (pencilIsDown)
            g.getChildren().add(new Line(x1, y1, x2, y2));
        posx = a;
        posy = b;
    }

    public void td(double r) {
        angle = (angle - toInt(r)) % 360;
        setTeta();
    }

    public void tg(double r) {
        angle = (angle + toInt(r)) % 360;
        setTeta();
    }

    public void lc() {
        pencilIsDown = false;
    }

    public void bc() {
        pencilIsDown = true;
    }

    public void ve() {
        g.getChildren().clear();
    }

    public void fpos(double xPos, double yPos) {
        posx = xPos;
        posy = yPos;
    }

    public void fcc(int red, int green, int blue) {
        currentColor = Color.rgb(red, green, blue);
    }
}
