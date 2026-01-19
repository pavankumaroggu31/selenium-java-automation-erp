package pages.sales;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.UIActions;

/**
 * Page Object Model class for the Quick View panel in the Sales module.
 *
 * Responsibilities:
 * - Change lead status
 * - Open Edit Lead form
 * - Open Full View
 *
 * NOTE:
 * This class does NOT handle form filling or business logic.
 */
public class QuickViewPanelPage {

    private final UIActions ui;

    // ===========================
    // LOCATORS
    // ===========================

    private final By statusDropdown = By.xpath("//div[@class='mt-3 flex items-center gap-2 w-full']//button[@role='combobox' and @data-slot='select-trigger']");

    private final By editLeadBtn = By.xpath("//span[contains(text(),'Edit Lead')]");
    private final By viewFullDetailsBtn = By.xpath("//button[contains(text(),'View Full Details')]");
    private final By leadStatusText =
        By.xpath("//span[@class='text-sm font-medium']");

    private final By moreActionsBtn = By.xpath("//button[contains(text(),'More Actions')]");
   

    // ===========================
    // CONSTRUCTOR
    // ===========================

    public QuickViewPanelPage(WebDriver driver) {
        this.ui = new UIActions(driver);
    }

    // ===========================
    // ACTIONS
    // ===========================
    
    /**
     * Returns the client name shown in Quick View side panel.
     */
    public String getClientName() {

        By clientName =
            By.xpath("//div[@class='text-title leading-snug truncate']");

        return ui.getText(clientName).trim();
    }


    /**
     * Selects a status from the status dropdown.
     */
    /**
     * Selects a lead status from the Status dropdown in Quick View.
     *
     * @param statusName One of: New, Follow Up, Prospect, Visit Fixed
     */
    public void selectStatus(String status) {

        // 1️. Open dropdown
        ui.click(statusDropdown);

        // 2.️ Build dynamic option locator
        By statusOption = By.xpath(
            "//div[@role='listbox']//span[normalize-space()='" + status + "']"
        );

        // 3️. Select option
        ui.click(statusOption);
        
        ui.getText(leadStatusText).trim();
    }

    public void openMoreActions() {
        ui.click(moreActionsBtn);
    }

    /**
     * Opens the Edit Lead form.
     */
    public void clickEditLead() {
        ui.click(editLeadBtn);
    }

    /**
     * Opens the full lead details page.
     */
    public void clickViewFullDetails() {
        ui.click(viewFullDetailsBtn);
    }
}

