package wrappers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;

public class ElementWrapper {

    private int elementTimeout = 30;
    private By locators;
    private WebElement element;
    private WebDriverWait wait;

    public ElementWrapper(By locators) {
        this.locators = locators;
        this.wait = new WebDriverWait(BrowserWrapper.getDriverInstance(), elementTimeout);
//        this.element = BrowserWrapper.getDriverInstance().findElement(this.locators);
    }

    private WebElement getElement() {
        this.element = BrowserWrapper.getDriverInstance().findElement(this.locators);
        return this.element;
    }

    public void waitClickable() {
//        timeOut = elementTimeout;
        this.wait.until(ExpectedConditions.elementToBeClickable(this.locators));
    }

    public void waitForPresenceOf() {
        this.wait.until(ExpectedConditions.presenceOfElementLocated(this.locators));
    }

    public void waitForVisibilityOf() {
        this.wait.until(ExpectedConditions.visibilityOfElementLocated(this.locators));
    }

    public void click() {
        waitClickable();
//        this.element.click();
        getElement().click();
    }

    public boolean isElementDisplayed() {
        boolean isDisplayed;
        this.waitForVisibilityOf();
        try {
//            isDisplayed = this.element.isDisplayed();
            isDisplayed = getElement().isDisplayed();
        } catch (Exception e) {
            throw new Error("No element found using locator");
        }
        return isDisplayed;
    }

    public int getElementCount() {
        return getElement().findElements(this.locators).size();
    }

    public void type(String text) {
        this.waitForVisibilityOf();
        try {
            getElement().sendKeys(text);
        } catch (Exception e) {
            throw new Error("No element found using locator");
        }
    }

    public String getText() {
        this.waitForVisibilityOf();
        try {
            return getElement().getText();
        } catch (Exception e) {
            throw new Error("No element found using locator");
        }
    }

    public void moveMouseAndClick() {
        try {
            Actions actions = new Actions(BrowserWrapper.getDriverInstance());
            actions.moveToElement(getElement()).click().perform();
        } catch (Exception e) {
            throw new Error("No element found using locator");
        }
    }

}
