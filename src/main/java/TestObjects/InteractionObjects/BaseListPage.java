package TestObjects.InteractionObjects;

import Utils.AutotestException;
import Utils.LocatorStorage;
import Utils.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static TestObjects.Helpers.MobileActionsHelper.isAppRunning;
import static TestObjects.Helpers.MobileActionsHelper.tapAtElement;
import static TestObjects.World.isAndroid;
import static TestObjects.World.testSession;
import static Utils.TestSettings.shortTimeOut;

public class BaseListPage extends AbstractPage {
    private String pageName = "";
    private By selectBtnLocator = new LocatorStorage(
            By.xpath(""),
            By.id(""))
            .getLocator();

    private By itemNameLocator = new LocatorStorage(
            By.xpath(""),
            By.id(""))
            .getLocator();

    //region Get & Set
    public By getItemNameLocator() {
        return itemNameLocator;
    }

    public void setItemNameLocator(By itemNameLocatorVal) {
        itemNameLocator = itemNameLocatorVal;
    }

    public By getSelectBtnLocator() {
        return selectBtnLocator;
    }

    public void setSelectBtnLocator(By selectBtnLocatorVal) {
        selectBtnLocator = selectBtnLocatorVal;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageNameVal) {
        this.pageName = pageNameVal;
    }

    public String pageName() {
        return this.getPageName();
    }
    //endregion

    public List<WebElement> getListItems() {
        return testSession.findElements(getSelectBtnLocator());
    }

    private String getItemName(WebElement itemName) {
        if (isAndroid)
          return getAllTextView(itemName).get(0).getText();
                  //itemName.findElement(getItemNameLocator()).getText();
        else
            // TODO: Magic constant [1]
            return getAllTextView(itemName).get(1).getText();
    }

    private WebElement getItem(String itemName) {
        Wait.waitFor(() -> getListItems().size() > 0, shortTimeOut);
        for (WebElement item : getListItems())
            if (getItemName(item).contains(itemName))
        //.equalsIgnoreCase(itemName))
                return item;
        return null;
    }

    protected void selectItem(String itemName) throws AutotestException {
        WebElement item = getItem(itemName);
        if (item == null)
            throw new AutotestException("Item with text: " + itemName + "not founded.");
       item.click();
        // tapAtElement(item);
    }
}
