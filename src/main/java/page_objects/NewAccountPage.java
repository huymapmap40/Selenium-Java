package page_objects;

import org.openqa.selenium.By;
import wrappers.ElementWrapper;

public class NewAccountPage {

    private static NewAccountPage instance;

    //Locators
    protected ElementWrapper inptCustomerID = new ElementWrapper(By.xpath("//input[@name='cusid']"));
    protected ElementWrapper drpAccountType = new ElementWrapper(By.xpath("//select[@name='selaccount']"));
    protected ElementWrapper inptDepositInitial = new ElementWrapper(By.xpath("//input[@name='inideposit']"));
    protected ElementWrapper btnSubmit = new ElementWrapper(By.xpath("//input[@value='submit']"));
    protected ElementWrapper lblSuccessCreation = new ElementWrapper(By.xpath("//table[@id='account']//p[@class='heading3']"));

    //Dynamic controls
    protected  ElementWrapper optAccountType(String accountType) {
        return new ElementWrapper(By.xpath("//select[@name='selaccount']/option[@value='" + accountType + "']"));
    }

    protected  ElementWrapper accountInfo(String infoType) {
        return new ElementWrapper(By.xpath("//td[text()='"+ infoType +"']/following-sibling::td"));
    }

    public static NewAccountPage getInstance() {
        if(instance == null){
            instance = new NewAccountPage();
        }
        return instance;
    }

    public void addNewAccount(String customerID, String accountType, int depositInitial) {
        inptCustomerID.type(customerID);
        drpAccountType.click();
        optAccountType(accountType).click();
        inptDepositInitial.type(Integer.toString(depositInitial));
        btnSubmit.click();
    }

    public boolean isSuccessAccountMessageDisplayed() {
        return lblSuccessCreation.isElementDisplayed();
    }

    public String getSuccessAccountMessage() {
        return lblSuccessCreation.getText();
    }

    public String getAccountID() {
        return accountInfo("Account ID").getText();
    }

    public String getCustomerID() {
        return accountInfo("Customer ID").getText();
    }

    public String getCustomerName() {
        return accountInfo("Customer Name").getText();
    }

    public String getEmail() {
        return accountInfo("Email").getText();
    }

    public String getAccountType() {
        return accountInfo("Account Type").getText();
    }

    public String getDateOpening() {
        return accountInfo("Date of Opening").getText();
    }

    public int getCurrentAmount() {
        String currentBalance = accountInfo("Current Amount").getText();
        return Integer.valueOf(currentBalance);
    }

}
