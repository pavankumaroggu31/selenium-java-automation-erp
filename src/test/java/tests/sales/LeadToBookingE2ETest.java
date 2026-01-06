package tests.sales;

import base.BaseTest;
import datamodels.sales.PreSalesData;
import datamodels.users.UserEMPRoles;
import listeners.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.JsonDataReader;
import workflows.sales.SalesLeadWorkflow;

@Listeners(TestListener.class)
public class LeadToBookingE2ETest extends BaseTest {

    @Test(description = "Pre-Sales creates a lead")
    public void verifyLeadCreation() {

        UserEMPRoles users = JsonDataReader.readUsers();
        PreSalesData salesData = JsonDataReader.readSalesData();

        SalesLeadWorkflow workflow =
                new SalesLeadWorkflow(driver, users);

        workflow.createLeadAsPreSales(salesData);
    }
}
