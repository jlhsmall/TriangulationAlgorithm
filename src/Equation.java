import java.util.LinkedHashSet;

public class Equation {
    LinkedHashSet<Variable> Variables = new LinkedHashSet<>();
    boolean isProcessed;

    public Equation() {
        isProcessed = false;
    }

    public Equation addVariable(Variable v) {
        Variables.add(v);
        v.NonProcessedEquation.add(this);
        return this;
    }
}