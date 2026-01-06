package pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.UIActions;

public class DashboardPage {

    private final UIActions ui;

    private final By salesModule =
            By.xpath("//h3[normalize-space()='Sales Module']");

    public DashboardPage(WebDriver driver) {
        this.ui = new UIActions(driver);
    }

    public void openSalesModule() {
        ui.click(salesModule);
    }
}
