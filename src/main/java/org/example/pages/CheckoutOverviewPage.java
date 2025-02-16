package org.example.pages;

import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Selenide.$;

public class CheckoutOverviewPage {

    public static CheckoutOverviewPage initCheckoutOverviewPage() {
        $(".title").shouldBe(Condition.visible).shouldHave(Condition.text("Checkout: Overview"));
        return new CheckoutOverviewPage();
    }



}
