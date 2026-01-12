package pages.sales;

import datamodels.sales.PreSalesData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.DateUtils;
import utils.UIActions;

public class LeadCreationPage {

    private final UIActions ui;
    private final DateUtils dateUtils;

    private final By addLeadBtn = By.xpath("//button[contains(text(),'Add Lead')]");
    private final By customerName = By.id("customerName");
    private final By mobile = By.xpath("//input[@data-testid='input-enter-mobile-number']");
    private final By email = By.id("email");
    
 // ===== Calendar Locators (ONLY HERE) =====
    // Direct input field
    private final By enquiryDateInput =
    		 By.xpath("//input[@data-testid='input-select-enquiry-date']");

    // Calendar open button / icon
    private final By enquiryCalendarIcon =
            By.xpath("(//button[@type=\"button\"])[8]");

    private final By yearDropdown =
            By.xpath("//select[@aria-label='Choose the Year']");

    private final By monthDropdown =
            By.xpath("//select[@aria-label='Choose the Month']");

    private final String dayXpath =
            "//div[text()='%s']";
    
    private final By projectType = By.name("projectType");
    private final By leadSource = By.name("leadSource");
    private final By projectName = By.name("project");
    private final By saveBtn = By.xpath("//button[contains(text(),'Save')]");

    public LeadCreationPage(WebDriver driver) {
        this.ui = new UIActions(driver);
        this.dateUtils = new DateUtils(driver);
    }

    public void createLead(PreSalesData data) {
        ui.click(addLeadBtn);
        ui.type(customerName, data.getCustomerName());
        ui.type(mobile, data.getMobile());
        ui.type(email, data.getEmail());
        // ✅ DATE HANDLING DECISION
            if ("DIRECT".equalsIgnoreCase(data.getDateSelectionType())) {

                dateUtils.enterDateDirectly(
                        enquiryDateInput,
                        data.getEnquiryDate()
                );

            } else {

                dateUtils.selectDateFromCalendar(
                        enquiryCalendarIcon,
                        yearDropdown,
                        monthDropdown,
                        dayXpath,
                        data.getEnquiryDate()
                );
            }
        
        ui.type(projectType, data.getProjectType());
        ui.selectCustomDropdown(leadSource, data.getLeadSource());
        ui.selectCustomDropdown(projectName, data.getProjectName());
        ui.click(saveBtn);
    }
}
