package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.HashMap;
import java.util.Map;

public class DateUtils {

    private final UIActions ui;
    private final WaitUtils waitUtils;

    // Calendar dropdown locators (example – adjust if needed)
    private final By calendarYearDropdown = By.cssSelector("select[aria-label='Select year']");
    private final By calendarMonthDropdown = By.cssSelector("select[aria-label='Select month']");

    private static final Map<String, String> MONTH_MAP = new HashMap<>();

    static {
        MONTH_MAP.put("January", "0");
        MONTH_MAP.put("February", "1");
        MONTH_MAP.put("March", "2");
        MONTH_MAP.put("April", "3");
        MONTH_MAP.put("May", "4");
        MONTH_MAP.put("June", "5");
        MONTH_MAP.put("July", "6");
        MONTH_MAP.put("August", "7");
        MONTH_MAP.put("September", "8");
        MONTH_MAP.put("October", "9");
        MONTH_MAP.put("November", "10");
        MONTH_MAP.put("December", "11");
    }

    public DateUtils(WebDriver driver) {
        this.ui = new UIActions(driver);
        this.waitUtils = new WaitUtils(driver);
    }

    /**
     * Example aria-label:
     * "Choose Monday, September 16, 2025"
     */
    public void selectDate(By dateFieldLocator, String fullDateAriaLabel) {

        // Open calendar
        ui.click(dateFieldLocator);

        // Extract month & year from aria-label
        String[] parts = fullDateAriaLabel.split(",\\s*");
        String year = parts[2].trim();
        String monthName = parts[1].split(" ")[0];

        // Select year
        Select yearDropdown =
                new Select(waitUtils.waitForVisible(calendarYearDropdown));
        yearDropdown.selectByValue(year);

        // Select month
        Select monthDropdown =
                new Select(waitUtils.waitForVisible(calendarMonthDropdown));
        monthDropdown.selectByValue(MONTH_MAP.get(monthName));

        // Select date
        By dateLocator = By.xpath("//div[@aria-label='" + fullDateAriaLabel + "']");
        ui.click(dateLocator);
    }
}
