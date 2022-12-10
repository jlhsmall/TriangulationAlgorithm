import javax.swing.*;

public class MyFrame2 extends JFrame
{
    MyPanel2 mp;
    public MyFrame2(Triangulation algo)
    {
        super();

        mp = new MyPanel2(algo);

        this.add(mp);

        this.setSize(800, 800);

        this.setVisible(true);

        this.setLocationRelativeTo(null);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}
