package logoparsing;

import javafx.scene.Group;
import logogui.Log;
import logogui.Traceur;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static logoparsing.LogoParser.*;

@SuppressWarnings("FieldCanBeLocal")
public class LogoTreeVisitor extends LogoBaseVisitor<Integer> {

    private static int BREAK_ACTIVE                  = -1;
    private static int DIVISION_BY_ZERO              = -2;
    private static int UNEXPECTED_NEGATIVE_VALUE     = -3;
    private static int BAD_RGB_VALUE                 = -4;
    private static int VARIABLE_NOT_SET              = -5;
    private static int UNKNOWN_BOOLEAN_OPERATOR      = -6;
    private static int UNEXPECTED_DOUBLE_VALUE       = -7;
    static         int INCORRECT_PARAMETERS_NUMBER   = -8;
    private static int MISSING_RETURN_STATEMENT      = -9;
    private static int UNKNOWN_PROCEDURE_OR_FUNCTION = -10;

    private Traceur traceur;
    private ParseTreeProperty<Double>  atts               = new ParseTreeProperty<>();
    //    private ParseTreeProperty<Map<Integer, Double>> atts               = new ParseTreeProperty<>();
    private Stack<Integer>             loopsStack         = new Stack<>();
    private Map<String, Procedure>     declaredProcedures = new HashMap<>();
    private Stack<VariablesContextMap> contextStack       = new Stack<>();
    private double rendValue;

    private boolean debug               = false;
    private int     debugFuncStackDepth = 0;

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
//        if (atts.get(node) == null) {
//            atts.put(node, new HashMap<>());
//        }
//        atts.get(node).put(debugFuncStackDepth, value);
        atts.put(node, value);
        debugFuncStack("setAttValue, key: " + node.getText() + ", stackdepth:" + debugFuncStackDepth + ", value: " + value);
    }

    private double getAttValue(ParseTree node) {
//        debugFuncStack("getAttValue, key: " + node.getText() + ", stackdepth" + debugFuncStackDepth);
//        Double res;
//        int    depth = debugFuncStackDepth;
//        while ((res = atts.get(node).get(depth)) == null) depth++;
//        return res;

        // Prendre en priorité la valeur de la variable dans contextStack
        if (node.getClass().getSimpleName().equals("GetvarContext"))
            return contextStack.peek().get(node.getText().substring(1));

        return atts.get(node);
    }

    private void debugFuncStack(String msg) {
        if (!debug)
            return;
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < debugFuncStackDepth; i++) {
            indent.append(". . ");
        }
        System.out.println(indent + msg);
    }

    @Override
    public Integer visitMul(MulContext ctx) {
        Integer code;
        debugFuncStack("VisitMul, stack: " + contextStack);

        for (ParseTree child : ctx.children) {
            code = visit(child);
            if (code != null && code < 0) return code;
        }

        String op = ctx.getChild(1).getText();
        debugFuncStack("Mul1->" + ctx.exp(0).getText());
        debugFuncStack("Mul2->" + ctx.exp(1).getText());
        double num1 = getAttValue(ctx.exp(0));
//        if (ctx.exp(0).getClass().getSimpleName().equals("GetvarContext"))
//            num1 = contextStack.peek().get(ctx.exp(0).getText().substring(1));
        double num2 = getAttValue(ctx.exp(1));
        double result;

        debugFuncStack("Mul1 (" + ctx.exp(0).getText() + ") = " + num1);
        debugFuncStack("Mul2 (" + ctx.exp(1).getText() + ") = " + num2);

        if (op.equals("*")) result = num1 * num2;
        else if (num2 == 0.0) return DIVISION_BY_ZERO;
        else result = num1 / num2;

        debugFuncStack("MulRes = " + result);

        setAttValue(ctx, result);
        Log.appendnl("visitMul");
        return 0;
    }

    @Override
    public Integer visitParenthese(ParentheseContext ctx) {
        Integer code;

        for (ParseTree child : ctx.children) {
            code = visit(child);

            if (code != null && code < 0) return code;
        }

        setAttValue(ctx, getAttValue(ctx.exp()));
        Log.appendnl("visitParenthese");

        return 0;
    }

    @Override
    public Integer visitSum(SumContext ctx) {
        Integer code;

        for (ParseTree child : ctx.children) {
            code = visit(child);

            if (code != null && code < 0) return code;
        }

        String op   = ctx.getChild(1).getText();
        double num1 = getAttValue(ctx.exp(0));
        double num2 = getAttValue(ctx.exp(1));
        double result;

        if (op.equals("+")) {
            result = num1 + num2;
        } else {
            result = num1 - num2;
        }

        if (result < 0.0) {
            return UNEXPECTED_NEGATIVE_VALUE;
        }

        setAttValue(ctx, result);
        Log.appendnl("visitSum");
        return 0;
    }

    @Override
    public Integer visitInt(IntContext ctx) {
        String intText = ctx.INT().getText();
        setAttValue(ctx, Integer.valueOf(intText));
        Log.appendnl("visitInt : " + getAttValue(ctx));
        return 0;
    }

    @Override
    public Integer visitDouble(DoubleContext ctx) {
        String doubleText = ctx.DOUBLE().getText();
        setAttValue(ctx, Double.valueOf(doubleText));
        Log.appendnl("visitDouble");
        return 0;
    }

    @Override
    public Integer visitRandom(RandomContext ctx) {
        Integer code = visit(ctx.exp());

        if (code != null && code < 0) return code;

        double borneSup = getAttValue(ctx.exp());

        if (!(borneSup == Math.floor(borneSup)) || Double.isInfinite(borneSup)) {
            return UNEXPECTED_DOUBLE_VALUE;
        } else if (borneSup < 0) return UNEXPECTED_NEGATIVE_VALUE;

        double random = ThreadLocalRandom.current().nextDouble(0, borneSup);
        setAttValue(ctx, random);
        Log.appendnl("visitRandom " + random);
        return 0;
    }

    @Override
    public Integer visitSqrt(SqrtContext ctx) {
        Integer code = visit(ctx.exp());

        if (code != null && code < 0) return code;

        double number = getAttValue(ctx.exp());
        if (number < 0)
            return UNEXPECTED_NEGATIVE_VALUE;

        double sqrt = Math.sqrt(number);
        setAttValue(ctx, sqrt);
        Log.appendnl("visitSqrt " + sqrt);
        return 0;
    }

    @Override
    public Integer visitRepete(RepeteContext ctx) {
        Integer code = visit(ctx.exp());

        if (code != null && code < 0) return code;

        double nbIterations = getAttValue(ctx.exp());

        if (!(nbIterations == Math.floor(nbIterations)) || Double.isInfinite(nbIterations)) {
            return UNEXPECTED_DOUBLE_VALUE;
        }

        for (int i = 0; i < nbIterations; i++) {
            loopsStack.push(i + 1);

            if ((code = visit(ctx.bloc())) != null && code < 0) return code;
        }

        loopsStack.pop();
        Log.appendnl("visitRepete");
        return 0;
    }

    @Override
    public Integer visitLoop(LoopContext ctx) {
        setAttValue(ctx, loopsStack.peek());
        Log.appendnl("visitLoop");
        return 0;
    }

    @Override
    public Integer visitAv(AvContext ctx) {
        Integer code = visit(ctx.exp());

        if (code != null && code < 0) return code;

        traceur.avance(getAttValue(ctx.exp()));
        Log.appendnl("visitAv");
        return 0;
    }

    @Override
    public Integer visitRe(ReContext ctx) {
        Integer code = visit(ctx.exp());

        if (code != null && code < 0) return code;

        traceur.recule(getAttValue(ctx.exp()));
        Log.appendnl("visitRe");
        return 0;
    }

    @Override
    public Integer visitTd(TdContext ctx) {
        Integer code = visit(ctx.exp());

        if (code != null && code < 0) return code;

        traceur.td(getAttValue(ctx.exp()));
        Log.appendnl("visitTd");
        return 0;
    }

    @Override
    public Integer visitTg(TgContext ctx) {
        Integer code = visit(ctx.exp());

        if (code != null && code < 0) return code;

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
        Integer code;

        for (ParseTree child : ctx.children) {
            code = visit(child);

            if (code != null && code < 0) return code;
        }

        traceur.fpos(getAttValue(ctx.exp(0)), getAttValue(ctx.exp(1)));
        Log.appendnl("visitFpos");
        return 0;
    }

    @Override
    public Integer visitFcc(FccContext ctx) {
        Integer code;

        for (ParseTree child : ctx.children) {
            code = visit(child);

            if (code != null && code < 0) return code;
        }

        int r = toInt(getAttValue(ctx.exp(0)));
        int g = toInt(getAttValue(ctx.exp(1)));
        int b = toInt(getAttValue(ctx.exp(2)));

        if (!(0 <= r && r <= 255 && 0 <= g && g <= 255 && 0 <= b && b <= 255)) {
            return BAD_RGB_VALUE;
        }

        traceur.fcc(r, g, b);
        Log.appendnl("visitFcc");
        return 0;
    }

    @Override
    public Integer visitDonne(DonneContext ctx) {
        Integer code = visit(ctx.exp());
        if (code != null && code < 0) return code;

        String varName = ctx.SETVAR().getText().substring(1);
        double value   = toInt(getAttValue(ctx.exp()));

        contextStack.peek().put(varName, value);
        debugFuncStack("Put: " + "key=" + varName + ", value=" + value);
        debugFuncStack("stack at put: " + contextStack);
        Log.appendnl("visitDonne");
        return 0;
    }

    @Override
    public Integer visitGetvar(GetvarContext ctx) {
        String varName = ctx.GETVAR().getText().substring(1);
        if (!contextStack.peek().containsKey(varName)) return VARIABLE_NOT_SET;
        setAttValue(ctx, contextStack.peek().get(varName));
        Log.appendnl("visitGetvar");
        debugFuncStack("visitGetvar (" + ctx.getParent().getText() + "), varName:" + varName + ", value: " + contextStack.peek().get(
                varName) + ", stack: " + contextStack);
        return 0;
    }

    @Override
    public Integer visitBooleancomposite(BooleancompositeContext ctx) {
        Integer code;

        for (ParseTree child : ctx.children) {
            code = visit(child);

            if (code != null && code < 0) return code;
        }

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
                return UNKNOWN_BOOLEAN_OPERATOR;
        }
        Log.appendnl("visitBooleanexpression");
        return 0;
    }

    @Override
    public Integer visitBooleanatom(BooleanatomContext ctx) {
        Integer code = visit(ctx.exp());

        if (code != null && code < 0) return 0;

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
        Integer code = visit(ctx.booleanexpression());

        if (code != null && code < 0) return code;

        double testResult = getAttValue(ctx.booleanexpression());

        Log.appendnl("visitSi");

        if (testResult != 0.0) {
            return visit(ctx.bloc(0));
        } else if (ctx.bloc().size() == 2) {
            return visit(ctx.bloc(1));
        }

        return 0;
    }

    @Override
    public Integer visitTantque(TantqueContext ctx) {
        Integer code = visit(ctx.booleanexpression());

        if (code != null && code < 0) return code;

        double testResult = getAttValue(ctx.booleanexpression());

        while (testResult != 0.0) {
            code = visit(ctx.bloc());

            if (code != null) {
                if (code < 0) return code;
                else if (code == BREAK_ACTIVE) {
                    Log.appendnl("STOP");
                    return 0;
                }
            }

            visit(ctx.booleanexpression());
            testResult = getAttValue(ctx.booleanexpression());

            Log.appendnl("visitTantque");
        }

        return 0;
    }

    @Override
    public Integer visitBreak(BreakContext ctx) {
        Log.appendnl("visitBreak");
        return BREAK_ACTIVE;
    }

    @Override
    public Integer visitListe_instructions(Liste_instructionsContext ctx) {
        Integer code;

        for (InstructionContext instructionContext : ctx.instruction()) {
            code = visit(instructionContext);
            if (code != null && code < 0) {
                return code;
            }
        }

        return 0;
    }

    @Override
    public Integer visitBloc(BlocContext ctx) {
        Integer code;
        for (ParseTree child : ctx.children) {
            code = visit(child);

            if (code != null && code < 0) return code;
        }

        return 0;
    }

    @Override
    public Integer visitProgramme(ProgrammeContext ctx) {
        contextStack.push(new VariablesContextMap());
        Integer code;
        for (ParseTree child : ctx.children) {
            code = visit(child);

            if (code != null && code < 0) {
                Log.appendnl("Error, code: " + code);
                return code;
            }
        }

        return 0;
    }

    @Override
    public Integer visitDef_procedure(Def_procedureContext ctx) {
        List<String> paramsNames = new LinkedList<>();

        for (TerminalNode paramNameNode : ctx.GETVAR()) {
            paramsNames.add(paramNameNode.getText().substring(1));
        }

        Procedure procedure = new Procedure(
                ctx.PROCNAME().getText(),
                paramsNames,
                ctx.liste_instructions(),
                ctx.rend_instruction(),
                contextStack
        );

        declaredProcedures.put(ctx.PROCNAME().getText(), procedure);
        return 0;
    }

    private Integer visitParamsValues(List<ExpContext> src, List<Double> dest) {
        Integer code;
        for (ExpContext expContext : src) {
            code = visit(expContext);
            if (code != null && code < 0) return code;
            dest.add(getAttValue(expContext));
        }
        return 0;
    }

    // TODO: handle "rends" statement at any moment (like STOP for tantque)
    @Override
    public Integer visitProcedurecall(ProcedurecallContext ctx) {
        String       procName     = ctx.PROCNAME().getText();
        List<Double> paramsValues = new LinkedList<>();
        Integer      code         = visitParamsValues(ctx.exp(), paramsValues);
        if (code != null && code < 0) return code;

        Procedure procedure = declaredProcedures.get(procName);
        if (procedure == null)
            return UNKNOWN_PROCEDURE_OR_FUNCTION;

        Log.appendnl("Visit func: " + procName);
        debugFuncStack("Visit func: " + procName);
        debugFuncStack("Params: " + paramsValues);

        code = procedure.prepare(paramsValues);
        if (code != null && code < 0) return code;

        debugFuncStack("ContextStack: " + contextStack);
        debugFuncStackDepth++;

        if (procedure.hasInstructions()) {
            code = visit(procedure.getInstructions());
            if (code != null && code < 0) {
                if (code == BREAK_ACTIVE) {
                    procedure.close();
                    return 0;
                }
                return code;
            }
        }

        if (procedure.hasRetourInstruction()) {
            code = visit(procedure.getRetourInstruction());
            if (code != null && code < 0) return code;
        }

        procedure.close();
        debugFuncStack("Pop, stack: " + contextStack);
        debugFuncStackDepth--;

        return 0;
    }

    @Override
    public Integer visitFunctioncall(FunctioncallContext ctx) {
        String       procName     = ctx.PROCNAME().getText();
        List<Double> paramsValues = new LinkedList<>();
        Integer      code         = visitParamsValues(ctx.exp(), paramsValues);

        if (code != null && code < 0) return code;

        Procedure procedure = declaredProcedures.get(procName);
        if (procedure == null)
            return UNKNOWN_PROCEDURE_OR_FUNCTION;

        Log.appendnl("Visit func: " + procName);
        debugFuncStack("Visit func: " + procName);
        debugFuncStack("Params: " + paramsValues);

        if (!procedure.hasRetourInstruction()) return MISSING_RETURN_STATEMENT;

        code = procedure.prepare(paramsValues);
        if (code != null && code < 0) return code;

        debugFuncStack("ContextStack: " + contextStack);
        debugFuncStackDepth++;

        if (procedure.hasInstructions()) {
            code = visit(procedure.getInstructions());
            if (code != null && code < 0) {
                if (code == BREAK_ACTIVE) {
                    procedure.close();
                    return 0;
                }
                return code;
            }
        }

        if ((code = visit(procedure.getRetourInstruction())) != null && code < 0) return code;

//        procedure.getValeurRetourStack().push(getAttValue(procedure.getRetourInstruction().exp()));
//        setAttValue(ctx, getAttValue(procedure.getRetourInstruction()));
        setAttValue(ctx, rendValue);

        procedure.close();
        debugFuncStack("Pop, stack: " + contextStack);
        debugFuncStackDepth--;

        return 0;
    }

    @Override
    public Integer visitRend_instruction(Rend_instructionContext ctx) {
        Integer code = visit(ctx.exp());
        if (code != null && code < 0) return code;
//        setAttValue(ctx, getAttValue(ctx.exp()));
        rendValue = getAttValue(ctx.exp());
        debugFuncStack("setRendValue: " + getAttValue(ctx.exp()));
        return 0;
    }
}