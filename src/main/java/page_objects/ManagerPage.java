package page_objects;

import org.openqa.selenium.By;
import wrappers.ElementWrapper;

public class ManagerPage {

    private static ManagerPage instance;

    //Locators
    protected ElementWrapper mnNewCustomer = new ElementWrapper(By.xpath("//ul[@class='menusubnav']/li/a[text()='New Customer']"));
    protected ElementWrapper mnNewAccount = new ElementWrapper(By.xpath("//ul[@class='menusubnav']/li/a[text()='New Account']"));
    protected ElementWrapper mnDeposit = new ElementWrapper(By.xpath("//ul[@class='menusubnav']/li/a[text()='Deposit']"));
    protected ElementWrapper mnDeleteAccount = new ElementWrapper(By.xpath("//ul[@class='menusubnav']/li/a[text()='Delete Account']"));
    protected ElementWrapper mnDeleteCustomer = new ElementWrapper(By.xpath("//ul[@class='menusubnav']/li/a[text()='Delete Customer']"));


    public static ManagerPage getInstance() {
        if(instance == null){
            instance = new ManagerPage();
        }
        return instance;
    }

    public NewCustomerPage gotoNewCustomerPage() {
        mnNewCustomer.click();
        return NewCustomerPage.getInstance();
    }

    public NewAccountPage gotoNewAccountPage() {
        mnNewAccount.click();
        return NewAccountPage.getInstance();
    }

    public DepositPage gotoDepositPage() {
        mnDeposit.click();
        return DepositPage.getInstance();
    }

    public DeleteAccountPage gotoDeleteAccounttPage() {
        mnDeleteAccount.click();
        return DeleteAccountPage.getInstance();
    }

    public DeleteCustomerPage gotoDeleteCustomerPage() {
        mnDeleteCustomer.click();
        return DeleteCustomerPage.getInstance();
    }
}
