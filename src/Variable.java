import java.util.LinkedHashSet;

public class Variable {
    public static int cnt=0;
    String name;
    int id;
    byte val;
    boolean isProcessed;
    LinkedHashSet<Triangulation.Equation> NonProcessedEquation = new LinkedHashSet<>();
    public Variable(String name) {
        this.name = name;
        this.id = ++cnt;
        isProcessed = false;
    }
    public void setVal(byte val){
        this.val=val;
    }
    @Override
    public int hashCode() {
        return id;
    }
}
