package TestObjects.InteractionObjects;

import TestObjects.Helpers.MobileActionsHelper;
import TestObjects.InteractionObjects.LoginPage.Locators;
import Utils.AutotestException;
import Utils.LocatorStorage;
import Utils.Utils;
import Utils.Wait;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static TestObjects.Helpers.MobileActionsHelper.s;
import static TestObjects.World.isAndroid;
import static Utils.TestSettings.longTimeOut;
import static Utils.TestSettings.shortTimeOut;
import static Utils.TestSettings.veryShortTimeOut;

public class MenuPage extends AbstractPage {

    public static class Locators {
        protected static By searchItem = new LocatorStorage(
                //By.name("Find a team"),
        		By.id("Search"),
                By.id("Search"))
                .getLocator();

        protected static By myTeamsItem = new LocatorStorage(
               // By.name("My teams"),
        		By.id("Teams"),
                By.id("Teams"))
                .getLocator();

        protected static By clearTeamsItem = new LocatorStorage(
               // By.name("Terms of use"),
                By.id("Close"),
                By.id("Close"))
                .getLocator();

        protected static By logoutItem = new LocatorStorage(
                By.name("Logout"),
                By.id("Logout"))
                .getLocator();

        protected static By menuButton = new LocatorStorage(
                By.id("Menu Button"))
                .getLocator();
    }
public WebElement getClearTeamsItem() {
        return s(Locators.clearTeamsItem, shortTimeOut);
    
}
    public void selectMenuItem(String itemName) {
        if (isAndroid)
          MobileActionsHelper.tapAtElement(getTextViewWithText(itemName));
        else
          MobileActionsHelper.tapAtElement(getStaticTextWithText(itemName));
    }

    public void selectSearch() throws AutotestException, InterruptedException {
        open();
        WebElement menuItem = MobileActionsHelper.s(Locators.searchItem, shortTimeOut);
        MobileActionsHelper.tapAtElement(menuItem);
    }

    public void selectMyTeams() throws AutotestException, InterruptedException {
        open();
        WebElement menuItem = MobileActionsHelper.s(Locators.myTeamsItem, shortTimeOut);
        MobileActionsHelper.tapAtElement(menuItem);
    }

    public void selectClearTeams() throws AutotestException, InterruptedException {
    

    //WebElement menuItem = MobileActionsHelper.s(Locators.clearTeamsItem, shortTimeOut);
    		open();
   // 	Wait.waitFor(() -> getClearTeamsItem().isDisplayed(), longTimeOut);
   		MobileActionsHelper.tapAtElement(getClearTeamsItem());
   		//getClearTeamsItem().click();
    }

    public void selectLogout() throws AutotestException, InterruptedException {
        open();
        WebElement menuItem = MobileActionsHelper.s(Locators.logoutItem, shortTimeOut);
        MobileActionsHelper.tapAtElement(menuItem);
    }

    public boolean isPageOpen() {
        WebElement menuItem = MobileActionsHelper.s(Locators.searchItem, 1000);
        return menuItem != null;
    }

    public void open() throws AutotestException, InterruptedException {
        //if (!isPageOpen())
        WebElement menuButton = MobileActionsHelper.s(Locators.menuButton, veryShortTimeOut);
                //Utils.getLast(MobileActionsHelper.ss(Locators.menuButton, veryShortTimeOut));
        if (menuButton != null) {
            if (isAndroid)
                while(MobileActionsHelper.s(Locators.myTeamsItem, 2000) == null){
                menuButton.click();
                Thread.sleep(500);
            }
         else
             while(menuButton.isDisplayed()){
                menuButton.click();
                Thread.sleep(500);
            }
        }
        else
          throw new AutotestException("Menu button is not accessible.");
        //MobileActionsHelper.slideToRight();
    }

    public boolean menuButtonExists()
    {
        return MobileActionsHelper.s(Locators.menuButton, veryShortTimeOut) != null;
    }
}
