package logoparsing;

import javafx.scene.Group;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import logogui.Log;
import logogui.Traceur;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

import static logoparsing.LogoParser.*;

public class LogoTreeVisitor extends LogoBaseVisitor<Integer> {
    private Traceur traceur;
    private ParseTreeProperty<Double> atts = new ParseTreeProperty<>();
    private Stack<Integer> loopsStack = new Stack<>();
    private Map<String, Double> varMap = new HashMap<>();

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

    /* TODO : vérifier les Var dans les autres expressions de la grammaire notamment, dans le repete avec un entier forcé.
    */


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
        Log.appendnl(String.valueOf(getAttValue(ctx.exp())));
        return 0;
    }

    @Override
    public Integer visitRe(ReContext ctx) {
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
    public Integer visitTg(TgContext ctx) {
        visitChildren(ctx);
        traceur.tg(getAttValue(ctx.exp()));
        Log.appendnl("visitTg");
        return 0;
    }

    @Override
    public Integer visitLc(LcContext ctx) {
        traceur.lc();
        Log.appendnl("visitLc");
        return 0;
    }

    @Override
    public Integer visitBc(BcContext ctx) {
        traceur.bc();
        Log.appendnl("visitBc");
        return 0;
    }

    @Override
    public Integer visitVe(VeContext ctx) {
        traceur.ve();
        Log.appendnl("visitVe");
        return 0;
    }

    @Override
    public Integer visitFpos(FposContext ctx) {
        visitChildren(ctx);
        traceur.fpos(getAttValue(ctx.exp(0)), getAttValue(ctx.exp(1)));
        Log.appendnl("visitFpos");
        return 0;
    }

    @Override
    public Integer visitFcc(FccContext ctx) {
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

    @Override
    public Integer visitDonne(DonneContext ctx) {
        visitChildren(ctx);
        String varName = ctx.SETVAR().getText().substring(1);
        double value = toInt(getAttValue(ctx.exp()));
        varMap.put(varName, value);
        Log.appendnl("visitDonne");
        return 0;
    }

    @Override
    public Integer visitGetvar(GetvarContext ctx) {
        String varName = ctx.GETVAR().getText().substring(1);
        if (!varMap.containsKey(varName))
            throw new NullPointerException("Variable '" + varName + "' is not set!");
        setAttValue(ctx, varMap.get(varName));
        Log.appendnl("visitGetvar");
        return 0;
    }

    @Override
    public Integer visitBooleancomposite(BooleancompositeContext ctx) {
        visitChildren(ctx);
        String operator = ctx.OPERATOR().getText();
        double operand1 = getAttValue(ctx.exp(0));
        double operand2 = getAttValue(ctx.exp(1));
        switch (operator) {
            case "==":
                setAttValue(ctx, operand1 == operand2 ? 1 : 0);
                break;
            case ">":
                setAttValue(ctx, operand1 > operand2 ? 1 : 0);
                break;
            case ">=":
                setAttValue(ctx, operand1 >= operand2 ? 1 : 0);
                break;
            case "<":
                setAttValue(ctx, operand1 < operand2 ? 1 : 0);
                break;
            case "<=":
                setAttValue(ctx, operand1 <= operand2 ? 1 : 0);
                break;
            case "!=":
                setAttValue(ctx, operand1 != operand2 ? 1 : 0);
                break;
            default:
                throw new IllegalArgumentException("Unknown operator");
        }
        Log.appendnl("visitBooleanexpression");
        return 0;
    }

    @Override
    public Integer visitBooleanatom(BooleanatomContext ctx) {
        double value = getAttValue(ctx.exp());
        if (value == 0.0) {
            setAttValue(ctx, 0);
        } else {
            setAttValue(ctx, 1);
        }
        Log.appendnl("visitBooleanatom");
        return 0;
    }

    @Override
    public Integer visitSi(SiContext ctx) {
        visit(ctx.booleanexpression());
        double testResult = getAttValue(ctx.booleanexpression());
        if (testResult != 0.0) {
            visit(ctx.bloc(0));
        } else if (ctx.bloc().size() == 2) {
            visit(ctx.bloc(1));
        }
        Log.appendnl("visitSi");
        return 0;
    }

    @Override
    public Integer visitTantque(TantqueContext ctx) {
        visit(ctx.booleanexpression());
        double testResult = getAttValue(ctx.booleanexpression());

        while (testResult != 0.0) {
            visit(ctx.bloc());

            visit(ctx.booleanexpression());
            testResult = getAttValue(ctx.booleanexpression());
            
            Log.appendnl("visitTantque");
        }

        // TODO: implémenter le throw exception pour le cas où il y aurait un break

        return 0;
    }


}