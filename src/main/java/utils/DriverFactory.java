package utils;

import config.AppConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    public static void initDriver() {

        switch (AppConfig.BROWSER) {
            case CHROME -> {
                WebDriverManager.chromedriver().setup();
                DRIVER.set(new ChromeDriver());
            }
            case EDGE -> {
                WebDriverManager.edgedriver().setup();
                DRIVER.set(new EdgeDriver());
            }
            case FIREFOX -> {
                WebDriverManager.firefoxdriver().setup();
                DRIVER.set(new FirefoxDriver());
            }
        }

        DRIVER.get().manage().window().maximize();
        DRIVER.get().get(AppConfig.ENV.getUrl());
    }

    public static WebDriver getDriver() {
        return DRIVER.get();
    }

    public static void quitDriver() {
        if (DRIVER.get() != null) {
            DRIVER.get().quit();
            DRIVER.remove();
        }
    }
}
