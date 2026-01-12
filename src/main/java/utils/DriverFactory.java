package utils;

import config.AppConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    public static void initDriver() {
        try {
            switch (AppConfig.BROWSER) {
                case CHROME -> {
                    ChromeOptions options = new ChromeOptions();

                    // Recommended options
                    options.addArguments("--start-maximized");
                    options.addArguments("--disable-notifications");
                    options.addArguments("--disable-infobars");

                    // Optional for CI / Server
                    // options.addArguments("--headless=new");
                    // options.addArguments("--no-sandbox");
                    // options.addArguments("--disable-dev-shm-usage");

                    WebDriverManager.chromedriver().setup();
                    DRIVER.set(new ChromeDriver(options));

                    System.out.println("Chrome WebDriver initialized.");
                }
                case EDGE -> {
                    WebDriverManager.edgedriver().setup();
                    DRIVER.set(new EdgeDriver());
                    System.out.println("Edge WebDriver initialized.");
                }
                case FIREFOX -> {
                    WebDriverManager.firefoxdriver().setup();
                    DRIVER.set(new FirefoxDriver());
                    System.out.println("Firefox WebDriver initialized.");
                }
                default -> throw new RuntimeException("Unsupported browser: " + AppConfig.BROWSER);
            }

            // Maximize the window
            DRIVER.get().manage().window().maximize();

            // Open URL as per configuration
            DRIVER.get().get(AppConfig.ENV.getUrl());
            System.out.println("Navigating to: " + AppConfig.ENV.getUrl());
        } catch (Exception e) {
            System.err.println("Error initializing WebDriver: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("WebDriver initialization failed", e);
        }
    }

    public static WebDriver getDriver() {
        if (DRIVER.get() == null) {
            throw new IllegalStateException("WebDriver is not initialized yet.");
        }
        return DRIVER.get();
    }

    public static void quitDriver() {
        if (DRIVER.get() != null) {
            DRIVER.get().quit();
            DRIVER.remove();
            System.out.println("WebDriver quit successfully.");
        }
    }

    public static boolean hasDriver() {
        return DRIVER.get() != null;
    }
}
