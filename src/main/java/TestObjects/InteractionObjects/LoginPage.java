package TestObjects.InteractionObjects;

import TestObjects.Helpers.MobileActionsHelper;
import Utils.AutotestException;
import Utils.LocatorStorage;
import Utils.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static TestObjects.Helpers.MobileActionsHelper.hideKeyboard;
import static TestObjects.Helpers.MobileActionsHelper.s;
import static TestObjects.World.isAndroid;
import static TestObjects.World.testSession;
import static Utils.TestSettings.veryShortTimeOut;

public class LoginPage extends AbstractPage {
    public String pageName() {
        return "LOG IN";
    }

    protected static class Locators {
        protected static By emailInput = new LocatorStorage(
                By.id("Email Input"),
                By.id("Email Input"))
                .getLocator();
        protected static By passwordInput = new LocatorStorage(
                By.id("Password Input"),
                By.id("Password Input"))
                .getLocator();
        protected static By continueBtn = new LocatorStorage(
                By.id("Continue Button"),
                By.id("Continue Button"))
                .getLocator();
        protected static By loginBtn = new LocatorStorage(
                By.id("Continue Button"),
                By.id("Continue Button"))
                .getLocator();
        protected static By errorMessage = new LocatorStorage(
                By.id(""),
                By.id("Error Message"))
                .getLocator();
        protected static By createAccount = new LocatorStorage(
                By.id("Create account"),
                By.id("Create account"))
                .getLocator();
        
    }

    private WebElement getLogin() {
        return s(Locators.emailInput, veryShortTimeOut);
    }

    private WebElement getPassword() {
        return s(Locators.passwordInput, veryShortTimeOut);
    }

    private WebElement getContinueBtn() throws AutotestException {
        return s(Locators.continueBtn, veryShortTimeOut);
    }
    
    public WebElement createAccount() {
        return s(Locators.createAccount, veryShortTimeOut);
    }

    private WebElement getLoginBtn(){
        /*if (!isAndroid)
        {
            WebElement scrollView = s(By.className("XCUIElementTypeScrollView"), veryShortTimeOut);
            return s(scrollView, Locators.loginBtn, veryShortTimeOut);
        }
        return null;*/
        return s(Locators.loginBtn, veryShortTimeOut);
    }

    public void login(String login, String pass) throws AutotestException {
       setLogin(login);
       setPassword(pass);
    }

    public void setLogin(String login) throws AutotestException {
        Wait.waitFor(() -> getLogin() != null, veryShortTimeOut);
        getLogin().sendKeys(login);
        testSession.hideKeyboard();
        MobileActionsHelper.tapAtElement(getContinueBtn());
    }

    public void setPassword(String pass){
        getPassword().sendKeys(pass);
        getLoginBtn().click();
    }

    public String getErrorMessage() throws AutotestException {
        WebElement error = s(Locators.errorMessage, veryShortTimeOut);
        if (error == null)
            throw new AutotestException("Can't find error message in login screen.");
        return error.getText();
    }
}
