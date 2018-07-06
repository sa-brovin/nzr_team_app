package TestObjects.StepDefinitions;

import TestObjects.Helpers.MobileActionsHelper;
import TestObjects.InteractionObjects.*;
import TestObjects.World;
import Utils.AutotestException;
import Utils.TestSettings;
import Utils.Wait;
import static java.lang.Thread.sleep;
import java.util.concurrent.TimeUnit;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.api.AutoCloseableSoftAssertions;

import static TestObjects.World.testSession;
import static Utils.TestSettings.veryShortTimeOut;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Registration {
	LoginPage loginPage = new LoginPage();
	RegistrationPage registrationPage = new RegistrationPage();
	//GuerrillaMailPage gPage = new GuerrillaMailPage();
	VerifyEmail gPage = new VerifyEmail();
	
	String RandomString = RandomStringUtils.random(5, 97, 121, true, true);
    String email = String.format("%s@sharklasers.com", RandomString);
    String mainTab;
	
    
	@Given("^I am on the Register Screen in Team App$")
	public void i_am_on_the_Register_Screen_in_Team_App() throws Throwable {
	 loginPage.createAccount().click();
	 
	}
	@Given("^I input a valid email$")
	public void i_input_a_valid() throws Throwable {
	     
     registrationPage.setRegister(email);
	}
	
	@When("^I input an existed account \"([^\"]*)\"$")
	public void i_input_an_existed_account(String arg1) throws Throwable {
		registrationPage.setRegister(arg1);
	}
	
	@When("^I input an invalid \"([^\"]*)\"$")
	public void i_input_an_invalid(String arg1) throws Throwable {
		registrationPage.setRegister(arg1);
	}

	
	@When("^I enter \"([^\"]*)\" and re-enter \"([^\"]*)\"$")
	public void i_enter_and_re_enter(String pw1, String pw2) throws Throwable {
	    registrationPage.setPassword(pw1, pw2);
	}

	@When("^I tick the option \"([^\"]*)\"$")
	public void i_tick_the_option(String arg1) throws Throwable {
	   registrationPage.setTickTermsConditions();
	}
	
	@When("^I click \"([^\"]*)\"$")
	public void i_click(String arg1) throws Throwable {
	  registrationPage.setSignUpBtn();
	}

	@Then("^I should see message \"([^\"]*)\"$")
	public void i_should_see_message(String arg1) throws Throwable {
	    

	        String verificationText = registrationPage.getVerifyEmailText().getText();
	        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {
	            softly.assertThat(verificationText).
	                    as("Email verification message should contain text: %s", arg1).containsIgnoringCase(arg1);
	        }
	}
	
	@Then("^I get an Email with instructions to verify my account$")
	public void i_get_an_Email_with_instructions_to_verify_my_account() throws Throwable {
		  gPage.openPage();
		  
	//	  testSession.get("https://www.guerrillamail.com/inbox");
	        //to do pass the email address  gPage.openInbox(email);
	//	  gPage.openInbox("test@sharklasers.com");
//	        gPage.selectEmail("Welcome to New Zealand Rugby - Please verify your email");
//
//	        String emailText = gPage.getEmailText();
//	        String template = "Welcome to New Zealand Rugby!";
//	        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {
//	            softly.assertThat(emailText).
//	                    as("Email text should contain:%s. But was: %s", template, emailText).containsIgnoringCase(template);
//	        }
	}
	
	@When("^I confirm the account in my email$")
	public void i_confirm_the_account_in_my_email() throws Throwable {
//		mainTab = getWebDriver().getWindowHandle();
//        gPage.clickLink("Confirm my account");
	}
	
	@Then("^I should be redirected to the successful verification page$")
	public void i_should_be_redirected_to_the_successful_verification_page() throws Throwable {
		//To do
		
	}
	
	@Then("^I am on the sign in page of the TeamApp after several seconds$")
	public void i_am_on_the_sign_in_page_of_the_TeamApp_after_several_seconds() throws Throwable {
		//To do
		TimeUnit.SECONDS.sleep(3);
		try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {
            softly.assertThat(loginPage.isPageOpen()).
                    as("Login page should be opened.").isTrue();
        }
        
	}
}
	

