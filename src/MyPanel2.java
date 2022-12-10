import javax.swing.*;
import java.awt.*;

class MyPanel2 extends JPanel

{

    //   private static final long serialVersionUID = 1;

    Triangulation algo;
    public MyPanel2(Triangulation algo){
        super();
        this.algo=algo;
    }
    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i < algo.ProcessedEquations.size(); ++i) {
            Equation e = algo.ProcessedEquations.get(i);
            g.setColor(e.col);
            for (Variable v : e.Variables)
                g.fillRect(v.nid*2,i*2, 2, 2);
        }
    }
}