package pages.common;

import datamodels.users.UserData;
import enums.UserRole;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.UIActions;

import java.util.Map;

public class LoginPage {

    private final UIActions ui;

    private final By email = By.id("email");
    private final By password = By.id("password");
    private final By submit = By.cssSelector("[type='submit']");

    private final Map<UserRole, UserData> users;

    public LoginPage(WebDriver driver, Map<UserRole, UserData> users) {
        this.ui = new UIActions(driver);
        this.users = users;
    }

    public void login(UserRole role) {
        UserData user = users.get(role);
        ui.type(email, user.getUsername());
        ui.type(password, user.getPassword());
        ui.click(submit);
    }
}
