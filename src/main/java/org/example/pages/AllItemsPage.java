package org.example.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import java.util.Arrays;
import java.util.List;
import org.bouncycastle.crypto.agreement.srp.SRP6Client;
import org.example.constants.DefaultDuration;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$$x;


public class AllItemsPage {
    private static final int itemsSize = 0;

    //private final SelenideElement loginPageTitle = $(".inventory_item");
    private final ElementsCollection productsList = $$(".inventory_item");
    private final ElementsCollection productNamesList = $$x("//div[@class='inventory_item_name ']");
    private final ElementsCollection productPriceList = $$x("//div[@class='inventory_item_price']");
    private final ElementsCollection productDescriptionList = $$x("//div[@class='inventory_item_desc']");
    private final ElementsCollection addToCardBtnList = $$(".btn_inventory");


    public static AllItemsPage initAllItemsPage() {
        $(".title").shouldBe(Condition.visible).shouldHave(Condition.text("Products"));
        return new AllItemsPage();
    }

    public AllItemsPage checkItemDetails(int itemIndex, Product expectedProduct) {
        SelenideElement product = productsList.get(itemIndex - 1).shouldBe(Condition.visible);
        product.shouldBe(Condition.visible, DefaultDuration.DEFAULT);

        productNamesList.get(itemIndex - 1).shouldHave(Condition.text(expectedProduct.getName()));
        productDescriptionList.get(itemIndex - 1).shouldHave(Condition.text(expectedProduct.getDescription()));
        productPriceList.get(itemIndex - 1).shouldHave(Condition.text(expectedProduct.getPrice()));
        return this;
    }

    public AllItemsPage clickAddToCartButton(int btnIndex) {
        addToCardBtnList.get(btnIndex - 1)
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text("Add to cart"))
                .click();
        return this;
    }

    public AllItemsPage checkAllPageItemsAvailable() {
        List<String> listOfProductLabels =
                Arrays.asList("Sauce Labs Backpack", "Sauce Labs Bike Light",
                        "Sauce Labs Bolt T-Shirt", "Sauce Labs Fleece Jacket",
                        "Sauce Labs Onesie", "Test.allTheThings() T-Shirt (Red)");

        $$(".inventory_item_name ")
                .shouldHave(CollectionCondition.size(listOfProductLabels.size()))
                .shouldHave(CollectionCondition.exactTexts(listOfProductLabels));
        return this;
    }

    public AllItemsPage checkItemAddedToBucket(String value) {
        $(".shopping_cart_badge").shouldBe(Condition.visible,DefaultDuration.DEFAULT)
                .shouldHave(Condition.text(value));
        return this;
    }
    public AllItemsPage checkRemoveItemBtn(int btnIndex) {
        addToCardBtnList.get(btnIndex - 1)
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text("Remove"));
        return this;
    }

    public CartPage clickOnBucketIcon() {
        $("#shopping_cart_container .shopping_cart_link")
                .shouldBe(Condition.visible,DefaultDuration.DEFAULT)
                .click();
        return CartPage.initCartPage();

    }

    public AllItemsPage clickAddItemBtn(int itemIndex, String btnName) {
        addToCardBtnList.get(itemIndex - 1).shouldBe(Condition.visible,DefaultDuration.DEFAULT)
                .shouldHave(Condition.text(btnName))
                .click();
        return this;
    }


//    public AllItemsPage checkMenuItems(ProductItems productType) {
//        String locator = "(//div[@class='inventory_item_name '])[%s]";
//        switch (productType) {
//            case SAUCE_LABS_BACKPACK -> {
//                $$(String.format(locator, ProductItems.SAUCE_LABS_BACKPACK.getListIndex()))
//                        .shouldHave(CollectionCondition.size(itemsSize), DefaultDuration.DEFAULT);
//                break;
//            }
//            case SAUCE_LABS_BIKE_LIGHT -> {
//                $$(String.format(locator, ProductItems.SAUCE_LABS_BIKE_LIGHT.getListIndex()))
//                        .shouldHave(CollectionCondition.sizeGreaterThan(itemsSize));
//                break;
//            }
//            case SAUCE_LABS_BOLT_SHIRT -> {
//                $$(String.format(locator, ProductItems.SAUCE_LABS_BOLT_SHIRT.getListIndex()))
//                        .shouldHave(CollectionCondition.sizeGreaterThan(itemsSize));
//                break;
//            }
//            case SAUCE_LABS_FLEECE_JACKET -> {
//                $$(String.format(locator, ProductItems.SAUCE_LABS_FLEECE_JACKET.getListIndex()))
//                        .shouldHave(CollectionCondition.size(itemsSize), DefaultDuration.DEFAULT);
//                break;
//            }
//            case SAUCE_LABS_ONESIE -> {
//                $$(String.format(locator, ProductItems.SAUCE_LABS_ONESIE.getListIndex()))
//                        .shouldHave(CollectionCondition.sizeGreaterThan(itemsSize));
//                break;
//            }
//            case SAUCE_LABS__SHIRT -> {
//                $$(String.format(locator, ProductItems.SAUCE_LABS__SHIRT.getListIndex()))
//                        .shouldHave(CollectionCondition.sizeGreaterThan(itemsSize));
//                break;
//            }
//            default -> throw new RuntimeException("Specified List items is wrong");
//        }
}
