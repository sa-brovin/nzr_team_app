package TestObjects.InteractionObjects;

import Utils.AutotestException;
import Utils.LocatorStorage;
import org.openqa.selenium.By;

public class GradeListPage extends BaseListPage {
    public String pageName = "Select a grade";

    private By selectBtnLocator = new LocatorStorage(
            By.id("SelectGrade"))
            .getLocator();

    private By itemNameLocator = new LocatorStorage(
            By.xpath(""),
            //By.id("gradeName"))
            By.id("SelectGrade"))
            .getLocator();

    public GradeListPage() {
        setPageName(this.pageName);
        setItemNameLocator(this.itemNameLocator);
        setSelectBtnLocator(this.selectBtnLocator);
    }

    public void selectGrade(String itemName) throws AutotestException {
        super.selectItem(itemName);
    }
}
