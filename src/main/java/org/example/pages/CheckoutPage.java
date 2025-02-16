package org.example.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.example.actions.ClearSelectedText;
import org.example.constants.DefaultDuration;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CheckoutPage {

    SelenideElement firstNameFld = $("#first-name");
    SelenideElement lastNameFld = $("#last-name");
    SelenideElement postalCodeNameFld = $("#postal-code");
    SelenideElement checkoutButton = $("#checkout");
    SelenideElement errorMessage = $x("//h3[@data-test='error']");

    public static CheckoutPage initCheckoutPage() {
        $(".title").shouldBe(Condition.visible).shouldHave(Condition.text("Checkout: Your Information"));
        return new CheckoutPage();
    }

    public CheckoutPage checkAndFillFirstNameFld(String firstName) {
        firstNameFld.shouldBe(Condition.visible)
                .shouldHave(Condition.attribute("placeholder", "First Name"));
        ClearSelectedText.jsClear(firstNameFld);
        firstNameFld.val(firstName);
        return this;
    }

    public CheckoutPage checkAndFillLastNameFld(String lastName) {
        lastNameFld.shouldBe(Condition.visible)
                .shouldHave(Condition.attribute("placeholder", "Last Name"));
        ClearSelectedText.jsClear(lastNameFld);
        lastNameFld.val(lastName);
        return this;
    }

    public CheckoutPage checkAndFillPostalCodeFld(String postalCode) {
        postalCodeNameFld.shouldBe(Condition.visible)
                .shouldHave(Condition.attribute("placeholder", "Zip/Postal Code"));
        ClearSelectedText.jsClear(postalCodeNameFld);
        postalCodeNameFld.val(postalCode);
        return this;
    }

    public CheckoutPage clickCheckoutBtn() {
        checkoutButton.shouldBe(Condition.visible, DefaultDuration.DEFAULT)
                .shouldHave(Condition.value("Continue"))
                .click();
        return this;
    }

    public CheckoutPage checkValidationMsg(String message) {
        errorMessage.shouldBe(Condition.visible, DefaultDuration.DEFAULT)
                .shouldHave(Condition.text(message));
        return this;
    }

    public CheckoutPage clickCloseError() {
        $(".error-button").shouldBe(Condition.visible,DefaultDuration.DEFAULT).click();
        return this;
    }
}
