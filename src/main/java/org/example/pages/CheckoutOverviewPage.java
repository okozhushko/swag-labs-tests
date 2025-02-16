package org.example.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import org.example.constants.DefaultDuration;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CheckoutOverviewPage {

    ElementsCollection labelList = $$(".summary_info_label");
    ElementsCollection fieldList = $$(".summary_value_label");

    public static CheckoutOverviewPage initCheckoutOverviewPage() {
        $(".title").shouldBe(Condition.visible).shouldHave(Condition.text("Checkout: Overview"));
        return new CheckoutOverviewPage();
    }

    public CheckoutOverviewPage checkShipmentInfo(int labelIndex, String label, String value) {
        labelList.get(labelIndex - 1)
                .shouldBe(Condition.visible, DefaultDuration.DEFAULT)
                .shouldHave(Condition.text(label));
        fieldList.get(labelIndex - 1)
                .shouldBe(Condition.visible, DefaultDuration.DEFAULT)
                .shouldHave(Condition.text(value));
        return this;
    }


}
