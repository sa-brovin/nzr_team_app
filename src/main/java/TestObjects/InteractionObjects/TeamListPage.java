package TestObjects.InteractionObjects;

import Utils.AutotestException;
import Utils.LocatorStorage;
import org.openqa.selenium.By;

public class TeamListPage extends BaseListPage {
    public String pageName = "Select a team";

    private By selectBtnLocator = new LocatorStorage(
            By.id("SelectTeam"))
            .getLocator();

    private By itemNameLocator = new LocatorStorage(
            By.xpath(""),
            By.id("SelectTeam"))
            .getLocator();

    public TeamListPage() {
        setPageName(this.pageName);
        setItemNameLocator(this.itemNameLocator);
        setSelectBtnLocator(this.selectBtnLocator);
    }

    public void selectTeam(String itemName) throws AutotestException {
        super.selectItem(itemName);
    }
}
