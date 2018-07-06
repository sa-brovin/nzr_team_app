package TestObjects.Helpers;

import Utils.Wait;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static TestObjects.World.testSession;
import static Utils.ResultsStorage.screenShotsStorage;
import static Utils.TestSettings.appPackage;
import static Utils.TestSettings.timeOut;

public class MobileActionsHelper {

    public static void getScr() {
        screenShotsStorage.add(testSession.getScreenshotAs(OutputType.BYTES));
    }

    public static void resetApp() throws InterruptedException {
        //    Wait.waitFor(()-> isAppRunning(), longTimeOut);
        testSession.resetApp();

        Wait.waitFor(() -> isAppRunning(), timeOut);
    }

    public static void runApp() {
        if (testSession != null && !isAppRunning())
            testSession.launchApp();
    }

    public static void closeApp() {
        if (testSession != null && isAppRunning())
            testSession.closeApp();
    }

    public static void slideToRight() {
        (new TouchAction(testSession))
                .press(10, 200)
                .moveTo(175, 200)
                .release().perform();
    }


    public static void SlideToLeft() {
        (new TouchAction(testSession))
                .press(175, 200)
                .moveTo(-160, 200)
                .release().perform();
    }
    
    public static void hideKeyboard() {
        try {
            testSession.hideKeyboard();
        } catch (Exception e) {
        }
    }

    public static void tapAtElement(WebElement element) {
//    	testSession.perform(TouchAction()).tap(element);
//        TouchAction tap = new TouchAction(testSession);
        (new TouchAction(testSession)).tap(element).perform();
//       tap.tap(element).perform();
//       if (exist(element))
//            tap.tap(element).perform();
    }

    public static boolean isAppRunning() {
        try {
            return testSession.getPageSource().contains(appPackage);
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public static boolean exist(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public static WebElement s(By selector, int timeInMs) {
        return s(null, selector, timeInMs);
    }

    public static WebElement s(WebElement parent, By selector, int timeInMs) {
        testSession.manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS);
        boolean result = Wait.waitFor(() -> {
                    try {
                        if (parent != null)
                            parent.findElement(selector);
                        else
                            testSession.findElement(selector);
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                }, timeInMs
        );
        testSession.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        if (result)
            if (parent != null)
                return parent.findElement(selector);
            else
                return testSession.findElement(selector);
        else
            return null;
    }

    public static List<WebElement> ss(By selector, int timeInMs) {
        return ss(null, selector, timeInMs);
    }

    public static List<WebElement> ss(WebElement parent, By selector, int timeInMs) {
        testSession.manage().timeouts().implicitlyWait(1, TimeUnit.MILLISECONDS);
        boolean result = Wait.waitFor(() -> {
                    try {
                        if (parent != null)
                            parent.findElements(selector);
                        else
                            testSession.findElements(selector);
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                }, timeInMs
        );
        testSession.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        if (result)
            if (parent != null)
                return parent.findElements(selector);
            else
                return testSession.findElements(selector);
        else
            return null;
    }
}
