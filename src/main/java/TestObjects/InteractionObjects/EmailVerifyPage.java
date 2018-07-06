package TestObjects.InteractionObjects;
import TestObjects.InteractionObjects.AbstractWebPage;
import Utils.Wait;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public class EmailVerifyPage extends AbstractWebPage {
    public String url() {
    	//To do
        return "https://nzr-poc.auth0.com/lo/verify_email?ticket=k7zPFnPUeWumzKQxU7DXaXsbWGPPKs4v#";
    }
    
    

}
