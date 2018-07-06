package TestObjects.StepDefinitions;

import TestObjects.Helpers.MobileActionsHelper;
import TestObjects.World;
import Utils.AutotestException;
import Utils.ResultsStorage;
import Utils.SlackReporter;
import Utils.Wait;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.ServerArgument;
import org.omg.CORBA.TRANSACTION_UNAVAILABLE;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import static TestObjects.World.isAndroid;
import static TestObjects.World.testSession;
import static Utils.TestSettings.*;

public class Hooks {
	
    @Before("@test")
    public void setUpAndroidCapabilities() throws AutotestException {
    	switch (testEnv) {
    	case "Local":
    	{
  
    		if (World.service != null)
            return;
    	}
    	break;
    	case "SauceLabs":
    	{         
    		if (World.isStarted)
            return;
    	}
    	break;
    	default:
    		break;
    	}
    	

         World.isStarted = true;
        try {
            switch (String.valueOf(System.getProperty("current.os"))) {
                case "ios":
                    isAndroid = false;
                    break;
                case "android":
                    isAndroid = true;
                    break;
                default:
                    if (Utils.Utils.isMac())
                        isAndroid = false;
                    else isAndroid = true;
                    break;
            }

            isAndroid = false;

            // Appium service For local testing
          	if (testEnv == "Local")
          	{ AppiumServiceBuilder appBuilder = new AppiumServiceBuilder();
            appBuilder.usingAnyFreePort();
            World.service = AppiumDriverLocalService.buildService(appBuilder);
            World.service.start();
            
           
            if (World.service == null || !World.service.isRunning()) {
                throw new AppiumServerHasNotBeenStartedLocallyException(
                        "An appium server node is not started!");
            }
          	}
            //

            if (isAndroid)
                appPackage = appPackageAndroid;
            else
                appPackage = appPackageMac;

            DesiredCapabilities capabilities = new DesiredCapabilities();
       //     DesiredCapabilities capsWeb = new DesiredCapabilities().chrome();
    //        WebDriver driver = new RemoteWebDriver(new URL(WebURL), capsWeb);
            
            if (!isAndroid) {
                // Common caps
                capabilities.setCapability("noReset", true);
                capabilities.setCapability("fullReset", false);
                capabilities.setCapability("newCommandTimeout", "300");
                capabilities.setCapability("appWaitDuration", "600");

                // Local
                switch (testEnv) {
                case "Local":
              	{
                capabilities.setCapability("platformName", "iOS");
                capabilities.setCapability("platformVersion", "11.2");
                capabilities.setCapability("deviceName", "iPhone 7 Plus");
                //capabilities.setCapability("app", "/Users/emily/AppPackage/NZ_Rugby.app");
                capabilities.setCapability("app", "/Users/emily/AppPackage/nz-rugby-team-app.app");
                
               testSession = new IOSDriver<>(World.service.getUrl(), capabilities);
              	}
              	break;
              	
              	//Local Web
                case "Localbak":
                {
                capabilities.setCapability("platformName", "iOS");
                capabilities.setCapability("platformVersion", "11.2");
                capabilities.setCapability("deviceName", "iPhone 6");
                capabilities.setCapability("browserName", "Safari");
               // testSession = new IOSDriver<>(World.service.getUrl(), capabilities);
               // testSession = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
                //testSession = new AppiumDriver<>(new URL("https://www.guerrillamail.com/inbox"), capabilities);
                testSession = new IOSDriver<>(World.service.getUrl(), capabilities);
                AppiumDriver driver = new AppiumDriver(World.service.getUrl(), capabilities);
                }
                break;


                // SauceLabs
                case "SauceLabs": 
                {capabilities.setCapability("appiumVersion", "1.7.2");
                capabilities.setCapability("recordVideo", "false");
                capabilities.setCapability("deviceName","iPhone 6 Simulator");
                capabilities.setCapability("deviceOrientation", "portrait");
                capabilities.setCapability("platformVersion","11.1");
                capabilities.setCapability("platformName", "iOS");
                capabilities.setCapability("browserName", "");
                capabilities.setCapability("app","sauce-storage:nz-rugby.zip");
                //capabilities.setCapability("app",sauceLabIOSBuildPath);
                testSession = new IOSDriver(new URL(sauceLabURL), capabilities);
                }
                 break;
                
                // BrowserStack
                case "BrowserStack":
                capabilities.setCapability("device", "iPhone 8");
                capabilities.setCapability("os_version", "11.0");
                capabilities.setCapability("app", "bs://3f76eedc1cbd2f3ebcbd0a3d431d0657d023e1d8");
                capabilities.setCapability("automationName", "Appium");
                testSession = new IOSDriver(new URL(browserStackURL), capabilities);
                break;
                default:
                	break;
                }
                
            } else {
                // Common caps
                capabilities.setCapability("noSign", "true");
                capabilities.setCapability("disableAndroidWatchers", "true");
                capabilities.setCapability("noReset", true);
                capabilities.setCapability("fullReset", false);
                capabilities.setCapability("newCommandTimeout", "300");
                capabilities.setCapability("androidInstallTimeout", "240000");
                capabilities.setCapability("appWaitDuration", "60000");

                switch (testEnv) {
                case "Local":
              	{
                // Local
//                capabilities.setCapability("platformName", "Android");
//                capabilities.setCapability("avd", "Nexus_5X_API_27_x86");
//                //capabilities.setCapability("deviceName", androidDeviceName);
//                capabilities.setCapability("deviceName", "Android Emulator");
//                capabilities.setCapability("appPackage", appPackage);
//                capabilities.setCapability("appActivity", appActivity);
//                //capabilities.setCapability("app", apkPath);
//                //capabilities.setCapability("app", "/Users/abrovin/Downloads/NZ_Rugby.apk");
//                capabilities.setCapability("app", "/Users/emily/AppPackage/NZ_Rugby.apk");
                
                
                capabilities.setCapability("platformName", "Android");
                capabilities.setCapability("deviceName", "Galaxy S3");
                capabilities.setCapability("appPackage", appPackage);
                capabilities.setCapability("appActivity", appActivity);
                capabilities.setCapability("app", "/Users/emily/AppPackage/NZ_Rugby.apk");
                
               
                testSession = new AndroidDriver<>(World.service.getUrl(), capabilities);
              	}
              	break;
              	
                //public static final String androidDeviceName = "4203a3b6d0cb8100";
           
              	
              	
              	
              	
                case "SauceLabs":
                {
                	// SauceLabs
                
                capabilities.setCapability("appiumVersion", "1.6.4");
                capabilities.setCapability("deviceName","Samsung Galaxy S4 Emulator");
                capabilities.setCapability("deviceOrientation", "portrait");
                capabilities.setCapability("browserName", "");
                capabilities.setCapability("platformVersion","4.4");
                capabilities.setCapability("platformName","Android");
                capabilities.setCapability("app",sauceLabAndroidBuildPath);
                //capabilities.setCapability("app","sauce-storage:NZ_Rugby.apk");
                
                testSession = new AndroidDriver<>(new URL(sauceLabURL), capabilities);
                }
                break;
                
                
                case "BrowserStack":
                {
                capabilities.setCapability("device", "Google Pixel");
                capabilities.setCapability("os_version", "7.1");
                //capabilities.setCapability("app", "bs://3f76eedc1cbd2f3ebcbd0a3d431d0657d023e1d8");
                //capabilities.setCapability("app", "bs://2ed7b7ba76fe431e284dc9b73e235f0a59e7c2e2");
                capabilities.setCapability("app", sauceLabAndroidBuildPath);
                capabilities.setCapability("automationName", "Appium");
                testSession = new AndroidDriver(new URL(browserStackURL), capabilities);
                }
                break;
                default:
                	break;
                }
            }
            Wait.waitFor(() -> MobileActionsHelper.isAppRunning(), longTimeOut);
            testSession.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } 
            
         catch (Exception e) {
            e.printStackTrace();
            if (testSession != null) {
                testSession.quit();
            }
            /*if (World.service != null) {
                World.service.stop();
            }*/
            throw new AutotestException("Can not run appium server.");
        } finally {
        }
    }

    @Before("@test")
    public void runApp() {
        // HACK: Run thread when ALL tests finishing.
        if (String.valueOf(System.getProperty("current.isMaven")).equalsIgnoreCase("true"))
            if (!World.isHookTriggered) {
                World.isHookTriggered = true;
                Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                    SlackReporter.sendMessage();
                }));
            }

        MobileActionsHelper.runApp();
    }

    @After("@test")
    public void closeApp() {
        MobileActionsHelper.closeApp();
    }

    @After("@test")
    public void addTestResultsToStorageAfterTestEnd(Scenario scenario) {
        ResultsStorage.addResult(scenario.getId().split(";")[0], scenario.getName(), scenario.getStatus());

        for (byte[] screen : ResultsStorage.screenShotsStorage) {
            scenario.embed(screen, "image/png");
        }

        ResultsStorage.screenShotsStorage.clear();
    }
}
