import java.awt.*;

public class Main {
    public static void trivialTest1() {
        //Example1 in paper
        Variable s = new Variable("s"), t = new Variable("t"), u = new Variable("u"), v = new Variable("v"), x = new Variable("x"), y = new Variable("y"), z = new Variable("z");
        Triangulation algo = new Triangulation();
        Equation e1 = new Equation(Color.red), e2 = new Equation(Color.red), e3 = new Equation(Color.red), e4 = new Equation(Color.red), e5 = new Equation(Color.red);
        e1.addVariable(y).addVariable(z).addVariable(u).addVariable(x).addVariable(s);
        e2.addVariable(z).addVariable(t).addVariable(v).addVariable(s);
        e3.addVariable(t).addVariable(u).addVariable(x);
        e4.addVariable(u).addVariable(v).addVariable(s);
        e5.addVariable(v).addVariable(x).addVariable(s);
        algo.AddEquation(e5).AddEquation(e1).AddEquation(e4).AddEquation(e2).AddEquation(e3);
        System.out.println(algo.work());
    }

    public static void trivialTest2() {
        //test2: a+b+c=0,a-b-c=0
        Variable a = new Variable("a"), b = new Variable("b"), c = new Variable("c");
        Triangulation algo = new Triangulation();
        Equation e1 = new Equation(Color.red), e2 = new Equation(Color.red);
        e1.addVariable(a).addVariable(b).addVariable(c);
        e2.addVariable(a).addVariable(b).addVariable(c);
        algo.AddEquation(e1).AddEquation(e2);
        System.out.println(algo.work());
    }

    public static void AESTest() {
        //test for Rijndael-hash 320/160, 5 rounds
        Variable[][][] m = new Variable[6][4][5], x = new Variable[12][4][5];
        Triangulation algo = new Triangulation();
        for (int k = 0; k < 12; ++k)
            for (int i = 0; i < 4; ++i)
                for (int j = 0; j < 5; ++j)
                    x[k][i][j] = new Variable();
        /*for (int k = 0; k < 6; ++k)
            for (int i = 0; i < 4; ++i)
                for (int j = 0; j < 5; ++j)
                    m[k][i][j] = new Variable();*/
        for (int k = 0; k < 6; k+=2)
            for (int i = 0; i < 4; ++i) {
                for (int j = 0; j < 5; ++j)
                    m[k][i][j] = new Variable();
                for (int j = 0; j < 5; ++j)
                    m[k+1][i][j] = new Variable();
            }
        for (int k = 0; k < 11; ++k) {
            if (k % 2 == 0) {
                //ARK&SB
                for (int i = 0; i < 4; ++i)
                    for (int j = 0; j < 5; ++j)
                        algo.AddEquation(new Equation(Color.red).addVariable(x[k][i][j]).addVariable(x[k + 1][i][j]).addVariable(m[k >> 1][i][j]));
            } else {
                //SR&MC
                /*for (int j = 0; j < 5; ++j) {
                    Equation e = new Equation();
                    for (int i = 0; i < 4; ++i) e.addVariable(x[k][i][j]).addVariable(x[k + 1][i][j]);
                    algo.AddEquation(e);
                }*/
                for(int i=0;i<4;++i)for (int j=0;j<5;++j){
                    Equation e = new Equation(Color.green);
                    for (int ii = 0; ii < 4; ++ii) e.addVariable(x[k][ii][(j+ii)%5]);
                    algo.AddEquation(e.addVariable(x[k+1][i][j]));
                }
            }
        }
        for (int k = 0; k <= 2; k += 2) for (int i = 0; i < 4; ++i){
            algo.AddEquation(new Equation(Color.blue).addVariable(m[k][i][0]).addVariable(m[k + 1][(i + 1) % 4][0]).addVariable(m[k + 2][i][0]));
            for (int j = 1; j < 5; ++j)
                algo.AddEquation(new Equation(Color.blue).addVariable(m[k][i][j]).addVariable(m[k + 2][i][j - 1]).addVariable(m[k + 2][i][j]));
            algo.AddEquation(new Equation(Color.blue).addVariable(m[k + 1][i][0]).addVariable(m[k + 2][i][4]).addVariable(m[k + 3][i][0]));
            for (int j = 1; j < 5; ++j)
                algo.AddEquation(new Equation(Color.blue).addVariable(m[k + 1][i][j]).addVariable(m[k + 3][i][j - 1]).addVariable(m[k + 3][i][j]));
        }
        System.out.println(algo.work());

        /*System.out.println(x[1][0][0].isFree);
        System.out.println(x[1][0][2].isFree);
        System.out.println(x[5][0][0].isFree);
        System.out.println(x[5][0][1].isFree);
        System.out.println(x[9][0][0].isFree);*/
        new MyFrame1(algo);
        //new MyFrame2(algo);
    }


    public static void main(String[] args) throws Exception {
        //trivialTest1();
        //trivialTest2();
        AESTest();
    }
}
