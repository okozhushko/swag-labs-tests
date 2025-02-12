package org.example.base.tests;

import org.example.pages.LoginPage;
import org.junit.jupiter.api.Test;

public class TestCheckProductsPage {
    @Test
    public void testLoginWithValidCredentials() {
        LoginPage.login()
                .validateLoginSuccess();
    }
}
