package pages.sales;

import datamodels.sales.PreSalesData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

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
            ("//div[text()='%s']");
    
    private final String projectTypeLabelXpath =
            "//label[normalize-space()='%s']";
    private final By leadSource = By.xpath("(//button[@type=\"button\"])[13]");
    private final By projectName = By.xpath("(//button[@type=\"button\"])[14]");
    private final By budgetRange = By.xpath("//button[span[contains(text(), 'Select Budget')]]");
    private final By additionalDetails = By.xpath("//button[@role='tab' and normalize-space()='Additional Details']");
    private final By leadHotness = By.xpath("//button[.//span[normalize-space()='Select lead hotness']]");
    private final By leadStage = By.xpath("//button[.//span[normalize-space()='Select lead stage']]");
    private final By zipCode = By.xpath("//input[@placeholder='Enter zip code']");
    private final By location = By.xpath("//input[@placeholder='Enter location']");
    private final By address = By.xpath("//input[@placeholder='Enter address']");
    private final By street = By.xpath("//input[@placeholder='Enter street']");
    private final By city = By.xpath("//input[@placeholder='Enter city']");
    private final By state = By.xpath("//input[@placeholder='Enter state']");
    private final By country = By.xpath("//input[@placeholder='Enter country']");
    private final By possessionTiming = By.xpath("//button[@role='combobox' and .//span[text()='Select possession timing']]");
    private final By bedroomPreference = By.xpath("//button[normalize-space()='Select bedroom preference']");
    private final By purpose = By.xpath("//button[normalize-space()='Select purpose']");
    private final By area = By.xpath("//button[normalize-space()='Select area']");
    private final By facing = By.xpath("//button[normalize-space()='Select Facing Directions']");
    private final By sourceOfEnquiry = By.xpath("//input[@placeholder='Enter source of enquiry']");
    private final By receivedOn = By.xpath("//input[@type='text' and @placeholder='Select received date']");
    private final By nextFollowupDate = By.xpath("//input[@placeholder='Select next follow-up date']");
    private final By lastFollowupDate = By.xpath("//input[@placeholder='Select last follow-up date']");
    private final By dateOfBirth = By.xpath("//input[@placeholder='Select date of birth']");
    private final By gender = By.xpath("(//button[@role='combobox' and @aria-expanded='false'])[9]");
    private final By maritalStatus = By.xpath("(//button[@role='combobox' and @aria-expanded='false'])[10]");
    private final By anniversaryDate = By.xpath("//input[@placeholder='Select anniversary date']");
    private final By educationQualification = By.xpath("//input[@placeholder='Enter education qualification']");
    private final By channelPartner = By.xpath("//input[@placeholder='Enter channel partner']");
    private final By reference = By.xpath("//input[@placeholder='Enter reference']");
    private final By comments = By.xpath("//textarea[@placeholder='Enter any additional comments']");
    private final By saveBtn = By.xpath("//button[normalize-space()='Save Lead']");
    

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
        selectProjectType(data.getProjectType());
        ui.selectCustomDropdown(leadSource, data.getLeadSource());
        ui.selectCustomDropdown(projectName, data.getProjectName());
        ui.selectCustomDropdown(budgetRange, data.getBudget());
        ui.click(additionalDetails);
        ui.selectCustomDropdown(leadHotness, data.getLeadHotness());
        ui.selectCustomDropdown(leadStage, data.getLeadStage());
        ui.type(zipCode, data.getZipCode());
        ui.type(location, data.getLocation());
        ui.type(address, data.getAddress());
        ui.type(street, data.getStreet());
        ui.type(city, data.getCity());
        ui.type(state, data.getState());
        ui.type(country, data.getCountry());
        ui.selectCustomDropdown(possessionTiming, data.getPossessionTiming());
        ui.selectCustomDropdown(bedroomPreference, data.getBedroomPreference());
        ui.selectCustomDropdown(purpose, data.getPurpose());
        ui.selectCustomDropdown(area, data.getArea());
        ui.selectCustomDropdown(facing, data.getFacing());
        ui.type(sourceOfEnquiry, data.getSourceOfEnquiry());
        dateUtils.enterDateDirectly(receivedOn, data.getReceivedOn());
        dateUtils.enterDateDirectly(nextFollowupDate, data.getNextFollowupDate());
        dateUtils.enterDateDirectly(lastFollowupDate, data.getLastFollowupDate());
        dateUtils.enterDateDirectly(dateOfBirth, data.getDateOfBirth());
        ui.selectCustomDropdown(gender, data.getGender());
        ui.selectCustomDropdown(maritalStatus, data.getMaritalStatus());
        dateUtils.enterDateDirectly(anniversaryDate, data.getAnniversaryDate());
        ui.type(educationQualification, data.getEducationQualification());
        ui.type(channelPartner, data.getChannelPartner());
        ui.type(reference, data.getReference());
        ui.type(comments, data.getComments());
        ui.click(saveBtn);
    }
    private void selectProjectType(String projectType) {

        Assert.assertNotNull(projectType, "Project Type is NULL");
        Assert.assertFalse(projectType.isBlank(), "Project Type is EMPTY");

        By option =
                By.xpath(String.format(projectTypeLabelXpath, projectType));

        ui.click(option);
    }
}
