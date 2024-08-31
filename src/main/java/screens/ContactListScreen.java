package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.awt.*;
import java.util.List;

import static java.awt.SystemColor.text;

public class ContactListScreen extends BaseScreen{
    public ContactListScreen(AppiumDriver<AndroidElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    AndroidElement activityTextView;

    @FindBy(id = "//*[@content-desc = 'More options']")
    AndroidElement menuOptions;

    @FindBy(id = "//*[@text = 'Logout']")
    AndroidElement logoutBtn;

    @FindBy(xpath = "//*[@content-desc='add']")
    AndroidElement plusBtn;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowName']")
    List<AndroidElement> contactNameList;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowContainer']")
    List<AndroidElement> contactList;
    @FindBy(id = "android:id/button1")
    AndroidElement yesBtn;
    @FindBy(id = "android:id/button1")
    AndroidElement OkBtn;
    @FindBy(id="com.sheygam.contactapp:id/emptyTxt")
    AndroidElement noContactsHere;
    int countBefore;
    int countAfter;


    public boolean isActivityTitleDisplayed(String text){

        // return activityTextView.getText().contains("Contact list");
        return isShouldHave(activityTextView, text, 8);
    }

    public AuthScreen logout() {
        if (activityTextView.getText().equals("Contact list")) {
            menuOptions.click();
            logoutBtn.click();
        }
        return new AuthScreen(driver);

    }

    public AuthScreen logout2() {
        if (isElementDisplayed(menuOptions)) {
            menuOptions.click();
            logoutBtn.click();
        }
        return new AuthScreen(driver);

    }
    

    public ContactListScreen isAccountOpened() {
        Assert.assertTrue(isActivityTitleDisplayed("Contact list"));
        return this;
    }

    public AddNewContactScreen openContactForm() {
        if (activityTextView.getText().equals("Contact list")) {
            shold(plusBtn, 5);
            plusBtn.click();
        }
        return new AddNewContactScreen(driver);
    }

    public ContactListScreen deleteFirstContact(){
        isActivityTitleDisplayed("Contact list");
        countBefore = contactList.size();
        System.out.println(countBefore);
        AndroidElement first = contactList.get(0);
        Rectangle rectangle = first.getRect();
        int xFrom = rectangle.getX()+rectangle.getWidth()/8;
        int y = rectangle.getY()+rectangle.getHeight()/2;
        int xTo = rectangle.getX()+(rectangle.getWidth()/8)*7;

        TouchAction<?> touchAction = new TouchAction<>(driver);
        touchAction.longPress(PointOption.point(xFrom, y)).moveTo(PointOption.point(xTo, y)).release().perform();

        shold(OkBtn, 8);
        OkBtn.click();

        countAfter = contactList.size();
        System.out.println(countAfter);
        return this;

    }


    public ContactListScreen isListSizeLessThenOne() {

        Assert.assertEquals(countBefore-countAfter, 1);
        return this;
    }

    public ContactListScreen removeAllContacts() {
        pause(1000);
        while (contactList.size()>0){
            deleteFirstContact();
        }
        return  this;
    }

    public ContactListScreen isNoContactsHere(){
        isShouldHave(noContactsHere, "No contacts/ Add one more!", 10);
        return  this;
    }
}
