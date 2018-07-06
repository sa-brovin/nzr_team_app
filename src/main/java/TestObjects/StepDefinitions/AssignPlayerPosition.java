package TestObjects.StepDefinitions;
import TestObjects.Helpers.MobileActionsHelper;
import TestObjects.InteractionObjects.*;
import Utils.Wait;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.AutoCloseableSoftAssertions;

import static Utils.TestSettings.shortTimeOut;
import static Utils.TestSettings.testEnv;
import static Utils.TestSettings.veryShortTimeOut;
public class AssignPlayerPosition {
	
    SearchPage searchPage = new SearchPage();
    GradeListPage gradeListPage = new GradeListPage();
    TeamListPage teamListPage = new TeamListPage();
    WelcomePage welcomePage = new WelcomePage();
PlayerPosition playerPositionPage = new PlayerPosition();

@When("^I am on the \"([^\"]*)\" tab$")
public void i_am_on_the_tab(String arg1) throws Throwable {
	MobileActionsHelper.SlideToLeft();
	Thread.sleep(100);
}

@When("^I click assign for the \"([^\"]*)\"$")
public void i_click_assign_for_the(String arg1) throws Throwable {
     playerPositionPage.assignPosition("Assign");
	
}

@Then("^the \"([^\"]*)\" screen should prompt$")
public void the_screen_should_prompt(String arg1) throws Throwable {

	}
}
