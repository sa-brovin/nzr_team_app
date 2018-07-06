package TestObjects.InteractionObjects;
import TestObjects.World;
import TestObjects.Helpers.MobileActionsHelper;
import TestObjects.InteractionObjects.OverviewPage.Locators;
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

public class PlayerPosition extends BaseListPage {
	   // public String pageName = "Select a grade";

	    private By selectBtnLocator = new LocatorStorage(
	            By.id("Assign"))
	            .getLocator();

	    private By itemNameLocator = new LocatorStorage(
	            By.xpath(""),
	            //By.id("gradeName"))
	            By.id("SelectGrade"))
	            .getLocator();

	    public PlayerPosition() {
	        //setPageName(this.pageName);
	        setItemNameLocator(this.itemNameLocator);
	        setSelectBtnLocator(this.selectBtnLocator);
	    }

	    public void assignPosition(String itemName) throws AutotestException {
	        super.selectItem(itemName);
	    }
}
