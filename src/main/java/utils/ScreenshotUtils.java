package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

public class ScreenshotUtils {

    public static String capture(WebDriver driver, String testName) {
        try {
            File src =
                    ((TakesScreenshot) driver)
                            .getScreenshotAs(OutputType.FILE);

            String fileName =
                    testName + "_" + LocalDateTime.now().toString()
                            .replace(":", "_") + ".png";

            Path dest =
                    Path.of("test-output/screenshots/", fileName);

            Files.createDirectories(dest.getParent());
            Files.copy(src.toPath(), dest);

            return dest.toString();

        } catch (Exception e) {
            return null;
        }
    }
}
