package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {

        if (extent == null) {

            ExtentSparkReporter reporter =
                    new ExtentSparkReporter("test-output/ExtentReport.html");

            reporter.config().setReportName("ERP Automation Report");
            reporter.config().setDocumentTitle("Automation Execution");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }
        return extent;
    }
}
