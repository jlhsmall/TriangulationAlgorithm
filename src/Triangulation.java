import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class Triangulation {
    int cnt = 0;

    public class Variable {
        byte val;
        int id;
        boolean isProcessed;
        LinkedHashSet<Equation> NonProcessedEquation = new LinkedHashSet<>();

        public Variable(byte val, int id) {
            this.val = val;
            this.id = id;
            isProcessed = false;
        }
    }

    public class Equation {
        LinkedHashSet<Variable> Variables = new LinkedHashSet<>();
        boolean isProcessed;

        public Equation() {
            isProcessed = false;
        }

        public void addVariable(Variable v) {
            Variables.add(v);
            v.NonProcessedEquation.add(this);
        }
    }

    ArrayList<Equation> Equations = new ArrayList<>();
    LinkedHashSet<Variable> AllVariables = new LinkedHashSet<>();

    public Triangulation() {

    }

    public void AddEquation(Equation e) {
        Equations.add(e);
        AllVariables.addAll(e.Variables);
    }

    public Set<Variable> getFreeVariables() {
        while(true){
            Variable variable=null;
            for (Variable v : AllVariables){
                if (!v.isProcessed && v.NonProcessedEquation.size()==1){
                    variable=v;break;
                }
            }
            if(variable==null)break;
        }
        LinkedHashSet<Variable>FreeVariables=new LinkedHashSet<>();
        for(Variable v : AllVariables){
            if(!v.isProcessed && v.NonProcessedEquation.size()==0)FreeVariables.add(v);
        }
        return FreeVariables;
    }
}
