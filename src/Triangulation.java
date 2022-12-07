import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
public class Triangulation {

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

    public void work() {
        for(int i=0;i<Equations.size();++i){
            Variable variable=null;
            //the searching can be optimized by a multimap, but I'm just lazy.
            for (Variable v : AllVariables){
                if (!v.isProcessed && v.NonProcessedEquation.size()==1){
                    variable=v;break;
                }
            }
            if(variable==null)break;
            Equation e=variable.NonProcessedEquation.iterator().next();
            e.isProcessed=true;
            for(Variable v : e.Variables){
                v.NonProcessedEquation.remove(e);
            }
        }
        LinkedHashSet<Variable>FreeVariables=new LinkedHashSet<>();
        for(Variable v : AllVariables){
            if(!v.isProcessed && v.NonProcessedEquation.size()==0)FreeVariables.add(v);
        }
    }
}
