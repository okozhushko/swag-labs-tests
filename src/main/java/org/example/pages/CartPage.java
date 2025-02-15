package org.example.pages;

import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Selenide.$;

public class CartPage {
    public static CartPage initCartPage(){
        $(".title").shouldBe(Condition.visible).shouldHave(Condition.text("Your Cart"));
        return new CartPage();
    }

    public CartPage checkCartItem(String name, String description, String price){
        $(".cart_item").shouldBe(Condition.exist);
        $(".inventory_item_name").shouldBe(Condition.visible).shouldHave(Condition.text(name));
        $(".inventory_item_desc").shouldBe(Condition.visible).shouldHave(Condition.text(description));
        $(".inventory_item_price").shouldBe(Condition.visible).shouldHave(Condition.text(price));
        $(".cart_button").shouldBe(Condition.visible).shouldHave(Condition.text("Remove"));
        return this;
    }

}
