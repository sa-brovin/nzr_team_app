package TestObjects.InteractionObjects;

import TestObjects.Helpers.MobileActionsHelper;
import TestObjects.InteractionObjects.LoginPage.Locators;
import TestObjects.World;
import Utils.LocatorStorage;
import Utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static TestObjects.Helpers.MobileActionsHelper.s;
import static Utils.TestSettings.shortTimeOut;
import static Utils.TestSettings.veryShortTimeOut;
import static TestObjects.World.testSession;

public class MyTeamsPage extends FixturesPage {
    public String pageName() {
        return "My Teams";
    }

    private WebElement currentTeam = null;
    private String currentTeamTitle = "";


    protected static class Locators {
        protected static By teamItem = new LocatorStorage(
                By.id("Team"),
                By.id("Team"))
                .getLocator();
        protected static By teamName = new LocatorStorage(
                By.id("Name1"),
                By.id("TeamContainer"))
                .getLocator();
        protected static By teamTitle = new LocatorStorage(
                By.id("title"),
                By.id("title"))
                .getLocator();
        protected static By teamOrganisation = new LocatorStorage(
                By.id("Organisation"),
                By.id("grade"))
                .getLocator();
        protected static By teamLastGameDate = new LocatorStorage(
                By.id("nextGame"),
                By.id("nextGame"))
                .getLocator();
        protected static By teamLogo = new LocatorStorage(
                By.className("XCUIElementTypeImage"),
                By.className("android.widget.ImageView"))
                .getLocator();
    }

    public List<WebElement> getAllTeams() {
        //return MobileActionsHelper.ss(Locators.teamItem, shortTimeOut);
    		return MobileActionsHelper.ss(Locators.teamTitle, shortTimeOut);
    }

//    private WebElement getteamTitle() {
//        return s(Locators.emailInput, veryShortTimeOut);
//    }
    public WebElement getTeamTest(String teamTitle) {
    return testSession.findElement(By.id(teamTitle));
    }
    
    public WebElement getClubTest(String club) {
        return testSession.findElement(By.id(club));
        }
    
    public WebElement getTeamGradeTest(String teamGrade) {
    	return testSession.findElement(By.id(teamGrade));
    }
    
    public WebElement getTeamDateTest(String teamDate) {
    	return testSession.findElement(By.id(teamDate));
    }
    
    public WebElement getTeamByTitle(String teamTitle) {
    	
        if (currentTeamTitle != teamTitle || currentTeam == null) {
            currentTeamTitle = teamTitle;
            List<WebElement> allTeams = getAllTeams();
            if (World.isAndroid) {
                currentTeam = allTeams.stream().filter((t) -> Utils.getLast(t.findElements(Locators.teamTitle)).getText().equalsIgnoreCase(teamTitle)).findFirst().orElse(null);
                        //allTeams.stream().filter((t) -> t.findElement(Locators.teamTitle).getText().equalsIgnoreCase(teamTitle)).findFirst().orElse(null);
                return currentTeam;
            } else {
                currentTeam = allTeams.stream().filter((t) -> getAllStaticText(t.findElement(Locators.teamTitle)).get(0).getText().equalsIgnoreCase(teamTitle)).findFirst().orElse(null);
           // 	currentTeam=allTeams.stream().filter((t)->t.getText().equalsIgnoreCase(teamTitle)).findFirst().orElse(null); 
//            	java.util.Iterator<WebElement> i = allTeams.iterator();
//            	while(i.hasNext()) {
//            	    WebElement row = i.next();
//            	    System.out.println(row.getText());

                return currentTeam;
            }
        }
        return currentTeam;
    }

    public boolean isLogoExist(String teamTitle) {
        WebElement team = getTeamByTitle(teamTitle);
        return MobileActionsHelper.s(team, Locators.teamLogo, shortTimeOut) != null;
    }

    public String getTeamName(String teamTitle) {
        WebElement team = getTeamByTitle(teamTitle);
        
        
        if (World.isAndroid)
            return getAllTextView(team.findElement(Locators.teamName)).get(0).getText();
        else
            return //getAllStaticText(team).get(0).getText(); 
            		getAllStaticText(team.findElement(Locators.teamName)).get(0).getText();
    }

    public String getTeamTitle(String teamTitle) {
        WebElement team = getTeamByTitle(teamTitle);
        if (World.isAndroid)
            return Utils.getLast(team.findElements(Locators.teamTitle)).getText();
        else
            return getAllStaticText(team.findElement(Locators.teamTitle)).get(0).getText();
    }

    public String getTeamGrade(String teamTitle) {
        WebElement team = getTeamByTitle(teamTitle);
        if (World.isAndroid)
          return team.findElement(Locators.teamOrganisation).getText();
        else
          return  getAllStaticText(team.findElement(Locators.teamTitle)).get(1).getText();
    }

    public String getTeamLastDate(String teamTitle) {
        WebElement team = getTeamByTitle(teamTitle);
        if (World.isAndroid)
            return getAllTextView(team.findElement(Locators.teamLastGameDate)).get(1).getText();
        else
            return getAllStaticText(team.findElement(Locators.teamLastGameDate)).get(1).getText();
    }
}
