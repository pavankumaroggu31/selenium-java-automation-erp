package datamodels.users;

public class UserEMPRoles {

    private UserData PRE_SALES;
    private UserData SITE_ADMIN;
    private UserData SALES_EXEC;

    public UserData getPreSales() {
        return PRE_SALES;
    }

    public UserData getSiteAdmin() {
        return SITE_ADMIN;
    }

    public UserData getSalesExec() {
        return SALES_EXEC;
    }
}
