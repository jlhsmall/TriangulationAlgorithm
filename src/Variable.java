import java.util.LinkedHashSet;

public class Variable {
    public static int cnt=0;
    String name;
    int id,nid;
    byte val;
    boolean isProcessed,isFree;
    LinkedHashSet<Equation> NonProcessedEquation = new LinkedHashSet<>();
    public Variable(String nm) {
        name = nm;
        id = ++cnt;
        isProcessed = false;
    }
    public Variable(){
        id = ++cnt;
        name= Integer.toString(id);
        isProcessed = isFree = false;
    }
    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
