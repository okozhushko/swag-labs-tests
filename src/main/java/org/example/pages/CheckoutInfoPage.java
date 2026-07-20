package org.example.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.example.actions.ClearSelectedText;
import org.example.constants.DefaultDuration;
import org.example.constants.PageConstants;
import static com.codeborne.selenide.Selenide.$;

public class CheckoutInfoPage {

    private final SelenideElement errorMessage = $("h3[data-test='error']"),
            postalCodeNameFld = $("#postal-code"),
            continueButton = $("#continue"),
            firstNameFld = $("#first-name"),
            lastNameFld = $("#last-name");


    public static CheckoutInfoPage initCheckoutPage() {
        $(".title").shouldBe(Condition.visible)
                .shouldHave(Condition.text(PageConstants.CHECKOUT_INFO_TITLE));
        return new CheckoutInfoPage();
    }

    public CheckoutInfoPage checkAndFillFirstNameFld(String firstName) {
        firstNameFld.shouldBe(Condition.visible, DefaultDuration.DEFAULT)
                .shouldHave(Condition.attribute("placeholder", "First Name"));
        ClearSelectedText.clearValueStepByStep(firstNameFld);
        firstNameFld.val(firstName);
        return this;
    }

    public CheckoutInfoPage checkAndFillLastNameFld(String lastName) {
        lastNameFld.shouldBe(Condition.visible, DefaultDuration.DEFAULT)
                .shouldHave(Condition.attribute("placeholder", "Last Name"));
        ClearSelectedText.clearValueStepByStep(lastNameFld);
        lastNameFld.sendKeys(lastName);
        return this;
    }

    public CheckoutInfoPage checkAndFillPostalCodeFld(String postalCode) {
        postalCodeNameFld.shouldBe(Condition.visible, DefaultDuration.DEFAULT)
                .shouldHave(Condition.attribute("placeholder", "Zip/Postal Code"));
        ClearSelectedText.clearValueStepByStep(postalCodeNameFld);
        postalCodeNameFld.sendKeys(postalCode);
        return this;
    }

    public CheckoutInfoPage clickContinueBtn() {
        continueButton.shouldBe(Condition.visible, DefaultDuration.DEFAULT)
                .shouldHave(Condition.value(PageConstants.CONTINUE_BTN_TEXT))
                .click();
        return this;
    }

    public CheckoutOverviewPage clickContinue() {
        continueButton.shouldBe(Condition.visible, DefaultDuration.DEFAULT)
                .shouldHave(Condition.value(PageConstants.CONTINUE_BTN_TEXT))
                .click();
        return CheckoutOverviewPage.initCheckoutOverviewPage();
    }

    public CheckoutInfoPage checkValidationMsg(String message) {
        errorMessage.shouldBe(Condition.visible, DefaultDuration.DEFAULT)
                .shouldHave(Condition.text("Error: " + message));
        return this;
    }
}
