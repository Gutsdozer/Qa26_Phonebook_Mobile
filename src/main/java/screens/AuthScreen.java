package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;

public class AuthScreen extends BaseScreen{
    public AuthScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "")
    AndroidElement emailEditText;

    @FindBy(xpath = "")
    AndroidElement passwordEditText;

    @FindBy(xpath = "")
    AndroidElement loginBtn;

    public AuthScreen fillMail(String email){
        type(emailEditText, email);
        return this;
    }

    public AuthScreen fillPassword(String password){
        type(passwordEditText, password);
        return this;
    }

    public ContactListScreen submitLogin(){
        loginBtn.click();
        return new ContactListScreen(driver);
    }

}
