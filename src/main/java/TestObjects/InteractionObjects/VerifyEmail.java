package TestObjects.InteractionObjects;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;
import TestObjects.InteractionObjects.AbstractWebPage;
import Utils.Wait;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.Set;

import static Utils.TestSettings.timeOut;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

import static TestObjects.World.testSession;
import static TestObjects.World.driver;


public class VerifyEmail {   
       // testSession.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    public void openPage()
    {
    driver.get("https://www.guerrillamail.com/inbox");
    }
	
        //region Elements  
//private WebElement inboxBtn = driver.findElement(By.cssSelector("#inbox-id.button")); //By.id("forget_button")); //
//	private WebElement inboxField = testSession.findElement(By.cssSelector("#inbox-id input")); 
//	private WebElement emailBody = testSession.findElement(By.cssSelector(".email_body")); 

//        
 //    private SelenideElement inboxBtn = $(By.cssSelector("#inbox-id.button"));
//        private SelenideElement inboxField = $(By.cssSelector("#inbox-id input"));
//        private SelenideElement emailBody = $(By.cssSelector(".email_body"));
//        private By emailsLocator = By.cssSelector(".mail_row");
//        
      //endregion

 
        
        public void openInbox(String inboxName) {
 //           inboxBtn.click();
//            inboxField.sendKeys(inboxName);
//            inboxField.sendKeys(Keys.RETURN);
//            Wait.waitFor(() -> $$(emailsLocator).size() > 1, timeOut);
        }

//        public void selectEmail(String subject) {
//        	SelenideElement email = $$(emailsLocator).stream().filter((e) -> e.getText().contains(subject)).findFirst().orElse(null);
//        	WebElement email = emailsLocator.
//            email.click();
//        }
//
//        public String getEmailText() {
//            return emailBody.getText();
//        }
//
//        public void clickLink(String linkLabel) {
//       // 	WebElement link = emailBody.$(By.linkText(linkLabel));
//        	WebElement link = emailBody.findElement(By.linkText(linkLabel));
//            link.click();
//        }

}

