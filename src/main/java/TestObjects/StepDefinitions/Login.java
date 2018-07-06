package TestObjects.StepDefinitions;

import TestObjects.Helpers.MobileActionsHelper;
import TestObjects.InteractionObjects.*;
import TestObjects.World;
import Utils.AutotestException;
import Utils.TestSettings;
import Utils.Wait;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.AutoCloseableSoftAssertions;

import static Utils.TestSettings.veryShortTimeOut;

public class Login {
    LoginPage loginPage = new LoginPage();

    @Given("^I logged in to the Team App by user \"([^\"]*)\"$")
    public void i_logged_in_to_the_Team_App_by_user(String userName) throws Throwable {
        if (loginPage.isPageOpen())
        {
            TestSettings.Account acc = TestSettings.accounts.get(userName);
            loginPage.login(acc.email, acc.password);
        }
    }

    @Given("^I open the Team App$")
    public void i_open_the_Team_App() throws Throwable {
        if (!loginPage.isPageOpen())
            new MenuPage().selectLogout();
    }

    @When("^I enter with my \"([^\"]*)\" and click contitue button$")
    public void i_enter_with_my_and_click_contitue_button(String arg1) throws Throwable {
        loginPage.setLogin(arg1);
    }
    @Then("^I see the message \"([^\"]*)\" \"([^\"]*)\"$")
    public void i_see_the_message(String arg1, String arg2) throws Throwable {
    }

    @When("^I enter the password \"([^\"]*)\"$")
    public void i_enter_the_password(String arg1) throws Throwable {
        loginPage.setPassword(arg1);
    }

    @Then("^I should be able to login$")
    public void i_should_be_able_to_login() throws Throwable {
        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {
            softly.assertThat(new SearchPage().isPageOpen() || new MyTeamsPage().isPageOpen()).
                    as("Search page should be opened.").isTrue();
        }
    }

    @Then("^I see the error message \"([^\"]*)\"$")
    public void i_see_the_error_message(String errorMessage) throws Throwable {
        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {
            boolean result = false;
            if (World.isAndroid)
                result = loginPage.getErrorMessage().equalsIgnoreCase(errorMessage);
            else {
                result = Wait.waitFor(() -> loginPage.getStaticTextWithText(errorMessage) != null, veryShortTimeOut);
            }
            softly.assertThat(result).
                    as("Should be error message: %s.", errorMessage).isTrue();
        }
    }

    @When("^I log out$")
    public void i_log_out() throws Throwable {
        new MenuPage().selectLogout();
    }

    @Then("^I see the page Login$")
    public void i_see_the_page_Login() throws Throwable {
        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {
            softly.assertThat(loginPage.isPageOpen()).
                    as("Login page should be opened.").isTrue();
        }
    }

    @Given("^I logged out$")
    public void i_logged_out() throws Throwable {
        if (!loginPage.isPageOpen())
            new MenuPage().selectLogout();
    }

    @Given("^I close the app on my device$")
    public void i_close_the_app_on_my_device() throws Throwable {
        MobileActionsHelper.closeApp();
    }

    @When("^I open the app$")
    public void i_open_the_app() throws Throwable {
        MobileActionsHelper.runApp();
    }
}
