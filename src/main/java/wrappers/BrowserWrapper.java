package wrappers;

import config.setup.BaseConfig;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class BrowserWrapper {

    private static WebDriver currentBrowser;
    private BrowserWrapper browserWrapper;

    // Get web driver instance
    public static WebDriver getDriverInstance() {
        try {
            if (currentBrowser == null){
                BaseConfig base = new BaseConfig();
                base.setUp();
                currentBrowser = base.getDriver();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return currentBrowser;
    }

    public BrowserWrapper() {
        BrowserWrapper.getDriverInstance();
    }

    public void setPageLoadTimeOut(long timeOut) {
        BrowserWrapper.getDriverInstance().manage().timeouts().pageLoadTimeout(timeOut*1000, TimeUnit.MILLISECONDS);
    }

    public void setElementTimeOut(long timeOut) {
        BrowserWrapper.getDriverInstance().manage().timeouts().implicitlyWait(timeOut*1000, TimeUnit.MILLISECONDS);
    }

    public void maximizeWindow() {
        BrowserWrapper.getDriverInstance().manage().window().maximize();
    }

    public void get(String url) {
        WebDriver currentBrowser = BrowserWrapper.getDriverInstance();
        currentBrowser.get(url);
        WebDriverWait waitPageLoad = new WebDriverWait(currentBrowser, 60);
        waitPageLoad.until( new Function<WebDriver, Boolean>(){
                public Boolean apply(WebDriver driver) {
                    return ((JavascriptExecutor) BrowserWrapper.currentBrowser).executeScript("return document.readyState").equals("complete");
                }
            }
        );
        setPageLoadTimeOut(60);
        setElementTimeOut(60);
    }

    public static void refreshPage() {
        BrowserWrapper.getDriverInstance().navigate().refresh();
    }

    public static Object executeScript(String script, Object... args) {
        return ((JavascriptExecutor) BrowserWrapper.currentBrowser).executeScript(script, args);
    }

    public static boolean isAlertDisplayed() {
        WebDriverWait wait = new WebDriverWait(BrowserWrapper.getDriverInstance(), 10 /*timeout in seconds*/);
        if(wait.until(ExpectedConditions.alertIsPresent())==null){
            return false;
        } else return true;
    }

    public static void acceptAlert() {
        BrowserWrapper.getDriverInstance().switchTo().alert().accept();
    }

    public static void cancelAlert() {
        BrowserWrapper.getDriverInstance().switchTo().alert().dismiss();
    }

    public void quit() {
        BrowserWrapper.getDriverInstance().quit();
    }
}
