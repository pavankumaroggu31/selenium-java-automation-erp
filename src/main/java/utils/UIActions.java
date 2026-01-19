package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class UIActions {

    private final WebDriver driver;
    private final WaitUtils waitUtils;
    private final JavascriptExecutor js;

    public UIActions(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver);
        this.js = (JavascriptExecutor) driver;
    }

    /* =========================
       BASIC ACTIONS
       ========================= */

    public void click(By locator) {
        WebElement element = waitUtils.waitForClickable(locator);
        scrollToCenter(element);
        element.click();
    }

    public void jsClick(By locator) {
        WebElement element = waitUtils.waitForClickable(locator);
        scrollToCenter(element);
        js.executeScript("arguments[0].click();", element);
    }

    public void type(By locator, String value) {
        WebElement element = waitUtils.waitForVisible(locator);
        scrollToCenter(element);
        element.click();
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);  // Clears the input field
        element.sendKeys(value);  // Types the new value
    }


    /* =========================
       SCROLL METHODS (FINAL)
       ========================= */

    // 🔹 Scroll element to CENTER (most used)
    public void scrollToCenter(WebElement element) {
        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                element
        );
    }

    public void scrollToCenter(By locator) {
        WebElement element = waitUtils.waitForVisible(locator);
        scrollToCenter(element);
    }

    // 🔹 Scroll element to TOP
    public void scrollToTop(WebElement element) {
        js.executeScript(
                "arguments[0].scrollIntoView({block:'start'});",
                element
        );
    }

    // 🔹 Scroll element to BOTTOM
    public void scrollToBottom(WebElement element) {
        js.executeScript(
                "arguments[0].scrollIntoView({block:'end'});",
                element
        );
    }

    // 🔹 Scroll FULL PAGE DOWN
    public void scrollPageDown() {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    // 🔹 Scroll FULL PAGE UP
    public void scrollPageUp() {
        js.executeScript("window.scrollTo(0, 0);");
    }

    // 🔹 Scroll by PIXELS (fine control)
    public void scrollByPixels(int x, int y) {
        js.executeScript("window.scrollBy(arguments[0], arguments[1]);", x, y);
    }

    // 🔹 Scroll to element using TEXT (when locator is hard)
    public void scrollToText(String visibleText) {
        WebElement element = driver.findElement(
                By.xpath("//*[contains(normalize-space(),'" + visibleText + "')]")
        );
        scrollToCenter(element);
    }

    /* =========================
       CUSTOM DROPDOWN
       ========================= */
    public void selectCustomDropdown(By dropdown, String value) {
        try {
            // Open dropdown
            WebElement trigger = waitUtils.waitForClickable(dropdown);
            trigger.click();

            // Select option (Radix / ShadCN safe)
            By optionLocator = By.xpath(
                    "//div[@role='option' and normalize-space()='" + value + "']" +
                    " | //span[normalize-space()='" + value + "']"
            );

            WebElement option = waitUtils.waitForClickable(optionLocator);
            js.executeScript("arguments[0].click();", option);

        } catch (Exception e) {
            throw new RuntimeException(
                    "❌ Failed to select dropdown value: " + value,
                    e
            );
        }
    }
    
}
