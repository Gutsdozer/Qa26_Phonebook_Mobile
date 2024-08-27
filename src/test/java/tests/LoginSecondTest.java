package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.AuthScreen;

public class LoginSecondTest extends AppiumConfig {

    @Test
    public void loginSuccess(){
        new AuthScreen(driver)
                .fillMail("mara@gmail.com")
                .fillPassword("Mmar123456$")
                .submitLogin()
                .isAccountOpened()
                .logout();
    }

    @Test
    public void loginSuccessModel(){
        new AuthScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("mara@gmail.com").password("Mmar123456$").build())
                .submitLogin()
                .isAccountOpened()
                .logout();
    }


}
