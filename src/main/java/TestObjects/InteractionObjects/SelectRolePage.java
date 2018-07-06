package TestObjects.InteractionObjects;

import static TestObjects.Helpers.MobileActionsHelper.s;
import static Utils.TestSettings.veryShortTimeOut;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import TestObjects.InteractionObjects.WelcomePage.Locators;
import Utils.LocatorStorage;

public class SelectRolePage extends AbstractPage {
	 public String pageName() {
	        return "Please select your role below";
	    }

	    protected static class Locators {
	        protected static By PlayerBtn = new LocatorStorage(
	                By.id("Player"),
	                By.id("Player"))
	                .getLocator();
	        protected static By CoachBtn = new LocatorStorage(
	                By.id("Coach"),
	                By.id("Coach"))
	                .getLocator();
	        
    		protected static By termsConditions = new LocatorStorage(
                    By.id("Terms & Conditions"),
                    By.id("Terms & Conditions"))
                    .getLocator();
    		
	        protected static By continueBtn = new LocatorStorage(
	                By.id("Continue Button"),
	                By.id("Continue Button"))
	                .getLocator();
	        


}
	    public WebElement getPlayerBtn(){
	        return s(Locators.PlayerBtn, veryShortTimeOut);
	    }

	    public WebElement getCoachBtn() {
	        return s(Locators.CoachBtn, veryShortTimeOut);
	    }
	    
	    private WebElement getContinueBtn() { //throws AutotestException {
	        return s(Locators.continueBtn, veryShortTimeOut);
	    }
	    
	    private WebElement getTermsConditions() {
	        return s(Locators.termsConditions, veryShortTimeOut);
	    }
	    
	    public void clickContinue() {
	    	getContinueBtn().click();
	    }
	    public void checkTermsConditions() {
	    	getTermsConditions().click();
	    }
	    
	    
}
