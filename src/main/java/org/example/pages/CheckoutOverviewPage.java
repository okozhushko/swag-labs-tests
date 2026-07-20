package org.example.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.example.constants.DefaultDuration;
import org.example.constants.PageConstants;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.example.actions.IndexedElements.byPosition;

public class CheckoutOverviewPage {

    private final SelenideElement finishBtn = $("#finish"),
            itemTotalLbl = $(".summary_subtotal_label"),
            itemTaxLbl = $(".summary_tax_label"),
            itemFullTotalLbl = $(".summary_total_label");

    private final ElementsCollection labelList = $$(".summary_info_label"),
            fieldList = $$(".summary_value_label"),
            itemTitle = $$(".inventory_item_name"),
            itemDescr = $$(".inventory_item_desc");

    public static CheckoutOverviewPage initCheckoutOverviewPage() {
        $(".title").shouldBe(Condition.visible)
                .shouldHave(Condition.text(PageConstants.CHECKOUT_OVERVIEW_TITLE));
        return new CheckoutOverviewPage();
    }

    public CheckoutOverviewPage checkOverviewItemInfo(int labelPosition, String label, String value) {
        byPosition(itemTitle, labelPosition).shouldBe(Condition.visible, DefaultDuration.DEFAULT)
                .shouldHave(Condition.text(label));
        byPosition(itemDescr, labelPosition).shouldBe(Condition.visible, DefaultDuration.DEFAULT)
                .shouldHave(Condition.text(value));
        return this;
    }
    public CheckoutOverviewPage checkShipmentInfo(int labelPosition, String label, String value) {
        byPosition(labelList, labelPosition).shouldBe(Condition.visible, DefaultDuration.DEFAULT)
                .shouldHave(Condition.text(label));
        byPosition(fieldList, labelPosition).shouldBe(Condition.visible, DefaultDuration.DEFAULT)
                .shouldHave(Condition.text(value));
        return this;
    }

    public CheckoutOverviewPage checkPrice(int labelPosition, String price, String tax, String total) {
        checkField(byPosition(labelList, labelPosition), PageConstants.PRICE_TOTAL_LABEL);
        checkField(itemTotalLbl, "Item total: $" + price);
        checkField(itemTaxLbl, "Tax: $" + tax);
        checkField(itemFullTotalLbl, "Total: $" + total);
        return this;
    }

    private void checkField(SelenideElement label, String expectedText) {
        label.shouldBe(Condition.visible, DefaultDuration.DEFAULT)
                .shouldHave(Condition.text(expectedText));
    }

    public CheckoutCompletePage clickFinishBtn() {
        finishBtn.shouldBe(Condition.visible, DefaultDuration.DEFAULT)
                .shouldHave(Condition.text("Finish"))
                .click();
        return CheckoutCompletePage.initCheckoutCompletePage();
    }
}
