package TestObjects.StepDefinitions;

import TestObjects.Helpers.MobileActionsHelper;
import TestObjects.InteractionObjects.*;
import Utils.AutotestException;
import Utils.Wait;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.openqa.selenium.WebElement;

import static Utils.TestSettings.longTimeOut;
import static Utils.TestSettings.shortTimeOut;
import static Utils.TestSettings.veryShortTimeOut;

public class ShowMyTeams {

    SearchPage searchPage = new SearchPage();
    TeamListPage teamListPage = new TeamListPage();
    MyTeamsPage myTeamsPage = new MyTeamsPage();
    MenuPage menuPage = new MenuPage();

    @Given("^Clear my teams$")
    public void clear_my_teams() throws Throwable {
        if (!searchPage.isPageOpen()) {
           new MenuPage().selectClearTeams();
           new ClearMyTeamsPage().removeAllMyTeams();
        }
    }

    @When("^I select a team \"([^\"]*)\"$")
    public void i_select_a_team(String teamName) throws Throwable {
        teamListPage.selectTeam(teamName);
    }

    @When("^I add team to my teams$")
    public void i_add_team_to_my_teams() throws Throwable {
        new FixturesPage().saveTeam();
    }

    @Then("^I should be able to see the team in my team list$")
    public void i_should_be_able_to_see_the_team_in_my_team_list() throws Throwable {
        new MenuPage().selectMyTeams();
        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {
            softly.assertThat(new MyTeamsPage().getAllTeams().size() > 0).
                    as("Team should be added to my teams.").isTrue();
        }
    }

    private void addTeamToMyTeams(String club, String teamGrade, String team) throws AutotestException {
        SearchPage sp = new SearchPage();
        sp.search(club);
        sp.selectRecord(club);
        new GradeListPage().selectGrade(teamGrade);
        new TeamListPage().selectTeam(team);
        new FixturesPage().saveTeam();
    }

    @Given("^that I followed more than one team$")
    public void that_I_followed_more_than_one_team() throws Throwable {
        addTeamToMyTeams("Tawa Rugby Football Club", "Premier", "Premier");
        new MenuPage().open();
        Thread.sleep(500);
        Thread.sleep(500);
        new MenuPage().selectSearch();
        addTeamToMyTeams("Te Aroha Junior Rugby", "Under 11", "Te Aroha U11");
    }

    @Given("^I should see the icon of the club or school \"([^\"]*)\"$")
    public void i_should_see_the_icon_of_the_club_or_school(String club) throws Throwable {
        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {
        if (!menuPage.isPageOpen() )  
         	menuPage.selectMyTeams();
        WebElement teamClub =myTeamsPage.getClubTest(club);
        softly.assertThat(teamClub.isDisplayed()).
        as("The team's club %s does not exist.", club).isTrue();
//            softly.assertThat(myTeamsPage.isLogoExist(teamTitle)).
//                    as("Logo for team %s is not exists.", teamTitle).isTrue();
        }
    }

    @Given("^I should see the \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void i_should_see_the(String name, String title, String grade, String date) throws Throwable {
        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {
            new MenuPage().selectMyTeams();
//            String teamName = myTeamsPage.getTeam
//            softly.assertThat(teamName.equalsIgnoreCase(name)).
//                    as("Team name should be: %s. But was: %s", name, teamName).isTrue();

            WebElement teamTitle =myTeamsPage.getTeamTest(title);
            Wait.waitFor(() -> teamTitle.isDisplayed(), veryShortTimeOut);
            System.out.println("******************element is ");
            System.out.print(teamTitle.isDisplayed());
            System.out.println("******************");
            softly.assertThat(teamTitle.isDisplayed()).
                    as("Your team %s doesn't exist.", title).isTrue();
            

            WebElement teamGrade = myTeamsPage.getTeamGradeTest(grade);
            softly.assertThat(teamGrade.isDisplayed()).
            as("Your team grade %s doesn't exist.", grade).isTrue();
  
if( !date.equals("none"))
{
	WebElement teamDate = myTeamsPage.getTeamDateTest(date);
            softly.assertThat(teamDate.isDisplayed()).
                    as("Next match date %s doesn't exist. ", date).isTrue();
}
        }
    }

}
