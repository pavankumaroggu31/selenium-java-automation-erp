package reports;

import com.aventstack.extentreports.ExtentTest;

public class ExtentTestManager {

    private static final ThreadLocal<ExtentTest> TEST = new ThreadLocal<>();

    public static void setTest(ExtentTest test) {
        TEST.set(test);
    }

    public static ExtentTest getTest() {
        return TEST.get();
    }

    public static void unload() {
        TEST.remove();
    }
}
