package workflows.sales;

import datamodels.sales.QuickViewData;
import datamodels.users.UserEMPRoles;
import enums.UserRole;
import org.openqa.selenium.WebDriver;
import pages.common.DashboardPage;
import pages.common.LoginPage;
import pages.sales.LeadManagementPage;
import pages.sales.QuickViewPanelPage;

import java.util.Map;

/**
 * Workflow for navigating Lead Management and its tabs.
 */
public class QuickViewflow {

    private final LoginPage login;
    private final DashboardPage dashboard;
    private final LeadManagementPage leadManagement;
    private final QuickViewPanelPage quickUIActions;

    public QuickViewflow(WebDriver driver, UserEMPRoles users) {

        this.login = new LoginPage(
                driver,
                Map.of(
                        UserRole.PRE_SALES, users.getPreSales(),
                        UserRole.SITE_ADMIN, users.getSiteAdmin(),
                        UserRole.SALES_EXEC, users.getSalesExec()
                )
        );

        this.dashboard = new DashboardPage(driver);
        this.leadManagement = new LeadManagementPage(driver);
        this.quickUIActions = new QuickViewPanelPage(driver);
    }

    /**
     * Pre-Sales user navigates to:
     * Sales Module → Lead Management → In-Progress → Follow-up
     */
    public void openInProgressProspectAsPreSales() {

        login.login(UserRole.PRE_SALES);
        dashboard.openSalesModule();

        leadManagement.clickLeadManReg();
        leadManagement.selectPrimaryTab();
        leadManagement.selectSecondaryTab();
        leadManagement.openFirstLeadFromList();
    }
    
    /**
     * Opens first lead and applies Quick View actions.
     */
    public void openFirstLeadAndApplyQuickView(QuickViewData data) {

        if (data != null && data.getStatus() != null) {
            quickUIActions.selectStatus(data.getStatus());
        }
        
        quickUIActions.openMoreActions();
        quickUIActions.clickEditLead();
        
        
    }



}
