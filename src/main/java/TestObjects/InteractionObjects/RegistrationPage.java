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
	import static java.lang.Thread.sleep;

	public class RegistrationPage extends AbstractPage {
	    public String pageName() {
	        return "SIGN UP";
	    }
         String selectFlag;
	    protected static class Locators {
	        protected static By emailInput = new LocatorStorage(
	                By.id("Email Input"),
	                By.id("Email Input"))
	                .getLocator();
	        protected static By passwordInput = new LocatorStorage(
	                By.id("Password Input"),
	                By.id("Password Input"))
	                .getLocator();
	        
	        protected static By passwordReInput = new LocatorStorage(
	        		    By.id("Password ReInput"),
	        		    By.id("Password ReInput"))
	        		    .getLocator();
	        
	        protected static By continueBtn = new LocatorStorage(
	                By.id("Continue Button"),
	                By.id("Continue Button"))
	                .getLocator();

	        protected static By errorMessage = new LocatorStorage(
	                By.id(""),
	                By.id("Error Message"))
	                .getLocator();
        
//	                By.id("Terms & Conditions I agree to our Terms & Conditions"),
//	                By.id("Terms & Conditions I agree to our Terms & Conditions"))
	        		protected static By termsConditions = new LocatorStorage(
	                        By.id("Terms & Conditions"),
	                        By.id("Terms & Conditions"))
	                        .getLocator();
   
	        protected static By signUpBtn = new LocatorStorage(
	        		    By.id("Sign Up Button"),
	        		    	By.id("Sign Up Button"))
	        		    .getLocator();	
	        //To do
	        protected static By VerifyEmailText = new  LocatorStorage(
	        		  By.id("Send verify Email"),
      		    	  By.id("Send verify Email"))
      		    .getLocator();	
	       
	    }

	    private WebElement getEmail() {
	        return s(Locators.emailInput, veryShortTimeOut);
	    }

	    private WebElement getPassword() {
	        return s(Locators.passwordInput, veryShortTimeOut);
	    }
 
	    private WebElement getPasswordReInput() {
	        return s(Locators.passwordReInput, veryShortTimeOut);
	    }
	    
	    private WebElement getContinueBtn() { //throws AutotestException {
	        return s(Locators.continueBtn, veryShortTimeOut);
	    }
	    
	    private WebElement getTermsConditions() {
	        return s(Locators.termsConditions, veryShortTimeOut);
	    }

	    private WebElement getSignUpBtn() { //throws AutotestException {
	        return s(Locators.signUpBtn, veryShortTimeOut);
	    }
	    
	    public WebElement getVerifyEmailText() { //throws AutotestException {
	        return s(Locators.VerifyEmailText, veryShortTimeOut);
	    }

	    public void register(String email, String pass1, String pass2) throws AutotestException {
	       setRegister(email);
	       setPassword(pass1,pass2);
	    }

	    public void setRegister(String Email) throws AutotestException {
	        Wait.waitFor(() -> getEmail() != null, veryShortTimeOut);
	        getEmail().sendKeys(Email);
	        testSession.hideKeyboard();
	        MobileActionsHelper.tapAtElement(getContinueBtn());
	    }

	    public void setPassword(String pass1, String pass2){
	        getPassword().sendKeys(pass1);
	        getPasswordReInput().sendKeys(pass2);
	        testSession.hideKeyboard();	        
	        try {
                sleep(2000);
            } catch (Exception e) {
            }
	    }

	    	public void setTickTermsConditions() {
	    		//if (!getTermsConditions().isSelected())
	    		testSession.hideKeyboard();
	    		MobileActionsHelper.tapAtElement(getTermsConditions());
	    		
	    		//getTermsConditions().click();
	    		
//	    		if (getTermsConditions().isSelected())
//	    		selectFlag="True";
//	    		else 
//	    			selectFlag="False";
//	    			
//	    		System.out.println("The check box is " + getTermsConditions().isSelected());


	    	}	
	    	
	    public void setSignUpBtn() {
	    	getSignUpBtn().click();
	    	}
	      	
}


