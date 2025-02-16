package org.example.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.example.constants.DefaultDuration;
import static com.codeborne.selenide.Selenide.$;

public class CheckoutCompletePage {
    String successTitleText = "Thank you for your order!";
    String successMessageText = "Your order has been dispatched, and will arrive just as fast as the pony can get there!";

    SelenideElement successIcon = $(".pony_express");
    SelenideElement successTitle = $(".complete-header");
    SelenideElement successMessage = $(".complete-text");
    SelenideElement backHomeBtn = $("#back-to-products");


    public static CheckoutCompletePage initCheckoutCompletePage() {
        $(".title").shouldBe(Condition.visible).shouldHave(Condition.text("Checkout: Complete!"));
        return new CheckoutCompletePage();
    }

    public CheckoutCompletePage checkCompleteOrderInfo() {
        successIcon.shouldBe(Condition.visible, DefaultDuration.DEFAULT);
        successTitle.shouldBe(Condition.visible, DefaultDuration.DEFAULT)
                .shouldHave(Condition.text(successTitleText));
        successMessage.shouldBe(Condition.visible, DefaultDuration.DEFAULT)
                .shouldHave(Condition.text(successMessageText));
        return this;
    }

    public CheckoutCompletePage clickBackHomeBtn() {
        backHomeBtn.shouldBe(Condition.visible, DefaultDuration.DEFAULT)
                .shouldHave(Condition.text("Back Home"));
        return this;
    }
}

