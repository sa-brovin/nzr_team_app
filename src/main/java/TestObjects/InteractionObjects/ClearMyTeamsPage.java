package TestObjects.InteractionObjects;

import TestObjects.World;
import Utils.AutotestException;
import TestObjects.Helpers.*;
import Utils.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static Utils.TestSettings.*;

public class ClearMyTeamsPage extends AbstractPage {
    public String pageName = "Clear My Teams";

    public void removeAllMyTeams() throws AutotestException {
        if (!this.isPageOpen()) {
            //  boolean result = Wait.waitFor(() -> this.isPageOpen(), shortTimeOut);
            //  if (!result)
            throw new AutotestException(pageName + " is not open.");
        }
        if (World.isAndroid)
            getTextViewWithText("REMOVE ALL MY TEAMS").click();
        else {
            WebElement button = Utils.getLast(MobileActionsHelper.ss(By.name("Remove All My Teams"), veryShortTimeOut));
            MobileActionsHelper.tapAtElement(button);
        }
    }
}


