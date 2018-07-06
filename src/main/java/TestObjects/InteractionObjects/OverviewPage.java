package TestObjects.InteractionObjects;

import TestObjects.Helpers.MobileActionsHelper;
import TestObjects.World;
import Utils.LocatorStorage;
import Utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static Utils.TestSettings.shortTimeOut;

public class OverviewPage extends AbstractPage {

    public static class Locators {
        protected static By grade = new LocatorStorage(
                By.id("header"),
                By.id("grade"))
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

        protected static By date = new LocatorStorage(
                By.id("date"),
                By.id("date"))
                .getLocator();

        protected static By clock = new LocatorStorage(
                By.id("clock"),
                By.id("clock"))
                .getLocator();

        protected static By suburb = new LocatorStorage(
                By.id("address"),
                By.id("suburb"))
                .getLocator();

        protected static By street = new LocatorStorage(
                By.id("address"),
                By.id("street"))
                .getLocator();
    }

    public String getGradeText() {
        if (World.isAndroid)
            return MobileActionsHelper.s(Locators.grade, shortTimeOut).getText();
        else {
            WebElement header = MobileActionsHelper.s(Locators.grade, shortTimeOut);
            return getAllStaticText(header).get(0).getText();
        }
    }

    public String getStatusText() {
        return MobileActionsHelper.s(Locators.status, shortTimeOut).getText();
    }

    public String getHomeTeamNameText() {
        if (World.isAndroid)
            return Utils.getLast(MobileActionsHelper.ss(Locators.homeTeamName, shortTimeOut)).getText();
        else {
            WebElement homeTeamName = MobileActionsHelper.s(Locators.homeTeamName, shortTimeOut);
            return getAllStaticText(homeTeamName).get(0).getText();
        }

    }

    public String getAwayTeamNameText() {
        if (World.isAndroid)
            return Utils.getLast(MobileActionsHelper.ss(Locators.awayTeamName, shortTimeOut)).getText();
        else {
            WebElement awayTeamName = MobileActionsHelper.s(Locators.awayTeamName, shortTimeOut);
            return getAllStaticText(awayTeamName).get(0).getText();
        }
    }

    public String getDateText() {
        if (World.isAndroid)
            return Utils.getLast(MobileActionsHelper.ss(Locators.date, shortTimeOut)).getText();
        else {
            WebElement date = MobileActionsHelper.s(Locators.date, shortTimeOut);
            return getAllStaticText(date).get(1).getText();
        }
    }

    public String getClockText() {
        if (World.isAndroid)
            return Utils.getLast(MobileActionsHelper.ss(Locators.clock, shortTimeOut)).getText();
        else {
            WebElement clock = MobileActionsHelper.s(Locators.clock, shortTimeOut);
            return getAllStaticText(clock).get(1).getText();
        }
    }

    public String getSuburbText() {
        if (World.isAndroid)
            return MobileActionsHelper.s(Locators.suburb, shortTimeOut).getText();
        else {
            WebElement suburb = MobileActionsHelper.s(Locators.suburb, shortTimeOut);
            return getAllStaticText(suburb).get(0).getText();
        }
    }

    public String getStreetText() {
        if (World.isAndroid)
            return MobileActionsHelper.s(Locators.street, shortTimeOut).getText();
        else {
            WebElement street = MobileActionsHelper.s(Locators.street, shortTimeOut);
            return getAllStaticText(street).get(1).getText();
        }
    }
}
