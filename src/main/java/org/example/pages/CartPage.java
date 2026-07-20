package org.example.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.example.constants.DefaultDuration;
import org.example.constants.PageConstants;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.example.actions.IndexedElements.byPosition;

public class CartPage {

    private final SelenideElement checkoutBtn = $("#checkout");

    private final ElementsCollection itemDescr = $$(".inventory_item_desc"),
            itemName = $$(".inventory_item_name"),
            itemPrice = $$(".inventory_item_price"),
            itemImage = $$(".inventory_details_img");


    public static CartPage initCartPage() {
        $(".title").shouldBe(Condition.visible)
                .shouldHave(Condition.text(PageConstants.YOUR_CART_TITLE));
        return new CartPage();
    }

    public CartPage checkCartItem(int itemPosition, Product expectedProduct) {
        $(".cart_item").shouldBe(Condition.exist);
        byPosition(itemName, itemPosition).shouldBe(Condition.visible)
                .shouldHave(Condition.text(expectedProduct.getName()));
        byPosition(itemDescr, itemPosition).shouldBe(Condition.visible)
                .shouldHave(Condition.text(expectedProduct.getDescription()));
        byPosition(itemPrice, itemPosition).shouldBe(Condition.visible)
                .shouldHave(Condition.text(expectedProduct.getPrice()));
        $(".cart_button").shouldBe(Condition.visible)
                .shouldHave(Condition.text(PageConstants.REMOVE_BTN));
        return this;
    }

    public CartPage clickCartItem(int itemPosition) {
        byPosition(itemName, itemPosition).shouldBe(Condition.visible, DefaultDuration.DEFAULT)
                .click();
        return this;
    }

    public CartPage verifyItemImage(int itemPosition, String imageUrl) {
        byPosition(itemImage, itemPosition).shouldHave(Condition.attribute("src", imageUrl));
        return this;
    }

    public CheckoutInfoPage clickCheckoutBtn() {
        checkoutBtn.shouldBe(Condition.visible, DefaultDuration.DEFAULT)
                .shouldHave(Condition.text(PageConstants.CHECKOUT_BTN))
                .click();
        return CheckoutInfoPage.initCheckoutPage();
    }

    public AllItemsPage clickContinueShoppingBtn() {
        $("#back-to-products").shouldBe(Condition.visible)
                .shouldHave(Condition.text(PageConstants.BACK_TO_PRODUCTS_BTN))
                .click();
        return AllItemsPage.initAllItemsPage();
    }
}