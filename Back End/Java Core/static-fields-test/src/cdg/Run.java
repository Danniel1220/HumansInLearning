package cdg;

public class Run {
    public static void main(String[] args) {
        StaticTest t1 = new StaticTest(1, 2);
        StaticTest t2 = new StaticTest(3, 4);
        StaticTest t3 = new StaticTest(5, 6);

        System.out.println(t1.getStaticTest());
        System.out.println(t2.getStaticTest());
        System.out.println(t3.getStaticTest());
        System.out.println();
        System.out.println(t1.getIntTest());
        System.out.println(t2.getIntTest());
        System.out.println(t3.getIntTest());
    }
}
