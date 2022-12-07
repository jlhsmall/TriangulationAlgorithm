import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
public class Triangulation {
    ArrayList<Equation> Equations = new ArrayList<>();
    LinkedHashSet<Variable> AllVariables = new LinkedHashSet<>();

    public Triangulation() {

    }

    public Triangulation AddEquation(Equation e) {
        Equations.add(e);
        AllVariables.addAll(e.Variables);
        return this;
    }

    public int work() {
        for(int i=0;i<Equations.size();++i){
            Variable variable=null;
            //the searching can be optimized by a multimap, but I'm just lazy.
            for (Variable v : AllVariables){
                if (!v.isProcessed && v.NonProcessedEquation.size()==1){
                    variable=v;break;
                }
            }
            if(variable==null)break;
            variable.isProcessed=true;
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
        return FreeVariables.size();
    }
}
