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

public class FindTeam {
    SearchPage searchPage = new SearchPage();
    GradeListPage gradeListPage = new GradeListPage();
    TeamListPage teamListPage = new TeamListPage();
    WelcomePage welcomePage = new WelcomePage();
    MyTeamsPage myTeamsPage = new MyTeamsPage();


@Given("^I view the introduction of Team App$")
public void i_view_the_introduction_of_Team_App() throws Throwable {
	MobileActionsHelper.SlideToLeft();
	Thread.sleep(100);
	
	MobileActionsHelper.SlideToLeft();
	Thread.sleep(100);
	
	MobileActionsHelper.SlideToLeft();
	Thread.sleep(100);
	
	
}

@Then("^I get started$")
public void i_get_started() throws Throwable {
	welcomePage.getStartBtn().click();

}

    
    @Given("^I'm on \"([^\"]*)\" page$")
    public void i_m_on_page(String pageName) throws Throwable {
        //boolean isOpen = Wait.waitFor(() -> searchPage.isPageOpen(), shortTimeOut);
    	switch (pageName) {
        case "Find A CLUB OR SCHOOL":
        {if (!searchPage.isPageOpen())
            searchPage.open();
        break;
        }
//        
//        case "Welcome":
//        {if (!welcomePage.isPageOpen())
//            welcomePage
//        break;}
    	}
    }

    @When("^I skip the welcome page$")
    public void i_skip_the_welcome_page() throws Throwable {
    	if (welcomePage.isPageOpen())
    	welcomePage.getSkipBtn().click();

    }
    @Then("^I should redirect to \"([^\"]*)\" page$")
    public void i_should_redirect_to_page(String pageName) throws Throwable {
        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {
            softly.assertThat(searchPage.isPageOpen()).
                    as("Not redirect to search page").isTrue();
        }
    }
    
    @When("^Find team \"([^\"]*)\"$")
    public void find_team(String teamName) throws Throwable {
        searchPage.search(teamName);
    }

    @When("^Select search result \"([^\"]*)\"$")
    public void select_search_result(String teamName) throws Throwable {
        searchPage.selectRecord(teamName);
    }

    @Then("^Team \"([^\"]*)\" should be in search results$")
    public void team_should_be_in_search_results(String teamName) throws Throwable {
        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {
            softly.assertThat(searchPage.searchResultsContainRecord(teamName)).
                    as("Team should be founded").isTrue();
        }
    }

    @When("^Select grade \"([^\"]*)\"$")
    public void select_grade(String gradeName) throws Throwable {
        gradeListPage.selectGrade(gradeName);
    }

    @When("^Select team \"([^\"]*)\"$")
    public void select_team(String teamName) throws Throwable {
        teamListPage.selectTeam(teamName);
    }

    @Then("^Match \"([^\"]*)\" should be opened$")
    public void match_should_be_opened(String arg1) throws Throwable {
        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {
            String pageHeader = new OverviewPage().getCurrentPageName(arg1);
            softly.assertThat(pageHeader).
                    as("Page header should be equal: %s. But was: %s", arg1, pageHeader).isEqualToIgnoringCase(arg1);
        }
    }
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////


    @Given("^I am on the Menu page$")
    public void i_am_on_the_Menu_page() throws Throwable {
        MenuPage mp = new MenuPage();
        if (!mp.isPageOpen()) {
            Wait.waitFor(() -> mp.isPageOpen(), veryShortTimeOut);
        }
        mp.open();
    }

    @Given("^I select \"([^\"]*)\"$")
    public void i_select(String itemName) throws Throwable {
        new MenuPage().selectSearch();// selectMenuItem(itemName);
    }

    @When("^I input the keyword for my school or club \"([^\"]*)\"$")
    public void i_input_the_keyword_for_my_school_or_club(String teamName) throws Throwable {
        searchPage.search(teamName);
    }

    @When("^I input the keyword for my school or club not exists$")
    public void i_input_the_keyword_for_my_school_or_club_not_exists() throws Throwable {
        searchPage.search("club not exists");
    }

    @Then("^the club or school including the keyword should be listed \"([^\"]*)\"$")
    public void the_club_or_school_including_the_keyword_should_be_listed(String teamName) throws Throwable {
        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {
            softly.assertThat(searchPage.searchResultsContainRecord(teamName)).
                    as("Search results should contain record: %s.", teamName).isTrue();
        }
    }

    @When("^I select a club or school \"([^\"]*)\"$")
    public void i_select_a_club_or_school(String teamName) throws Throwable {
        searchPage.selectRecord(teamName);
    }

    @When("^select the corresponding grade \"([^\"]*)\"$")
    public void select_the_corresponding_grade(String gradeName) throws Throwable {
        gradeListPage.selectGrade(gradeName);
    }

    @Then("^I should be able to see the list of teams$")
    public void i_should_be_able_to_see_the_list_of_teams() throws Throwable {
        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {
            softly.assertThat(teamListPage.getListItems().size() > 0).
                    as("Team count should be greater than 0.").isTrue();
        }
    }

    @Then("^I should see error message \"([^\"]*)\"$")
    public void i_should_see_error_message(String errMessage) throws Throwable {
        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {
            softly.assertThat(searchPage.isErrorExist(errMessage)).
                    as("Should be error message.").isTrue();
        }
    }

    @Given("^it is not the first time I use Team App$")
    public void it_is_not_the_first_time_I_use_Team_App() throws Throwable {
        // TODO: not implemented.
    }

    @When("^I click go back on the left top corner$")
    public void i_click_go_back_on_the_left_top_corner() throws Throwable {
        gradeListPage.back();
    }

    @Then("^I should be able to go back to the search page$")
    public void i_should_be_able_to_go_back_to_the_search_page() throws Throwable {
        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {
            softly.assertThat(searchPage.isPageOpen()).
                    as("Search page should be opened.").isTrue();
        }
    }

    @Given("^I use Team App at the first time$")
    public void i_use_Team_App_at_the_first_time() throws Throwable {
        //MobileActionsHelper.resetApp();
        if (new MenuPage().menuButtonExists()) {
            new MenuPage().selectClearTeams();
            new ClearMyTeamsPage().removeAllMyTeams();
        }
    }

    @Given("^I open the Team APP$")
    public void i_open_the_Team_APP() throws Throwable {
        // App is running automatically, this method only for logging.
    }

    @Given("^I should be redirected to the \"([^\"]*)\" screen$")
    public void i_should_be_redirected_to_the_screen(String arg1) throws Throwable {
        
        
        switch (arg1) {
        case "My Teams":
        {
        boolean isPageOpen1 = myTeamsPage.isPageOpen();
        	if (!isPageOpen1)
        		isPageOpen1 = Wait.waitFor(() -> myTeamsPage.isPageOpen(), shortTimeOut);
            try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {
                softly.assertThat(isPageOpen1).
                        as(arg1 + " page should be opened.").isTrue();}
        }
        break;
       
        case  "Find A CLUB OR SCHOOL":
        { boolean isPageOpen2 = searchPage.isPageOpen();
        if (!isPageOpen2)
        	     isPageOpen2 = Wait.waitFor(() -> searchPage.isPageOpen(), shortTimeOut);

        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {
            softly.assertThat(isPageOpen2).
                    as("Search page should be opened.").isTrue();
        }

        }
        }
    }
    //To do 
//        private void redirectPage(Boolean boolean1, String String1) {
//        	
//        }
    
}
