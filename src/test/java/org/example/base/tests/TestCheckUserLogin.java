package org.example.base.tests;

import org.example.pages.AllItemsPage;
import org.example.pages.LoginPage;
import org.testng.annotations.Test;


public class TestCheckUserLogin extends BaseTest {

    @Test
    public void testLoginWithInvalidCredentials() {
        LoginPage.loginWithInvalidCredentials("invalidUser", "wrongPassword")
                .validateErrorMessage();
    }

    @Test
    public void testLogout() {
        LoginPage.login()
                .validateLoginSuccess();

        AllItemsPage.initAllItemsPage()
                .clickMenu()
                .clickLogout();
    }
}
