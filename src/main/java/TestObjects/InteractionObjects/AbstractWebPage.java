package TestObjects.InteractionObjects;

import Utils.Wait;
import com.codeborne.selenide.*;
import org.openqa.selenium.By;

import static Utils.TestSettings.*;
import static Utils.TestSettings.veryShortTimeOut;
import static com.codeborne.selenide.Selenide.$;

public class AbstractWebPage extends SelenidePageFactory {
    public AbstractWebPage() {
        // Use only for local debug. When you push to repository, this lines should be commented.
        //System.setProperty("webdriver.chrome.driver", "D:\\Projects\\bitbucket\\nzr_tv_ui_tests\\Drivers\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", PathChromeDriver);
        Configuration.browser = "chrome";
        Configuration.timeout = 6000;
    }

    public String url() {
        return siteAddress;
    }

    public AbstractWebPage openPage() {
        if (!Wait.waitFor(() -> this.isOpenURLEquals(), veryShortTimeOut))
            Selenide.open(url());
        else
            Selenide.refresh();
        return this;
    }

    public void refresh() {
        Selenide.refresh();
    }

    public boolean isOpen() {
        if (!WebDriverRunner.hasWebDriverStarted() )
            return false;
        if (!WebDriverRunner.url().contains(this.url()))
            return false;
        return true;
    }

    public boolean isOpenURLEquals() {
        if (!WebDriverRunner.hasWebDriverStarted() )
            return false;
        if (!WebDriverRunner.url().equalsIgnoreCase(this.url()))
            return false;
        return true;
    }

    public SelenideElement get(By by, int timeout) {
        try {
            return $(by).waitUntil(Condition.appear, timeout);
        } catch (Throwable t) {
            return null;
        }
    }
}


