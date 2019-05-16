package page_objects;

import org.openqa.selenium.By;
import wrappers.ElementWrapper;

public class LoginPage {

    private static LoginPage instance;

    //Locators
    protected ElementWrapper inptUserID = new ElementWrapper(By.xpath("//input[@name='uid']"));
    protected ElementWrapper inptPasswd = new ElementWrapper(By.xpath("//input[@name='password']"));
    protected ElementWrapper btnLogin = new ElementWrapper(By.xpath("//input[@name='btnLogin']"));
    protected ElementWrapper btnReset = new ElementWrapper(By.xpath("//input[@name='btnReset']"));

    public static LoginPage getInstance() {
        if(instance == null){
            instance = new LoginPage();
        }
        return instance;
    }

    public ManagerPage loginToManagerPage(String userId, String password) {
        inptUserID.type(userId);
        inptPasswd.type(password);
        btnLogin.click();
        return ManagerPage.getInstance();
    }
}
