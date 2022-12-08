public class Main {
    public static void trivialTest1() {
        //Example1 in paper
        Variable s = new Variable("s"), t = new Variable("t"), u = new Variable("u"), v = new Variable("v"), x = new Variable("x"), y = new Variable("y"), z = new Variable("z");
        Triangulation algo = new Triangulation();
        Equation e1 = new Equation(), e2 = new Equation(), e3 = new Equation(), e4 = new Equation(), e5 = new Equation();
        e1.addVariable(y).addVariable(z).addVariable(u).addVariable(x).addVariable(s);
        e2.addVariable(z).addVariable(t).addVariable(v).addVariable(s);
        e3.addVariable(t).addVariable(u).addVariable(x);
        e4.addVariable(u).addVariable(v).addVariable(s);
        e5.addVariable(v).addVariable(x).addVariable(s);
        algo.AddEquation(e1).AddEquation(e2).AddEquation(e3).AddEquation(e4).AddEquation(e5);
        System.out.println(algo.work());
    }

    public static void trivialTest2() {
        //test2: a+b+c=0,a-b-c=0
        Variable a = new Variable("a"), b = new Variable("b"), c = new Variable("c");
        Triangulation algo = new Triangulation();
        Equation e1 = new Equation(), e2 = new Equation();
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
        for (int k = 0; k < 6; ++k)
            for (int i = 0; i < 4; ++i)
                for (int j = 0; j < 5; ++j)
                    m[k][i][j] = new Variable();
        x[1][0][0].isProcessed = x[1][0][2].isProcessed = x[5][0][1].isProcessed = x[5][0][2].isProcessed = x[9][0][0].isProcessed = true;
        for (int k = 0; k < 11; ++k) {
            if (k % 2 == 0) {
                //ARK&SB
                for (int i = 0; i < 4; ++i)
                    for (int j = 0; j < 5; ++j)
                        algo.AddEquation(new Equation().addVariable(x[k][i][j]).addVariable(x[k + 1][i][j]).addVariable(m[k >> 1][i][j]));
            } else {
                //SR&MC
                for (int j = 0; j < 5; ++j) {
                    Equation e = new Equation();
                    for (int i = 0; i < 4; ++i) e.addVariable(x[k][i][j]).addVariable(x[k + 1][i][j]);
                    algo.AddEquation(e);
                }
            }
        }
        System.out.println(algo.work());
    }

    public static void main(String[] args) throws Exception {
        trivialTest1();
        trivialTest2();
        AESTest();
    }
}
