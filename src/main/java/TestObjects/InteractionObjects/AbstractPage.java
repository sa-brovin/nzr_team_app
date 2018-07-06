package TestObjects.InteractionObjects;

import TestObjects.Helpers.MobileActionsHelper;
import Utils.LocatorStorage;
import Utils.Wait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static TestObjects.Helpers.MobileActionsHelper.s;
import static TestObjects.World.isAndroid;
import static TestObjects.World.testSession;
import static Utils.TestSettings.shortTimeOut;
import static Utils.TestSettings.timeOut;
import static Utils.TestSettings.veryShortTimeOut;
import static Utils.Utils.normalizeString;

public abstract class AbstractPage {
    public String pageName() {
        return "";
    }

    public static class Locators {
    		protected static By captionSkip = new LocatorStorage(
                By.xpath("(//XCUIElementTypeOther[@name=\"Skip Welcome Screen\"]"),
                //By.xpath("/hierarchy/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.view.View/android.widget.TextView[1]"))
                By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.TextView"))
                .getLocator();
    	
        protected static By captionXpatch = new LocatorStorage(
                By.xpath("(//XCUIElementTypeOther[@name=\"FIND A CLUB OR SCHOOL\"])[2]"),
                //By.xpath("/hierarchy/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.view.View/android.widget.TextView[1]"))
                By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.TextView"))
                .getLocator();

        protected static By backButtonXpatch = new LocatorStorage(
                //By.id("backButton")
                By.xpath("//XCUIElementTypeStaticText[@name=\"\uF10C\"]"),
                By.id("backButton"))
                .getLocator();

        protected static By textView = new LocatorStorage(
                By.className("XCUIElementTypeOther"),//"XCUIElementTypeStaticText"),
                By.className("android.widget.TextView"))
                .getLocator();

        protected static By editText = new LocatorStorage(
                By.xpath(""),
                By.className("android.widget.EditText"))
                .getLocator();

        protected static By ioStaticText = new LocatorStorage(
                By.className("XCUIElementTypeStaticText"),
                By.className(""))
                .getLocator();
    }

    private List<WebElement> allEditTexts = new ArrayList<>();
    private List<WebElement> allTextView = new ArrayList<>();
    private List<WebElement> allStaticText = new ArrayList<>();

    //region Getters

    public List<WebElement> getAllEditText() {
        allEditTexts = testSession.findElements(Locators.editText);
        return allEditTexts;
    }

    public WebElement getEditTextWithText(String text) {
        if (allEditTexts.isEmpty())
            getAllEditText();
        return allEditTexts.stream().filter((e) -> normalizeString(e.getText()).contains(normalizeString(text))).findFirst().orElse(null);
    }

    ////
    public List<WebElement> getAllStaticText() {
        allStaticText = testSession.findElements(Locators.ioStaticText);
        return allStaticText;
    }

    public WebElement getStaticTextWithText(String text) {
        if (allStaticText.isEmpty())
            getAllStaticText();
        return allStaticText.stream().filter((e) -> normalizeString(e.getText()).contains(normalizeString(text))).findFirst().orElse(null);
    }

    public List<WebElement> getAllStaticText(WebElement parent) {
        return parent.findElements(Locators.ioStaticText);
    }

    public WebElement getStaticTextWithText(WebElement parent, String text) {
        getAllStaticText(parent);
        return allStaticText.stream().filter((e) -> normalizeString(e.getText()).contains(normalizeString(text))).findFirst().orElse(null);
    }

    ////
    public List<WebElement> getAllTextView() {
        allTextView = testSession.findElements(Locators.textView);
        return allTextView;
    }

    public List<WebElement> getAllTextView(WebElement parent) {
        return parent.findElements(Locators.textView);
    }

    public WebElement getTextViewWithText(String text) {
        if (allTextView.isEmpty())
            getAllTextView();
        return allTextView.stream().filter((e) -> normalizeString(e.getText()).equalsIgnoreCase(normalizeString(text))).findFirst().orElse(null);
    }

    public WebElement getTextViewWithText(WebElement parent, String text) {
        List<WebElement> textViews = getAllTextView(parent);
       // return textViews.stream().filter((e) -> normalizeString(e.getText()).contains(normalizeString(text))).findFirst().orElse(null);
        return textViews.stream().filter((e) -> normalizeString(e.getText()).equalsIgnoreCase(normalizeString(text))).findFirst().orElse(null);
    }

    //endregion

    public String getCurrentPageName() {
        String text = getAllTextView().get(0).getText();// s(Locators.captionXpatch, timeOut).getText();
        //TODO: dirty hack.
        if (text.length() > 1)
            return text;
        else
            return getAllTextView().get(1).getText();
         //s(By.xpath(Locators.captionXpatch.toString().replace('1', '2').split(" ")[1]), shortTimeOut).getText();
    }

    public String getCurrentPageName(String expectedName) {
        WebElement title = s(By.xpath("(//XCUIElementTypeOther[@name=\""+expectedName.toUpperCase()+"\"])[2]"), timeOut);
        if (title != null)
            return title.getText();
        return "";
    }

    public String getCurrentPageName(String expectedName, int waitTime) {
        WebElement title = s(By.xpath("(//XCUIElementTypeOther[@name=\""+expectedName.toUpperCase()+"\"])[2]"), waitTime);
        if (title != null)
            return title.getText();
        return "";
    }

    private WebElement getBackButton() {
        return s(Locators.backButtonXpatch, shortTimeOut);
    }

    public void back() {
        WebElement backButton = getBackButton();
        MobileActionsHelper.tapAtElement(backButton);
    }

    public boolean isPageOpen() {
        if (isAndroid) {
            if (!Wait.waitFor(() -> normalizeString(getCurrentPageName()).contains(normalizeString(this.pageName())), veryShortTimeOut))
                return false;
            return true;
        }
        else {
            if (!normalizeString(getCurrentPageName(this.pageName(), veryShortTimeOut)).contains(normalizeString(this.pageName())))
                return false;
            return true;
        }
    }

    public boolean isTextExistOnPage(String text) {
        return getTextViewWithText(text) != null;
    }
}
