import javax.swing.*;

public class MyFrame1 extends JFrame
{
    MyPanel1 mp;
    public MyFrame1(Triangulation algo)
    {
        super();

        mp = new MyPanel1(algo);

        this.add(mp);

        this.setSize(800, 800);

        this.setVisible(true);

        this.setLocationRelativeTo(null);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
