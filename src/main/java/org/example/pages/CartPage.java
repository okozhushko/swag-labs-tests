package org.example.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.example.constants.DefaultDuration;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CartPage {
    String itemImageLik = "https://www.saucedemo.com/static/media/bolt-shirt-1200x1500.c2599ac5.jpg";
    String checkOutBtnText = "Checkout";
    String removeBtnText = "Remove";

    private final ElementsCollection itemDescr = $$(".inventory_item_desc");
    private final ElementsCollection itemName = $$(".inventory_item_name");
    private final ElementsCollection itemPrice = $$(".inventory_item_price");
    private final ElementsCollection itemImage = $$(".inventory_details_img");
    private final SelenideElement checkoutBtn = $("#checkout");


    public static CartPage initCartPage() {
        $(".title").shouldBe(Condition.visible).shouldHave(Condition.text("Your Cart"));
        return new CartPage();
    }

    public CartPage checkCartItem(int itemIndex, Product expectedProduct) {
        $(".cart_item").shouldBe(Condition.exist);
        itemName.get(itemIndex - 1).shouldBe(Condition.visible).shouldHave(Condition.text(expectedProduct.getName()));
        itemDescr.get(itemIndex - 1).shouldBe(Condition.visible).shouldHave(Condition.text(expectedProduct.getDescription()));
        itemPrice.get(itemIndex - 1).shouldBe(Condition.visible).shouldHave(Condition.text(expectedProduct.getPrice()));
        $(".cart_button").shouldBe(Condition.visible).shouldHave(Condition.text(removeBtnText));
        return this;
    }

    public CartPage checkCartItemDescription(int item) {
        itemName.get(item - 1).click();
        itemImage.get(item - 1).shouldHave(Condition.attribute("src", itemImageLik));
        return this;
    }

    public CheckoutInfoPage clickCheckoutBtn() {
        checkoutBtn.shouldBe(Condition.visible, DefaultDuration.DEFAULT)
                .shouldHave(Condition.text(checkOutBtnText))
                .click();
        return CheckoutInfoPage.initCheckoutPage();
    }

    public AllItemsPage clickContinueShoppingBtn() {
        $("#back-to-products").shouldBe(Condition.visible).shouldHave(Condition.text("Back to products"))
                .click();
        return AllItemsPage.initAllItemsPage();
    }
}