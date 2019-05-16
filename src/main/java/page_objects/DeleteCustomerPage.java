package page_objects;

import org.openqa.selenium.By;
import wrappers.BrowserWrapper;
import wrappers.ElementWrapper;

public class DeleteCustomerPage {

    private static DeleteCustomerPage instance;

    //Locators
    protected ElementWrapper inptCustomerID = new ElementWrapper(By.xpath("//input[@name='cusid']"));
    protected ElementWrapper btnSubmit = new ElementWrapper(By.xpath("//input[@value='Submit']"));

    public static DeleteCustomerPage getInstance() {
        if(instance == null){
            instance = new DeleteCustomerPage();
        }
        return instance;
    }

    public void deleteCustomer(String customerId) {
        inptCustomerID.type(customerId);
        btnSubmit.click();
        if(BrowserWrapper.isAlertDisplayed()) {
            BrowserWrapper.acceptAlert();
            BrowserWrapper.acceptAlert();
        }
    }
}
