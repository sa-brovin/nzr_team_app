package TestObjects.InteractionObjects;

import TestObjects.Helpers.MobileActionsHelper;
import Utils.LocatorStorage;
import Utils.Utils;
import Utils.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static TestObjects.World.isAndroid;
import static TestObjects.World.testSession;
import static Utils.TestSettings.shortTimeOut;

public class FixturesPage extends AbstractPage {

    private enum FixtureSection {
        Upcoming,
        Played
    }

    public static class Locators {
        protected static By scrollView = new LocatorStorage(
                By.className("XCUIElementTypeScrollView"),
                By.className("android.widget.ScrollView"))
                .getLocator();

        protected static By scrollViewItem = new LocatorStorage(
                By.id("container"),
                //By.className("android.view.ViewGroup"))
                By.id("container"))
                .getLocator();

        protected static By round = new LocatorStorage(
                By.id("header"),
                By.id("round"))
                .getLocator();

        protected static By status = new LocatorStorage(
                By.id("header"),
                By.id("status"))
                .getLocator();

        protected static By homeTeamName = new LocatorStorage(
                By.id("homeTeamName"),
                By.id("homeTeamName"))
                .getLocator();

        protected static By awayTeamName = new LocatorStorage(
                By.id("awayTeamName"),
                By.id("awayTeamName"))
                .getLocator();

        protected static By dateAndLocation = new LocatorStorage(
                By.id("dateAndLocation"),
                By.id("dateAndLocation"))
                .getLocator();

        protected static By saveTeamButton = new LocatorStorage(
                By.id("SaveTeam"),
                By.id("SaveTeam"))
                .getLocator();
    }

    public String getRoundText(WebElement parent) {
        if (isAndroid)
            return MobileActionsHelper.s(parent, Locators.round, shortTimeOut).getText();
        else
        {
            WebElement round = MobileActionsHelper.s(parent, Locators.round, shortTimeOut);
            return getAllStaticText(round).get(0).getText();
        }
    }

    public String getHomeTeamNameText(WebElement parent) {
        if (isAndroid)
            return Utils.getLast(MobileActionsHelper.ss(parent, Locators.homeTeamName, shortTimeOut)).getText();
        else
        {
            WebElement el = MobileActionsHelper.s(parent, Locators.homeTeamName, shortTimeOut);
            return getAllStaticText(el).get(0).getText();
        }
    }

    public String getAwayTeamNameText(WebElement parent) {
        if (isAndroid)
            return Utils.getLast(MobileActionsHelper.ss(parent, Locators.awayTeamName, shortTimeOut)).getText();
        else
        {
            WebElement el = MobileActionsHelper.s(parent, Locators.awayTeamName, shortTimeOut);
            return getAllStaticText(el).get(0).getText();
        }
    }

    public String getStatusText(WebElement parent) {
        if (isAndroid)
            return MobileActionsHelper.s(parent, Locators.status, shortTimeOut).getText();
        else
        {
            WebElement status = MobileActionsHelper.s(parent, Locators.status, shortTimeOut);
            return getAllStaticText(status).get(1).getText();
        }
    }

    public String getDateText(WebElement parent) {
        if (isAndroid)
            return getAllTextView(MobileActionsHelper.s(parent, Locators.dateAndLocation, shortTimeOut)).get(0).getText().split(" at ")[0];
        else
        {
            WebElement el = MobileActionsHelper.s(parent, Locators.dateAndLocation, shortTimeOut);
            return getAllStaticText(el).get(0).getText().split(" at ")[0];
        }
    }

    public String getLocationText(WebElement parent) {
        if (isAndroid)
            return getAllTextView(MobileActionsHelper.s(parent, Locators.dateAndLocation, shortTimeOut)).get(0).getText().split(" at ")[1];
        else
        {
            WebElement el = MobileActionsHelper.s(parent, Locators.dateAndLocation, shortTimeOut);
            return getAllStaticText(el).get(0).getText().split(" at ")[1];
        }
    }

    public List<WebElement> getAllFixtures() {
        WebElement scrollView = Utils.getLast(testSession.findElements(Locators.scrollView));
        List<WebElement> items = scrollView.findElements(Locators.scrollViewItem);
        return items;
    }

    public void selectUpcoming() {
        if (isAndroid)
            getTextViewWithText(FixtureSection.Upcoming.toString()).click();
        else
            testSession.findElement(By.id(FixtureSection.Upcoming.toString().toUpperCase())).click();
    }

    public void selectPlayed() {
        if (isAndroid)
            getTextViewWithText(FixtureSection.Played.toString()).click();
        else
            testSession.findElement(By.id(FixtureSection.Played.toString().toUpperCase())).click();
    }

    public void selectFirstGame() {
        Wait.waitFor(() -> getAllFixtures().size() > 0, shortTimeOut);
        MobileActionsHelper.tapAtElement(getAllFixtures().get(0));
    }

    public void saveTeam() {
        WebElement btn = MobileActionsHelper.s(Locators.saveTeamButton, shortTimeOut);
        btn.click();
    }
}
