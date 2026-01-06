package pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.UIActions;

public class HeaderComponent {

    private final UIActions ui;

    private final By logoutBtn = By.xpath("//button[contains(text(),'Logout')]");

    public HeaderComponent(WebDriver driver) {
        this.ui = new UIActions(driver);
    }

    public void logout() {
        ui.click(logoutBtn);
    }
}
