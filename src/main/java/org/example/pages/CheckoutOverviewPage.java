package org.example.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.example.constants.DefaultDuration;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CheckoutOverviewPage {
    String finishBtnText = "Finish";

    ElementsCollection labelList = $$(".summary_info_label");
    ElementsCollection fieldList = $$(".summary_value_label");

    private final  SelenideElement finishBtn = $("#finish");
    private final  SelenideElement itemTotalLbl = $(".summary_subtotal_label");
    private final  SelenideElement itemTaxLbl = $(".summary_tax_label");
    private final  SelenideElement itemFullTotalLbl = $(".summary_total_label");

    public static CheckoutOverviewPage initCheckoutOverviewPage() {
        $(".title").shouldBe(Condition.visible).shouldHave(Condition.text("Checkout: Overview"));
        return new CheckoutOverviewPage();
    }

    public CheckoutOverviewPage checkShipmentInfo(int labelIndex, String label, String value) {
        labelList.get(labelIndex - 1).shouldBe(Condition.visible, DefaultDuration.DEFAULT).shouldHave(Condition.text(label));
        fieldList.get(labelIndex - 1).shouldBe(Condition.visible, DefaultDuration.DEFAULT).shouldHave(Condition.text(value));
        return this;
    }

    public CheckoutOverviewPage checkPrice(int labelIndex, String price, String tax, String total) {
        checkField(labelList.get(labelIndex - 1), "Price Total");
        checkField(itemTotalLbl, "Item total: $" + price);
        checkField(itemTaxLbl, "Tax: $" + tax);
        checkField(itemFullTotalLbl, "Total: $" + total);
        return this;
    }

    private void checkField(SelenideElement label, String expectedText) {
        label.shouldBe(Condition.visible, DefaultDuration.DEFAULT).shouldHave(Condition.text(expectedText));
    }


    public CheckoutCompletePage clickFinishBtn() {
        finishBtn.shouldBe(Condition.visible, DefaultDuration.DEFAULT).shouldHave(Condition.text(finishBtnText)).click();
        return CheckoutCompletePage.initCheckoutCompletePage();
    }
}
