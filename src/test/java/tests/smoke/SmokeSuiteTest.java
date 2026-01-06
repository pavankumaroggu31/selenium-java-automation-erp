package tests.smoke;

import base.BaseTest;
import listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.common.DashboardPage;

@Listeners(TestListener.class)
public class SmokeSuiteTest extends BaseTest {

    @Test
    public void verifyDashboardLoads() {
        new DashboardPage(driver).openSalesModule();
    }
}
