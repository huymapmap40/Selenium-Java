package page_objects;

import org.openqa.selenium.By;
import wrappers.BrowserWrapper;
import wrappers.ElementWrapper;

public class DeleteAccountPage {

    private static DeleteAccountPage instance;

    //Locators
    protected ElementWrapper inptAccountID = new ElementWrapper(By.xpath("//input[@name='accountno']"));
    protected ElementWrapper btnSubmit = new ElementWrapper(By.xpath("//input[@value='Submit']"));

    public static DeleteAccountPage getInstance() {
        if(instance == null){
            instance = new DeleteAccountPage();
        }
        return instance;
    }

    public void deleteAccount(String accountId) {
        inptAccountID.type(accountId);
        btnSubmit.click();
        if(BrowserWrapper.isAlertDisplayed()) {
            BrowserWrapper.acceptAlert();
            BrowserWrapper.acceptAlert();
        }
    }
}
