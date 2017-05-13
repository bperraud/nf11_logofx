package logoparsing;

import logogui.Log;
import logoparsing.LogoParser.Liste_instructionsContext;

import java.util.List;
import java.util.Stack;

import static logoparsing.LogoParser.*;
import static logoparsing.LogoTreeVisitor.INCORRECT_PARAMETERS_NUMBER;

class Procedure {
    private String                           name;
    private List<String>                     paramsNames;
    private Liste_instructionsContext        instructions;
    private Rend_instructionContext          retourInstruction;
    private Stack<VariablesContextMap>       contextStack;

    Procedure(
            String name,
            List<String> paramsNames,
            Liste_instructionsContext instructions,
            Rend_instructionContext retourInstruction,
            Stack<VariablesContextMap> contextStack
    ) {
        this.name = name;
        this.paramsNames = paramsNames;
        this.instructions = instructions;
        this.retourInstruction = retourInstruction;
        this.contextStack = contextStack;
    }

    Liste_instructionsContext getInstructions() {
        return instructions;
    }

    boolean hasInstructions() {
        return instructions != null;
    }

    boolean hasRetourInstruction() {
        return retourInstruction != null;
    }

    Rend_instructionContext getRetourInstruction() {
        return retourInstruction;
    }

    Integer prepare(List<Double> params) {
        if (paramsNames.size() != params.size()) {
            Log.appendnl("ERROR, incorrect parameters number given when calling proc!");
            return INCORRECT_PARAMETERS_NUMBER;
        }

        VariablesContextMap procVarsMap = new VariablesContextMap();

        for (int i = 0, paramsLength = params.size(); i < paramsLength; i++) {
            procVarsMap.put(paramsNames.get(i), params.get(i));
        }

        contextStack.push(procVarsMap);
        return 0;
    }

    void close() {
        contextStack.pop();
    }

}
