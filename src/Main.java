public class Main {
    public static void trivialTest1(){
        //Example1 in paper
        Variable s=new Variable("s"),t=new Variable("t"),u=new Variable("u"),v=new Variable("v"),x=new Variable("x"),y=new Variable("y"),z=new Variable("z");
        Triangulation algo=new Triangulation();
        Equation e1=new Equation(),e2=new Equation(),e3=new Equation(),e4=new Equation(),e5=new Equation();
        e1.addVariable(y).addVariable(z).addVariable(u).addVariable(x).addVariable(s);
        e2.addVariable(z).addVariable(t).addVariable(v).addVariable(s);
        e3.addVariable(t).addVariable(u).addVariable(x);
        e4.addVariable(u).addVariable(v).addVariable(s);
        e5.addVariable(v).addVariable(x).addVariable(s);
        algo.AddEquation(e1).AddEquation(e2).AddEquation(e3).AddEquation(e4).AddEquation(e5);
        System.out.println(algo.work());
    }
    public static void trivialTest2(){
        //test2: a+b+c=0,a-b-c=0
        Variable a=new Variable("a"),b=new Variable("b"),c=new Variable("c");
        Triangulation algo=new Triangulation();
        Equation e1=new Equation(),e2=new Equation();
        e1.addVariable(a).addVariable(b).addVariable(c);
        e2.addVariable(a).addVariable(b).addVariable(c);
        algo.AddEquation(e1).AddEquation(e2);
        System.out.println(algo.work());
    }
    public static void main(String[] args) throws Exception {
        trivialTest1();
        trivialTest2();
    }
}
