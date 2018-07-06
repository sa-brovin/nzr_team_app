package TestObjects.InteractionObjects;
import TestObjects.Helpers.MobileActionsHelper;
import TestObjects.InteractionObjects.LoginPage.Locators;
import TestObjects.World;
import Utils.AutotestException;
import Utils.LocatorStorage;
import Utils.Utils;
import Utils.Wait;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static TestObjects.Helpers.MobileActionsHelper.s;
import static TestObjects.World.testSession;
import static Utils.TestSettings.shortTimeOut;
import static Utils.TestSettings.veryShortTimeOut;

public class WelcomePage extends AbstractPage {
    public String pageName() {
        return "WELCOME";
    }

    protected static class Locators {
        protected static By StartBtn = new LocatorStorage(
                By.id("Get Started Button"),
                By.id("Get Started Button"))
                .getLocator();
        protected static By SkipBtn = new LocatorStorage(
                By.id("Skip Welcome Screen"),
                By.id("Skip Welcome Screen"))
                .getLocator();
        
        protected static By intro1 = new LocatorStorage(
                By.name("FIND AND SAVE TEAMS Find the club and school teams you want to follow"),
                By.id("FIND AND SAVE TEAMS Find the club and school teams you want to follow"))
                .getLocator();
      
    }
    
    private WebElement getIntro1() {
    		return s(Locators.intro1, veryShortTimeOut);
    }
    
    
    public WebElement getStartBtn(){
        return s(Locators.StartBtn, veryShortTimeOut);
    }

    public WebElement getSkipBtn() {
        return s(Locators.SkipBtn, veryShortTimeOut);
    }




}
