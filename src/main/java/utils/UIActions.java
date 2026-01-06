package utils;

import org.openqa.selenium.*;

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
        element.clear();
        element.sendKeys(value);
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
            // 1. Open dropdown
            WebElement dropdownButton = waitUtils.waitForClickable(dropdown);
            scrollToCenter(dropdownButton);
            dropdownButton.click();

            // 2. YOUR EXACT WORKING LOCATORS (UNCHANGED)
            By optionLocator = By.xpath(
                    "//div[@role='dialog']//*[contains(text(),'" + value + "')] | " +
                    "//div[@role='presentation']//*[contains(text(),'" + value + "')] | " +
                    "//div[contains(text(),'" + value + "')]"
            );

            // 3. Select option
            WebElement option = waitUtils.waitForClickable(optionLocator);
            scrollToCenter(option);
            js.executeScript("arguments[0].click();", option);

        } catch (Exception e) {
            throw new RuntimeException(
                    "❌ Failed to select '" + value +
                    "' from custom dropdown. Locator: " + dropdown +
                    " | Error: " + e.getMessage(),
                    e
            );
        }
    }
}
