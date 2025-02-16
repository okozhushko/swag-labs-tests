package org.example.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.example.constants.AuthConfig;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage {

    private final SelenideElement loginPageTitle = $(".login_logo"),
            userPasswordField = $("#password"),
            userNameField = $("#user-name"),
            loginButton = $("#login-button"),
            productsTitle = $x("//span[@data-test='title']"),
            errorMessage = $("h3[data-test='error']");
    private static boolean isLoggedIn = false;

    static String errorMessageText = "Epic sadface: Username and password do not match any user in this service";

    private LoginPage() {
    }

    public static LoginPage initLoginPage() {
        $(".login_logo").shouldBe(Condition.visible).shouldHave(Condition.text("Swag Labs"));
        return new LoginPage();
    }

    public static LoginPage login() {
        if (!isLoggedIn) {
            open(AuthConfig.BASE_URL);

            initLoginPage()
                    .fillUserData(AuthConfig.USERNAME, AuthConfig.PASSWORD)
                    .clickLoginButton();

            $x("//span[@data-test='title']").shouldBe(Condition.visible).shouldHave(Condition.text("Products"));
            isLoggedIn = true;
        }
        return new LoginPage();
    }

    public static LoginPage loginWithInvalidCredentials(String username, String password) {
        open(AuthConfig.BASE_URL);

        initLoginPage()
                .fillUserData(username, password)
                .clickLoginButton();

        $("h3[data-test='error']").shouldBe(Condition.visible).shouldHave(Condition.text(errorMessageText));

        return new LoginPage();
    }

    private LoginPage fillUserData(String username, String password) {
        userNameField.setValue(username);
        userPasswordField.setValue(password);
        return this;
    }

    public LoginPage clickLoginButton() {
        loginButton.click();
        return this;
    }

    public LoginPage validateErrorMessage() {
        errorMessage.shouldBe(Condition.visible).shouldHave(Condition.text(errorMessageText));
        return this;
    }

    public LoginPage validateLoginSuccess() {
        productsTitle.shouldBe(Condition.visible).shouldHave(Condition.text("Products"));
        return this;
    }
}