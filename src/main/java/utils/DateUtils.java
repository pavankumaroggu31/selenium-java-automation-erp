package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateUtils {

    private final WaitUtils wait;
    private final UIActions ui;

    public DateUtils(WebDriver driver) {
        this.wait = new WaitUtils(driver);
        this.ui = new UIActions(driver);
    }

    // ✅ DIRECT INPUT
    public void enterDateDirectly(By inputLocator, String date) {
        ui.type(inputLocator, date);
    }

    // ✅ CALENDAR USING DROPDOWNS (PAGE PROVIDES LOCATORS)
    public void selectDateFromCalendar(
            By calendarOpenLocator,
            By yearDropdown,
            By monthDropdown,
            String dayXpath,
            String date
    ) {
        ui.click(calendarOpenLocator);

        LocalDate parsed =
                LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        String year = String.valueOf(parsed.getYear());
        String month =
                parsed.getMonth()
                        .getDisplayName(java.time.format.TextStyle.FULL, Locale.ENGLISH);
        String day = String.valueOf(parsed.getDayOfMonth());

        new Select(wait.waitForVisible(yearDropdown))
                .selectByVisibleText(year);

        new Select(wait.waitForVisible(monthDropdown))
                .selectByVisibleText(month);

        ui.click(By.xpath(String.format(dayXpath, day)));
    }
}