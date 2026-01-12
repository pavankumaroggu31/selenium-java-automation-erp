package listeners;

import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reports.ExtentReportManager;
import reports.ExtentTestManager;
import utils.DriverFactory;
import utils.ScreenshotUtils;

public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {

        ExtentTest test =
                ExtentReportManager.getInstance()
                        .createTest(result.getMethod().getMethodName());

        ExtentTestManager.setTest(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest()
                .pass("✅ Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        ExtentTest test = ExtentTestManager.getTest();
        test.fail(result.getThrowable());

        String path = ScreenshotUtils.capture(
                DriverFactory.getDriver(),
                result.getMethod().getMethodName()
        );

        // ✅ CRITICAL SAFETY CHECK
        if (path != null && !path.isEmpty()) {
            test.addScreenCaptureFromPath(path);
        } else {
            test.warning("⚠ Screenshot not available");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

        ExtentTest test = ExtentTestManager.getTest();
        test.skip(result.getThrowable());

        String path = ScreenshotUtils.capture(
                DriverFactory.getDriver(),
                result.getMethod().getMethodName() + "_SKIPPED"
        );

        if (path != null && !path.isEmpty()) {
            test.addScreenCaptureFromPath(path);
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentTestManager.unload();
        ExtentReportManager.getInstance().flush();
    }
}