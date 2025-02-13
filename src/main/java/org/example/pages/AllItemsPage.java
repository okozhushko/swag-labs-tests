package org.example.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
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



    public static AllItemsPage initAllItemsPage() {
        $(".title").shouldBe(Condition.visible).shouldHave(Condition.text("Products"));
        return new AllItemsPage();
    }

    public AllItemsPage checkProductDetails(int itemIndex, Product expectedProduct) {
        SelenideElement product = productsList.get(itemIndex - 1);
        product.shouldBe(Condition.visible, DefaultDuration.DEFAULT);

        checkElement(productNamesList.get(itemIndex - 1), expectedProduct.getName());
        checkElement(productDescriptionList.get(itemIndex - 1), expectedProduct.getDescription());
        checkElement(productPriceList.get(itemIndex - 1), expectedProduct.getPrice());

        return this;
    }

    private void checkElement(SelenideElement element, String expectedText) {
        element.shouldBe(Condition.visible, DefaultDuration.DEFAULT)
                .shouldHave(Condition.text(expectedText));
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
