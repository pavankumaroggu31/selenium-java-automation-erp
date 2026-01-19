package tests.sales;

import base.BaseTest;
import datamodels.sales.PreSalesData;
import datamodels.sales.QuickViewData;
import datamodels.users.UserEMPRoles;
import listeners.TestListener;
import pages.sales.LeadManagementPage;
import pages.sales.QuickViewPanelPage;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.JsonDataReader;
import workflows.sales.QuickViewflow;
import workflows.sales.SalesLeadWorkflow;

@Listeners(TestListener.class)
public class LeadToBookingE2ETest extends BaseTest {
	
	UserEMPRoles users = JsonDataReader.readUsers();
	QuickViewData data = JsonDataReader.readQuickViewData();

    //@Test(description = "Pre-Sales creates a lead")
    public void verifyLeadCreation() {

        
        PreSalesData salesData = JsonDataReader.readSalesData();
        
        
        SalesLeadWorkflow workflow =
                new SalesLeadWorkflow(driver, users);

        workflow.createLeadAsPreSales(salesData);
    }
    
    @Test(description = "Quick View actions Test")
    public void navigateToInProgressProspect() {

        QuickViewflow quickView =
                new QuickViewflow(driver, users);

        quickView.openInProgressProspectAsPreSales();
        quickView.openFirstLeadAndApplyQuickView(data);


        LeadManagementPage leadManagement =
                new LeadManagementPage(driver);
       
        QuickViewPanelPage quickUIActions = new QuickViewPanelPage(driver);

        // Assert tabs
        Assert.assertEquals(
            leadManagement.getActivePrimaryTab(),
            "In Progress"
        );

        Assert.assertEquals(
            leadManagement.getActiveSecondaryTab(),
            "Prospects"
        );

       
        String expectedClientName =
                leadManagement.getClientNameFromList();
        String actualClientName =
        quickUIActions.getClientName();
       
        System.out.println("Selected Lead name: " + actualClientName);
       
        Assert.assertEquals(
                actualClientName,
                expectedClientName,
                "Client name mismatch between Lead List and Quick View"
        );
       
    }
    
}
