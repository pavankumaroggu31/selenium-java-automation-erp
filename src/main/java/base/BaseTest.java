package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.DriverFactory;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.out.println("Initializing WebDriver...");
        DriverFactory.initDriver();  // Initialize WebDriver
        driver = DriverFactory.getDriver();  // Get the WebDriver instance
    }

    @AfterMethod
    public void tearDown() {
        System.out.println("Cleaning up WebDriver...");
        DriverFactory.quitDriver();  // Quit WebDriver after the test
    }
}
