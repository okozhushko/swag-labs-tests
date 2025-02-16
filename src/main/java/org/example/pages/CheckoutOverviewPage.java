package org.example.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.example.constants.DefaultDuration;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CheckoutOverviewPage {

    ElementsCollection labelList = $$(".summary_info_label");
    ElementsCollection fieldList = $$(".summary_value_label");

    SelenideElement finishBtn = $("#finish");
    SelenideElement itemTotalLbl = $(".summary_subtotal_label");
    SelenideElement itemTaxLbl = $(".summary_tax_label");
    SelenideElement itemFullTotalLbl = $(".summary_total_label");

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

    public CheckoutOverviewPage checkPrice(int labelIndex, String price, String tax, String total) {
        labelList.get(labelIndex - 1)
                .shouldBe(Condition.visible, DefaultDuration.DEFAULT)
                .shouldHave(Condition.text("Price Total"));
        itemTotalLbl.shouldBe(Condition.visible, DefaultDuration.DEFAULT)
                .shouldHave(Condition.text("Item total: " + price));
        itemTaxLbl.shouldBe(Condition.visible, DefaultDuration.DEFAULT)
                .shouldHave(Condition.text("Tax: " + tax));
        itemFullTotalLbl.shouldBe(Condition.visible, DefaultDuration.DEFAULT)
                .shouldHave(Condition.text("Total: " + total));

        return this;
    }


    public CheckoutOverviewPage clickFinishBtn() {
        finishBtn.shouldBe(Condition.visible, DefaultDuration.DEFAULT).
                shouldHave(Condition.text("Finish"))
                .click();
        return this;
    }
}
