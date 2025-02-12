package org.example.base.tests;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import static com.codeborne.selenide.Configuration.holdBrowserOpen;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;


public class BaseTest {
    @BeforeMethod
    public void setUp() {
        open("https://www.saucedemo.com/");
        Configuration.browserSize = "1920x1080";
        Configuration.browser = "chrome";
        Configuration.timeout = 5000;
    }


    @AfterMethod
    public void tearDown() {
        closeWebDriver();
    }
}
