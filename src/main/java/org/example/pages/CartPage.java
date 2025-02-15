package org.example.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$;

public class CartPage {
    String itemImageLik = "https://www.saucedemo.com/static/media/bolt-shirt-1200x1500.c2599ac5.jpg";

    private final SelenideElement itemDescr = $(".inventory_item_desc");
    private final SelenideElement itemName = $(".inventory_item_name");
    private final SelenideElement itemPrice = $(".inventory_item_price");
    private final SelenideElement itemImage = $(".inventory_details_img");

    public static CartPage initCartPage() {
        $(".title").shouldBe(Condition.visible).shouldHave(Condition.text("Your Cart"));
        return new CartPage();
    }

    public CartPage checkCartItem(String name, String description, String price) {
        $(".cart_item").shouldBe(Condition.exist);
        $(itemName).shouldBe(Condition.visible).shouldHave(Condition.text(name));
        $(itemDescr).shouldBe(Condition.visible).shouldHave(Condition.text(description));
        $(itemPrice).shouldBe(Condition.visible).shouldHave(Condition.text(price));
        $(".cart_button").shouldBe(Condition.visible).shouldHave(Condition.text("Remove"));
        return this;
    }

    public CartPage checkCartItemDescription() {
        $(itemName).click();
        $(itemImage).shouldHave(Condition.attribute("src", itemImageLik));
        return this;
    }

    public AllItemsPage clickContinueShopingBtn() {
        $("#back-to-products").shouldBe(Condition.visible).shouldHave(Condition.text("Back to products"))
                .click();
        return AllItemsPage.initAllItemsPage();
    }
}
