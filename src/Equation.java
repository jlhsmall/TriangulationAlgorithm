import java.awt.*;
import java.util.LinkedHashSet;

public class Equation {
    LinkedHashSet<Variable> Variables = new LinkedHashSet<>();
    boolean isProcessed;
    Color col;
    public Equation(Color c) {
        isProcessed = false;
        col=c;
    }

    public Equation addVariable(Variable v) {
        Variables.add(v);
        v.NonProcessedEquation.add(this);
        return this;
    }
}