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
    private final By customerName = By.name("customerName");
    private final By mobile = By.name("mobile");
    private final By email = By.name("email");
    private final By enquiryDate = By.name("enquiryDate");
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
        dateUtils.selectDate(enquiryDate, data.getEnquiryDate());
        ui.selectCustomDropdown(projectType, data.getProjectType());
        ui.selectCustomDropdown(leadSource, data.getLeadSource());
        ui.selectCustomDropdown(projectName, data.getProjectName());
        ui.click(saveBtn);
    }
}
