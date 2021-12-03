package cdg;

public class StaticTest {
    private static int staticTest;
    private int intTest;

    StaticTest(int staticTest, int intTest) {
        this.staticTest = staticTest;
        this.intTest = intTest;
    }

    public static int getStaticTest() {
        return staticTest;
    }

    public static void setStaticTest(int staticTest) {
        StaticTest.staticTest = staticTest;
    }

    public int getIntTest() {
        return intTest;
    }

    public void setIntTest(int intTest) {
        this.intTest = intTest;
    }
}
