package logoparsing;

import javafx.scene.Group;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import logogui.Log;
import logogui.Traceur;
import logoparsing.LogoParser.*;

import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

public class LogoTreeVisitor extends LogoBaseVisitor<Integer> {
    private Traceur traceur;
    private ParseTreeProperty<Double> atts = new ParseTreeProperty<>();
    private Stack<Integer> loopsStack = new Stack<>();

    private int toInt(double a) {
        return (int) Math.round(a);
    }

    public LogoTreeVisitor() {
        super();
    }

    public void initialize(Group g) {
        traceur = new Traceur();
        traceur.setGraphics(g);
    }

    private void setAttValue(ParseTree node, double value) {
        atts.put(node, value);
    }

    private double getAttValue(ParseTree node) {
        return atts.get(node);
    }

    @Override
    public Integer visitMul(MulContext ctx) {
        visitChildren(ctx);
        String op = ctx.getChild(1).getText();
        double num1 = getAttValue(ctx.exp(0));
        double num2 = getAttValue(ctx.exp(1));
        double result;
        if (op.equals("*")) {
            result = num1 * num2;
        } else {
            if (num2 == 0.0) {
                throw new IllegalArgumentException("Argument 'divisor' is 0");
            } else {
                result = num1 / num2;
            }
        }
        setAttValue(ctx, result);
        Log.appendnl("visitMul");
        return 0;
    }

    @Override
    public Integer visitParenthese(ParentheseContext ctx) {
        visitChildren(ctx);
        setAttValue(ctx, getAttValue(ctx.exp()));
        Log.appendnl("visitParenthese");
        return 0;
    }

    @Override
    public Integer visitSum(SumContext ctx) {
        visitChildren(ctx);
        String op = ctx.getChild(1).getText();
        double num1 = getAttValue(ctx.exp(0));
        double num2 = getAttValue(ctx.exp(1));
        double result;
        if (op.equals("+")) {
            result = num1 + num2;
        } else {
            result = num1 - num2;
        }
        if (result < 0.0) {
            throw new IllegalArgumentException("Negative result from substraction");
        }
        setAttValue(ctx, result);
        Log.appendnl("visitSum");
        return 0;
    }

    @Override
    public Integer visitInt(IntContext ctx) {
        visitChildren(ctx);
        String intText = ctx.INT().getText();
        setAttValue(ctx, Integer.valueOf(intText));
        Log.appendnl("visitInt");
        return 0;
    }

    @Override
    public Integer visitRandom(RandomContext ctx) {
        double random = ThreadLocalRandom.current().nextDouble(0, Double.valueOf(ctx.INT().getText()));
        setAttValue(ctx, random);
        Log.appendnl("visitRandom " + random);
        return 0;
    }

    @Override
    public Integer visitRepete(RepeteContext ctx) {
        int nbiterations = Integer.valueOf(ctx.INT().getText());
        for (int i = 0; i < nbiterations; i++) {
            loopsStack.push(i + 1);
            visit(ctx.bloc());
        }
        loopsStack.pop();
        Log.appendnl("visitRepete");
        return 0;
    }

    @Override
    public Integer visitLoop(LoopContext ctx) {
        setAttValue(ctx, loopsStack.peek());
        Log.appendnl("visitLoop ");
        return 0;
    }

    @Override
    public Integer visitAv(AvContext ctx) {
        visitChildren(ctx);
        traceur.avance(getAttValue(ctx.exp()));
        Log.appendnl("visitAv");
        return 0;
    }

    @Override
    public Integer visitRe(LogoParser.ReContext ctx) {
        visitChildren(ctx);
        traceur.recule(getAttValue(ctx.exp()));
        Log.appendnl("visitRe");
        return 0;
    }

    @Override
    public Integer visitTd(TdContext ctx) {
        visitChildren(ctx);
        traceur.td(getAttValue(ctx.exp()));
        Log.appendnl("visitTd");
        return 0;
    }

    @Override
    public Integer visitTg(LogoParser.TgContext ctx) {
        visitChildren(ctx);
        traceur.tg(getAttValue(ctx.exp()));
        Log.appendnl("visitTg");
        return 0;
    }

    @Override
    public Integer visitLc(LogoParser.LcContext ctx) {
        traceur.lc();
        Log.appendnl("visitLc");
        return 0;
    }

    @Override
    public Integer visitBc(LogoParser.BcContext ctx) {
        traceur.bc();
        Log.appendnl("visitBc");
        return 0;
    }

    @Override
    public Integer visitVe(LogoParser.VeContext ctx) {
        traceur.ve();
        Log.appendnl("visitVe");
        return 0;
    }

    @Override
    public Integer visitFpos(LogoParser.FposContext ctx) {
        visitChildren(ctx);
        traceur.fpos(getAttValue(ctx.exp(0)), getAttValue(ctx.exp(1)));
        Log.appendnl("visitFpos");
        return 0;
    }

    @Override
    public Integer visitFcc(LogoParser.FccContext ctx) {
        visitChildren(ctx);
        int r = toInt(getAttValue(ctx.exp(0)));
        int g = toInt(getAttValue(ctx.exp(1)));
        int b = toInt(getAttValue(ctx.exp(2)));
        if (!(0 <= r && r <= 255 && 0 <= g && g <= 255 && 0 <= b && b <= 255))
            throw new IllegalArgumentException("Bad value given for red, green or blue color (expected range from 0 to 255)");
        traceur.fcc(r, g, b);
        Log.appendnl("visitFcc");
        return 0;
    }
}