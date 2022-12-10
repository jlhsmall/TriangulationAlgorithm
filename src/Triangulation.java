import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class Triangulation {
    ArrayList<Equation> Equations = new ArrayList<>(),ProcessedEquations=new ArrayList<>();
    LinkedHashSet<Variable> AllVariables = new LinkedHashSet<>();
    public Triangulation() {
        Variable.cnt=0;
    }

    public Triangulation AddEquation(Equation e) {
        Equations.add(e);
        AllVariables.addAll(e.Variables);
        return this;
    }

    public int work() {
        int ncnt=0;
        while(ncnt< Equations.size()) {
            Variable variable = null;
            //the searching can be optimized by a multimap, but I'm just lazy.
            for (Variable v : AllVariables) {
                if (!v.isProcessed && v.NonProcessedEquation.size() == 1) {
                    variable = v;
                    break;
                }
            }
            if (variable == null) return -1;
            variable.isProcessed = true;variable.nid=ncnt++;
            Equation e = variable.NonProcessedEquation.iterator().next();
            e.isProcessed = true;
            ProcessedEquations.add(e);
            for (Variable v : e.Variables) {
                v.NonProcessedEquation.remove(e);
            }
        }
        int numFree=0;
        for (Variable v : AllVariables) {
            if (!v.isProcessed && v.NonProcessedEquation.size() == 0){
                ++numFree;
                v.isFree=true;
                v.nid=ncnt++;
            }
        }
        return numFree;
    }
}
