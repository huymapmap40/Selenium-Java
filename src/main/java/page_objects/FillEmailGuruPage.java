package page_objects;

import org.openqa.selenium.By;
import wrappers.ElementWrapper;

public class FillEmailGuruPage {

    private static FillEmailGuruPage instance;

    //Locators
    protected ElementWrapper inptEmailAddress = new ElementWrapper(By.xpath("//input[@name='emailid']"));
    protected ElementWrapper btnSubmit = new ElementWrapper(By.xpath("//input[@name='btnLogin']"));
    protected ElementWrapper txtUserName = new ElementWrapper(By.xpath("//td[@class='accpage'][contains(text(),'User ID')]/following-sibling::td"));
    protected ElementWrapper txtPasswd = new ElementWrapper(By.xpath("//td[@class='accpage'][contains(text(),'Password')]/following-sibling::td"));

    public static FillEmailGuruPage getInstance() {
        if(instance == null){
            instance = new FillEmailGuruPage();
        }
        return instance;
    }

    public void fillEmailAddress(String email) {
        inptEmailAddress.type(email);
    }

    public void clickSubmit() {
        btnSubmit.click();
    }

    public void fillEmailAndSubmit(String email) {
        fillEmailAddress(email);
        clickSubmit();
    }

    public String getUserName() {
        return txtUserName.getText();
    }

    public String getPassword() {
        return txtPasswd.getText();
    }
}
