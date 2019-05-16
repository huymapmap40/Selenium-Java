package page_objects;

import data_objects.Customer;
import org.openqa.selenium.By;
import wrappers.ElementWrapper;

public class NewCustomerPage {

    private static NewCustomerPage instance;

    //Locators
    protected ElementWrapper inptCustomerName = new ElementWrapper(By.xpath("//input[@name='name']"));
    protected ElementWrapper inptBirthDay = new ElementWrapper(By.xpath("//input[@name='dob']"));
    protected ElementWrapper txtAddress = new ElementWrapper(By.xpath("//textarea[@name='addr']"));
    protected ElementWrapper inptCity = new ElementWrapper(By.xpath("//input[@name='city']"));
    protected ElementWrapper inptState = new ElementWrapper(By.xpath("//input[@name='state']"));
    protected ElementWrapper inptPinNumber = new ElementWrapper(By.xpath("//input[@name='pinno']"));
    protected ElementWrapper inptPhoneNumber = new ElementWrapper(By.xpath("//input[@name='telephoneno']"));
    protected ElementWrapper inptEmailId = new ElementWrapper(By.xpath("//input[@name='emailid']"));
    protected ElementWrapper inptPassword = new ElementWrapper(By.xpath("//input[@name='password']"));
    protected ElementWrapper btnSubmit = new ElementWrapper(By.xpath("//input[@value='Submit']"));
    protected ElementWrapper btnReset = new ElementWrapper(By.xpath("//input[@value='Reset']"));
    protected ElementWrapper lblSuccessCreation = new ElementWrapper(By.xpath("//table[@id='customer']//p[@class='heading3']"));


    //Dynamic controls
    protected ElementWrapper customerInfo(String infoType) {
        return new ElementWrapper(By.xpath("//td[text()='"+ infoType +"']/following-sibling::td"));
    }

    public static NewCustomerPage getInstance() {
        if(instance == null){
            instance = new NewCustomerPage();
        }
        return instance;
    }

    public void addNewCustomer(Customer newCustomer) {
        // Fill all customer infomation
        inptCustomerName.type(newCustomer.getName());
        inptBirthDay.type(newCustomer.getBirthdate());
        txtAddress.type(newCustomer.getAddress());
        inptCity.type(newCustomer.getCity());
        inptState.type(newCustomer.getState());
        inptPinNumber.type(newCustomer.getPin());
        inptPhoneNumber.type(newCustomer.getMobileNumber());
        inptEmailId.type(newCustomer.getEmail());
        inptPassword.type(newCustomer.getPassword());

        // Click submit
        btnSubmit.click();
    }

    public boolean isSuccessCustomerCreationDisplayed() {
        return lblSuccessCreation.isElementDisplayed();
    }

    public String getSuccessCreationLabel() {
        return lblSuccessCreation.getText();
    }

    public String getCustomerId() {
        return customerInfo("Customer ID").getText();
    }

    public String getCustomerName() {
        return customerInfo("Customer Name").getText();
    }

    public String getCustomerGender() {
        return customerInfo("Gender").getText();
    }

    public String getCustomerBrithDate() {
        return customerInfo("Birthdate").getText();
    }

    public String getCustomerAddress() {
        return customerInfo("Address").getText();
    }

    public String getCustomerCity() {
        return customerInfo("City").getText();
    }

    public String getCustomerState() {
        return customerInfo("State").getText();
    }

    public String getCustomerPin() {
        return customerInfo("Pin").getText();
    }

    public String getCustomerMobileNo() {
        return customerInfo("Mobile No.").getText();
    }

    public String getCustomerEmail() {
        return customerInfo("Email").getText();
    }
}
