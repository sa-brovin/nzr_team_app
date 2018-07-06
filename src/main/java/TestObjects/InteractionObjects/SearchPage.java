package TestObjects.InteractionObjects;

import TestObjects.Helpers.MobileActionsHelper;
import Utils.AutotestException;
import Utils.LocatorStorage;
import Utils.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static TestObjects.Helpers.MobileActionsHelper.s;
import static TestObjects.Helpers.MobileActionsHelper.tapAtElement;
import static TestObjects.World.isAndroid;
import static TestObjects.World.testSession;
import static Utils.TestSettings.shortTimeOut;

public class SearchPage extends AbstractPage {
    public static class Constants {
        public static String pageName = "Find a club or school";
        //"Find a team";
    }

    public static class Locators extends AbstractPage.Locators {
        private static By searchField = new LocatorStorage(
                //By.id("\uF139 SearchInput"),
                By.id("\uF139 Search for your club or school"),
                By.className("android.widget.EditText"))
                .getLocator();

        private static By searchResult = new LocatorStorage(
                By.id("SearchResult"))
                .getLocator();

        private static By errorMessage = new LocatorStorage(
                By.id("ErrorMessage"))
                .getLocator();
    }

    public String pageName() {
        return Constants.pageName;
    }

    private WebElement getSearchField() {
        if (!isAndroid)
            return Utils.Utils.getLast(MobileActionsHelper.ss(Locators.searchField, shortTimeOut));
        else
            return s(Locators.searchField, shortTimeOut);
    }

    private List<WebElement> getSearchResults() {
        return testSession.findElements(Locators.searchResult);
    }

    public void search(String searchText) {
        WebElement searchField = getSearchField();
        searchField.click();
        searchField.sendKeys(searchText);
    }

    private WebElement getRecord(String recordText) {
        Wait.waitFor(() -> getSearchResults().size() > 0, shortTimeOut);
        for (WebElement record : getSearchResults()) {
            WebElement recordTextElement = getTextViewWithText(record, recordText);
            if (recordTextElement != null)
                return recordTextElement;
        }
        return null;
    }

    public boolean searchResultsContainRecord(String recordText) {
        if (getRecord(recordText) != null)
            return true;
        return false;
    }

    public void selectRecord(String recordText) throws AutotestException {
        WebElement record = getRecord(recordText);
        if (record == null)
            throw new AutotestException("Record with text: " + recordText + "not founded.");
        record.click();
        //tapAtElement(record);
    }

    public boolean isErrorExist(String expectedError) {
        if (isAndroid)
          return getTextViewWithText(s(Locators.errorMessage, shortTimeOut), expectedError) != null;
        else
            return s(Locators.errorMessage, shortTimeOut) != null;
    }

    public void open() throws AutotestException, InterruptedException {
        MenuPage mp = new MenuPage();
        mp.open();
        mp.selectSearch();
    }
}
