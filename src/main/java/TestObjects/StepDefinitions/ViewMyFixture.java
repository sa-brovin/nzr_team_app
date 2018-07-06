package TestObjects.StepDefinitions;

import TestObjects.InteractionObjects.FixturesPage;
import TestObjects.InteractionObjects.OverviewPage;
import TestObjects.World;
import Utils.AutotestException;
import Utils.Wait;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;

import static Utils.TestSettings.veryShortTimeOut;

public class ViewMyFixture {

    FixturesPage fixturesPage = new FixturesPage();
    OverviewPage overviewPage = new OverviewPage();

    @When("^I should go to the upcoming fixtures section$")
    public void i_should_go_to_the_upcoming_fixtures_section() throws Throwable {
        fixturesPage.selectUpcoming();
    }

    @When("^I should go to the played fixtures section$")
    public void i_should_go_to_the_played_fixtures_section() throws Throwable {
        fixturesPage.selectPlayed();
    }

    @When("^Select first game$")
    public void select_first_game() throws Throwable {
        fixturesPage.selectFirstGame();
    }

    @Then("^I should see all \"([^\"]*)\" for my \"([^\"]*)\" with the following details per fixture:$")
    public void i_should_see_all_for_my_with_the_following_details_per_fixture(String arg1, String arg2, List<Map<String, String>> arg3) throws Throwable {
        for (Map<String, String> item : arg3) {
            try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {
                String key = "competition_name";
                String value = item.get(key);
                boolean result = false;
                if (World.isAndroid)
                  result = fixturesPage.getCurrentPageName().equalsIgnoreCase(value);
                else
                  result = fixturesPage.getCurrentPageName(value).equalsIgnoreCase(value);
                softly.assertThat(result).
                        as("Fixtures overview page not contain '%s' value: %s.", "competition_name", value).isTrue();

                WebElement firstRecord = fixturesPage.getAllFixtures().stream().findFirst().orElse(null);
                if (firstRecord == null)
                    throw new AutotestException("No fixture founded.");

                key = "round";
                value = item.get(key);
                result = fixturesPage.getRoundText(firstRecord).equalsIgnoreCase(value);
                softly.assertThat(result).
                        as("Fixtures overview page not contain '%s' value: %s.", "round", value).isTrue();

                key = "match_status";
                value = item.get(key);
                result = fixturesPage.getStatusText(firstRecord).equalsIgnoreCase(value);
                softly.assertThat(result).
                        as("Fixtures overview page not contain '%s' value: %s.", "match_status", value).isTrue();

                key = "match_date";
                value = item.get(key);
                result = fixturesPage.getDateText(firstRecord).equalsIgnoreCase(value);
                softly.assertThat(result).
                        as("Fixtures overview page not contain '%s' value: %s.", "match_date", value).isTrue();

                key = "ground";
                value = item.get(key);
                result = fixturesPage.getLocationText(firstRecord).equalsIgnoreCase(value);
                softly.assertThat(result).
                        as("Fixtures overview page not contain '%s' value: %s.", "ground", value).isTrue();

                key = "my_organisation";
                value = item.get(key);
                result = fixturesPage.getHomeTeamNameText(firstRecord).equalsIgnoreCase(value);
                softly.assertThat(result).
                        as("Fixtures overview page not contain '%s' value: %s.", "my_organisation", value).isTrue();

                key = "my_team";
                value = item.get(key);
                result = fixturesPage.getHomeTeamNameText(firstRecord).equalsIgnoreCase(value);
                softly.assertThat(result).
                        as("Fixtures overview page not contain '%s' value: %s.", "my_team", value).isTrue();

                key = "opponent_organisation";
                value = item.get(key);
                result = fixturesPage.getAwayTeamNameText(firstRecord).equalsIgnoreCase(value);
                softly.assertThat(result).
                        as("Fixtures overview page not contain '%s' value: %s.", "opponent_organisation", value).isTrue();

                key = "opponent_team";
                value = item.get(key);
                result = fixturesPage.getAwayTeamNameText(firstRecord).equalsIgnoreCase(value);
                softly.assertThat(result).
                        as("Fixtures overview page not contain '%s' value: %s.", "opponent_team", value).isTrue();
            }
        }
    }

    @Then("^I should see the following details for the \"([^\"]*)\":$")
    public void i_should_see_the_following_details_for_the(String arg1, List<Map<String, String>> arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // List<YourType>, List<List<E>>, List<Map<K,V>> or Map<K,V>.
        // E,K,V must be a scalar (String, Integer, Date, enum etc)
        for (Map<String, String> item : arg2) {
            try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {

                String key = "competition_name";
                String value = item.get(key);
                boolean result = overviewPage.getGradeText().equalsIgnoreCase(value);
                softly.assertThat(result).
                        as("Fixtures overview page not contain '%s' value: %s.", "competition_name", value).isTrue();

                key = "round";
                value = item.get(key);
                if (World.isAndroid)
                    result = overviewPage.getCurrentPageName().equalsIgnoreCase(value);
                else
                    result = overviewPage.getCurrentPageName(value).equalsIgnoreCase(value);
                softly.assertThat(result).
                        as("Fixtures overview page not contain '%s' value: %s.", "round", value).isTrue();

                key = "match_date";
                value = item.get(key);
                result = overviewPage.getDateText().equalsIgnoreCase(value);
                softly.assertThat(result).
                        as("Fixtures overview page not contain '%s' value: %s.", "", value).isTrue();

                key = "kickoff_time";
                value = item.get(key);
                result = overviewPage.getClockText().contains(value);
                softly.assertThat(result).
                        as("Fixtures overview page not contain '%s' value: %s.", "kickoff_time", value).isTrue();

                key = "my_organisation";
                value = item.get(key);
                result = overviewPage.getHomeTeamNameText().equalsIgnoreCase(value);
                softly.assertThat(result).
                        as("Fixtures overview page not contain '%s' value: %s.", "my_organisation", value).isTrue();

                key = "my_team";
                value = item.get(key);
                result = overviewPage.getHomeTeamNameText().equalsIgnoreCase(value);
                softly.assertThat(result).
                        as("Fixtures overview page not contain '%s' value: %s.", "my_team", value).isTrue();

                key = "opponent_organisation";
                value = item.get(key);
                result = overviewPage.getAwayTeamNameText().equalsIgnoreCase(value);
                softly.assertThat(result).
                        as("Fixtures overview page not contain '%s' value: %s.", "opponent_organisation", value).isTrue();

                key = "opponent_team";
                value = item.get(key);
                result = overviewPage.getAwayTeamNameText().equalsIgnoreCase(value);
                softly.assertThat(result).
                        as("Fixtures overview page not contain '%s' value: %s.", "opponent_team", value).isTrue();

                key = "ground";
                value = item.get(key);
                result = overviewPage.getStreetText().equalsIgnoreCase(value);
                softly.assertThat(result).
                        as("Fixtures overview page not contain '%s' value: %s.", "ground", value).isTrue();

                key = "field";
                value = item.get(key);
                result = overviewPage.getSuburbText().equalsIgnoreCase(value);
                softly.assertThat(result).
                        as("Fixtures overview page not contain '%s' value: %s.", "field", value).isTrue();
            }
        }
    }

    @Then("^I should see a message \"([^\"]*)\"$")
    public void i_should_see_a_message(String message) throws Throwable {
        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {
            WebElement msg = null;
            if (World.isAndroid)
                msg = new FixturesPage().getTextViewWithText(message);
            else {
                Wait.waitFor(()->new FixturesPage().getStaticTextWithText(message) != null, veryShortTimeOut);
                msg = new FixturesPage().getStaticTextWithText(message);
            }
            softly.assertThat(msg != null).
                    as("Should be message: %s. But was: %s", message, msg.getText()).isTrue();
        }
    }

}
