package org.example.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.example.constants.AuthConfig;
import org.example.constants.DefaultDuration;
import org.example.constants.PageConstants;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {

    private final SelenideElement loginPageTitle = $(".login_logo"),
            userPasswordField = $("#password"),
            userNameField = $("#user-name"),
            loginButton = $("#login-button"),
            productsTitle = $("span[data-test='title']"),
            errorMessage = $("h3[data-test='error']");


    private LoginPage() {
    }

    public static LoginPage initLoginPage() {
        $(".login_logo").shouldBe(Condition.visible)
                .shouldHave(Condition.text(PageConstants.LOGIN_PAGE_LOGO));
        return new LoginPage();
    }

    public static LoginPage login() {
        open(AuthConfig.BASE_URL);

        initLoginPage()
                .fillUserData(AuthConfig.USERNAME, AuthConfig.PASSWORD)
                .clickLoginButton();

        $("span[data-test='title']").shouldBe(Condition.visible)
                .shouldHave(Condition.text(PageConstants.PRODUCTS_PAGE_TITLE));
        return new LoginPage();
    }

    public static AllItemsPage loginViaCookie() {
        open(AuthConfig.BASE_URL);
        WebDriverRunner.getWebDriver().manage()
                .addCookie(new Cookie("session-username", AuthConfig.USERNAME, "/"));
        open(AuthConfig.BASE_URL + "inventory.html");
        return AllItemsPage.initAllItemsPage();
    }

    public static LoginPage loginWithInvalidCredentials(String username, String password) {
        open(AuthConfig.BASE_URL);

        initLoginPage()
                .fillUserData(username, password)
                .clickLoginButton();

        $("h3[data-test='error']").shouldBe(Condition.visible)
                .shouldHave(Condition.text(PageConstants.LOGIN_ERROR_MESSAGE));

        return new LoginPage();
    }

    private LoginPage fillUserData(String username, String password) {
        userNameField.shouldBe(Condition.visible, DefaultDuration.DEFAULT)
                .sendKeys(username);
        userPasswordField.shouldBe(Condition.visible, DefaultDuration.DEFAULT)
                .sendKeys(password);
        return this;
    }

    public LoginPage clickLoginButton() {
        loginButton.shouldBe(Condition.visible, DefaultDuration.DEFAULT)
                .click();
        return this;
    }

    public LoginPage validateErrorMessage() {
        errorMessage.shouldBe(Condition.visible)
                .shouldHave(Condition.text(PageConstants.LOGIN_ERROR_MESSAGE));
        return this;
    }

    public LoginPage validateLoginSuccess() {
        productsTitle.shouldBe(Condition.visible)
                .shouldHave(Condition.text(PageConstants.PRODUCTS_PAGE_TITLE));
        return this;
    }
}