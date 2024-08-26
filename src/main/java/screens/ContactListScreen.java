package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.support.FindBy;

import static java.awt.SystemColor.text;

public class ContactListScreen extends BaseScreen{
    public ContactListScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "")
    AndroidElement activityTextView;

    public boolean isContactListDisplayed(String text){

        // return activityTextView.getText().contains("Contact list");
        return isShouldHave(activityTextView, text, 8);
    }
}
