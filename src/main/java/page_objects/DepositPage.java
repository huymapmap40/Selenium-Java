package page_objects;

import org.openqa.selenium.By;
import wrappers.ElementWrapper;

public class DepositPage {

    private static DepositPage instance;

    //Locators
    protected ElementWrapper inptAccountNo = new ElementWrapper(By.xpath("//input[@name='accountno']"));
    protected ElementWrapper inptAmount = new ElementWrapper(By.xpath("//input[@name='ammount']"));
    protected ElementWrapper inptDescription = new ElementWrapper(By.xpath("//input[@name='desc']"));
    protected ElementWrapper btnSubmit = new ElementWrapper(By.xpath("//input[@name='AccSubmit']"));
    protected ElementWrapper lblSuccessDeposit = new ElementWrapper(By.xpath("//table[@id='deposit']//p[@class='heading3']"));

    // Dynamics control
    protected  ElementWrapper depositInfo(String infoType) {
        return new ElementWrapper(By.xpath("//td[text()='"+ infoType +"']/following-sibling::td"));
    }

    public static DepositPage getInstance() {
        if(instance == null){
            instance = new DepositPage();
        }
        return instance;
    }

    public void addNewDeposit(String accountId, int amount, String description) {
        inptAccountNo.type(accountId);
        inptAmount.type(Integer.toString(amount));
        inptDescription.type(description);
        btnSubmit.click();
    }

    public boolean isSuccessDepositMessageDisplayed() {
        return lblSuccessDeposit.isElementDisplayed();
    }

    public String getSuccessDepositMessage() {
        return lblSuccessDeposit.getText();
    }

    public String getTransactionID() {
        return depositInfo("Transaction ID").getText();
    }

    public String getAccountID() {
        return depositInfo("Account No").getText();
    }

    public String getAmountCredited() {
        return depositInfo("Amount Credited").getText();
    }

    public String getTransactionType() {
        return depositInfo("Type of Transaction").getText();
    }

    public String getDescription() {
        return depositInfo("Description").getText();
    }

    public String getCurrentBalance() {
        return depositInfo("Current Balance").getText();
    }
}
