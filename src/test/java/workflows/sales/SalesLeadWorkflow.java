package workflows.sales;

import datamodels.sales.PreSalesData;
import datamodels.users.UserEMPRoles;
import enums.UserRole;
import org.openqa.selenium.WebDriver;
import pages.common.DashboardPage;
import pages.common.HeaderComponent;
import pages.common.LoginPage;
import pages.sales.LeadCreationPage;

import java.util.Map;

public class SalesLeadWorkflow {

    private final LoginPage login;
    private final DashboardPage dashboard;
    private final HeaderComponent header;
    private final LeadCreationPage leadPage;

    public SalesLeadWorkflow(WebDriver driver, UserEMPRoles users) {

        this.login = new LoginPage(
                driver,
                Map.of(
                        UserRole.PRE_SALES, users.getPreSales(),
                        UserRole.SITE_ADMIN, users.getSiteAdmin(),
                        UserRole.SALES_EXEC, users.getSalesExec()
                )
        );

        this.dashboard = new DashboardPage(driver);
        this.header = new HeaderComponent(driver);
        this.leadPage = new LeadCreationPage(driver);
    }

    public void createLeadAsPreSales(PreSalesData data) {
        login.login(UserRole.PRE_SALES);
        dashboard.openSalesModule();
        leadPage.createLead(data);
        header.logout();
    }
}
