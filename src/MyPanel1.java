import javax.swing.*;
import java.awt.*;

class MyPanel1 extends JPanel

{

 //   private static final long serialVersionUID = 1;

    Triangulation algo;
    public MyPanel1(Triangulation algo){
        super();
        this.algo=algo;
    }
    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i < algo.Equations.size(); ++i) {
            Equation e = algo.Equations.get(i);
            g.setColor(e.col);
            for (Variable v : e.Variables)
                g.fillRect(v.id*2,i*2, 2, 2);
        }
    }
}