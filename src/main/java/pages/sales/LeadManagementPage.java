package pages.sales;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.UIActions;

/**
 * Page Object Model class for the Lead Management list view in the Sales module.
 *
 * This class handles interactions on the lead list screen including:
 * - Switching between primary sub-tabs (New, In-Progress, Dead, Booked, Master Lead)
 * - Switching between secondary sub-tabs (Follow-up, Prospect, Visit Fixed under In-Progress;
 *   Not-Interested, Junk under Dead)
 * - Clicking lead rows to open Quick View side panel
 *
 * Entry assumptions:
 * - User is already logged in
 * - User is already on the Sales Module Dashboard
 * - Lead Management register is already opened
 *
 * Note: Clicking a lead row opens Quick View side panel.
 * Full View is accessed via "View Full Details" button inside Quick View panel (handled by QuickViewPanel).
 *
 * @author Automation Team
 */
public class LeadManagementPage {

    private final UIActions ui;

    /* ===========================
       PRIMARY SUB-TABS
       =========================== */
    /** Locator for the "Lead Management" register */
    public final By leadManReg = By.xpath("//*[contains(text(),'Leads Management')]");
   
    /** Locator for the "New" primary sub-tab */
    private final By newTab = By.xpath("//*[contains(text(),'New Leads')]");
   
    /** Locator for the "In-Progress" primary sub-tab */
    private final By inProgressTab = By.xpath("//*[contains(text(),'In Progress')]");
   
    /** Locator for the "Dead" primary sub-tab */
    private final By deadTab = By.xpath("//*[contains(text(),'Dead Leads')]");
   
    /** Locator for the "Booked" primary sub-tab */
    private final By bookedTab = By.xpath("//*[contains(text(),'Booked')]");
   
    /** Locator for the "Master Lead" primary sub-tab (if visible) */
    private final By masterLeadTab = By.xpath("//*[contains(text(),'Master Lead')]");

    /* ===========================
    SECONDARY SUB-TABS (Under New)
    =========================== */
    
    private final By newSubTab = By.xpath("//span[contains(normalize-space(.),'New') and .//span[contains(@class,'rounded-full')]]");
    
    private final By unAssignedTab = By.xpath("//span[contains(normalize-space(.),'Un Assigned') and .//span[contains(@class,'rounded-full')]]");
    
    /* ===========================
       SECONDARY SUB-TABS (Under In-Progress)
       =========================== */
   
    /** Locator for the "Follow-up" secondary tab (under In-Progress) */
    private final By followUpTab = By.xpath("//span[contains(normalize-space(.),'Follow Up') and .//span[contains(@class,'rounded-full')]]");
   
    /** Locator for the "Prospect" secondary tab (under In-Progress) */
    private final By prospectTab = By.xpath("//span[contains(normalize-space(.),'Prospect') and .//span[contains(@class,'rounded-full')]]");
   
    /** Locator for the "Visit Fixed" secondary tab (under In-Progress) */
    private final By visitFixedTab = By.xpath("//span[contains(normalize-space(.),'Visit Fixed') and .//span[contains(@class,'rounded-full')]]");

    /* ===========================
       SECONDARY SUB-TABS (Under Dead)
       =========================== */
   
    /** Locator for the "Not-Interested" secondary tab (under Dead) */
    private final By notInterestedTab = By.xpath("//span[contains(normalize-space(.),'Not Interested') and .//span[contains(@class,'rounded-full')]]");
   
    /** Locator for the "Junk" secondary tab (under Dead) */
    private final By junkTab = By.xpath("//span[contains(normalize-space(.),'Junk') and .//span[contains(@class,'rounded-full')]]");

    private final  By activePrimaryTabLoc =
    		By.xpath("//a[@data-sidebar='menu-sub-button' and @data-active='true']//span");
    
    private final  By activeSecondaryTabLoc =
            By.xpath("//div[contains(@class,'ta')]//button[@aria-selected='true']");
    
    /* ===========================
   PARAMETERIZED TAB LOCATORS
   =========================== */

/**
 * Generic locator for primary lead tabs like:
 * New, In-Progress, Dead, Booked, Master Lead
 */
/*
 * private By primaryTabByName(String tabName) { return
 * By.xpath("//button[normalize-space()='" + tabName + "']"); }
 */

/**
 * Generic locator for secondary tabs like:
 * Follow-up, Prospect, Visit Fixed, Not-Interested, Junk
 */
/*
 * private By secondaryTabByName(String tabName) { return
 * By.xpath("//div[contains(@class,'sub-tab')]//button[normalize-space()='" +
 * tabName + "']"); }
 */


    /* ===========================
       LEAD DATA TABLE
       =========================== */
   
    /**
     * Base locator for lead rows in the data table.
     * Update this to match your actual table row structure.
     * Example: "//table//tr" or "//div[@class='lead-row']"
     *
     * Note: Clicking a lead row opens the Quick View side panel.
     */
    private final String leadRowBaseXpath = "//table//tbody//tr";

    /**
     * Constructor to initialize the LeadListPage with WebDriver instance.
     *
     * @param driver The WebDriver instance to interact with the browser
     */
    public LeadManagementPage(WebDriver driver) {
        this.ui = new UIActions(driver);
    }

    /* ===========================
   PARAMETERIZED TAB ACTIONS
   =========================== */
    
    public void clickLeadManReg() {
    	ui.click(leadManReg);
    }

	/**
	 * Selects a primary lead tab by visible name.
	 * Example: "New", "In-Progress", "Dead"
	 */
	public void selectPrimaryTab() {
	    ui.click(inProgressTab);
	}

	/**
	 * Selects a secondary lead tab by visible name.
	 * Example: "Follow-up", "Prospect", "Visit Fixed"
	 *
	 * NOTE:
	 * Caller must ensure the correct primary tab
	 * (e.g., In-Progress or Dead) is already selected.
	 */
	public void selectSecondaryTab() {
	    ui.click(prospectTab);
	}
	
	// How to USE this in real tests (very important)
	/*LeadManagementPage leadList = new LeadManagementPage(driver);
	
	leadList.openQuickViewForRow(0);*/

	/**
	 * Returns the active Lead Management primary tab name
	 * (e.g. New Leads, In Progress, Dead Leads).
	 */
	public String getActivePrimaryTab() {
	    By activePrimaryTab = activePrimaryTabLoc;
	
	    return ui.getText(activePrimaryTab).trim();
	}


	public String getActiveSecondaryTab() {
	    String rawText = ui.getText(activeSecondaryTabLoc).trim();
	
	    // Remove trailing counts like "Prospects 1", "Follow Up 3"
	    return rawText.replaceAll("\\s+\\d+$", "");
	}
	
	
    /* ===========================
       LEAD ROW CLICK ACTIONS
       (Clicking a row opens Quick View side panel)
       =========================== */

    /**
     * Clicks on the first available lead row from the table.
     * This opens the Quick View side panel.
     */
	public void openFirstLeadFromList() {
	
	    By firstRow =
	        By.xpath("//table//tbody//tr");
	
	    // 1️⃣ Wait until at least one row is present
	    ui.waitForPresence(firstRow);
	
	    // 2️⃣ Click the first row AFTER table is ready
	    ui.click(By.xpath("(//table//tbody//tr//td)[2]"));
	}

	public String getClientNameFromList() {
	
	    By firstLeadNameCell =
	        By.xpath("(//table//tbody//tr)[1]//td[2]");
	
	    ui.waitForPresence(By.xpath("//table//tbody//tr"));
	
	    return ui.getText(firstLeadNameCell).trim();
	}
}
