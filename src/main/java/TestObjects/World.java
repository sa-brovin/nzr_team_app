package TestObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.WebElement;

public class World {
    public static AppiumDriver<WebElement> testSession = null;
    public static AppiumDriver driver=null;
    public static AppiumDriverLocalService service = null;
   //sauceLab
    public static boolean isStarted = false;
    public static boolean isHookTriggered = false;
    public static boolean isAndroid = false;
}
