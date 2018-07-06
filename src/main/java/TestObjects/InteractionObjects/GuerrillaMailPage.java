package TestObjects.InteractionObjects;

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

public class GuerrillaMailPage extends AbstractWebPage {
    public String url() {
        return "https://www.guerrillamail.com/inbox";
    }

    //region Elements

    private SelenideElement inboxBtn = $(By.cssSelector("#inbox-id.button"));
    private SelenideElement inboxField = $(By.cssSelector("#inbox-id input"));
    private SelenideElement emailBody = $(By.cssSelector(".email_body"));
    private By emailsLocator = By.cssSelector(".mail_row");

    //endregion

    public void openInbox(String inboxName) {
        inboxBtn.click();
        inboxField.sendKeys(inboxName);
        inboxField.sendKeys(Keys.RETURN);
        Wait.waitFor(() -> $$(emailsLocator).size() > 1, timeOut);
    }

    public void selectEmail(String subject) {
        SelenideElement email = $$(emailsLocator).stream().filter((e) -> e.getText().contains(subject)).findFirst().orElse(null);
        email.click();
    }

    public String getEmailText() {
        return emailBody.getText();
    }

    public void clickLink(String linkLabel) {
        SelenideElement link = emailBody.$(By.linkText(linkLabel));
        link.click();
    }

//    public String getVerificationText(String mainTabHandle) {
//        Set<String> handles = getWebDriver().getWindowHandles();
//        String newTab = handles.stream().filter((s) -> !s.equalsIgnoreCase(mainTabHandle)).findFirst().orElse("");
//        Selenide.switchTo().window(newTab);

//        SuccessRegistrationDialog dialog = new SuccessRegistrationDialog();
//        Wait.waitFor(() -> dialog.isOpen(), timeOut);
//        String text = dialog.getText();
//        dialog.clickContinue();

//        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {
//            softly.assertThat(new MainPage().isOpen()).
//                    as("Main page should be opened.").isTrue();
//        }
//        getWebDriver().switchTo().window(newTab).close();
//        getWebDriver().switchTo().window(mainTabHandle);
//        return text;
//    }
}