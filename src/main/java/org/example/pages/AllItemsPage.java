package org.example.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.example.constants.DefaultDuration;
import org.example.constants.PageConstants;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.example.actions.IndexedElements.byPosition;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AllItemsPage {

    private final ElementsCollection productsList = $$(".inventory_item"),
            productNames = $$(".inventory_item_name"),
            productPrices = $$(".inventory_item_price"),
            productDescriptions = $$(".inventory_item_desc"),
            addToCardBtnList = $$(".btn_inventory");

    private final SelenideElement menuIcon = $("#react-burger-menu-btn"),
            sortDropdown = $(".product_sort_container");

    public static AllItemsPage initAllItemsPage() {
        $(".title").shouldBe(Condition.visible).shouldHave(Condition.text(PageConstants.PRODUCTS_PAGE_TITLE));
        return new AllItemsPage();
    }

    public AllItemsPage checkItemDetails(int itemPosition, Product expectedProduct) {
        byPosition(productsList, itemPosition).shouldBe(Condition.visible);
        byPosition(productNames, itemPosition).shouldHave(Condition.text(expectedProduct.getName()));
        byPosition(productDescriptions, itemPosition).shouldHave(Condition.text(expectedProduct.getDescription()));
        byPosition(productPrices, itemPosition).shouldHave(Condition.text(expectedProduct.getPrice()));
        return this;
    }

    public AllItemsPage checkAllPageItemsAvailable() {
        List<String> expectedProductNames = Arrays.asList(PageConstants.EXPECTED_PRODUCT_NAMES);

        $$(".inventory_item_name")
                .shouldHave(CollectionCondition.size(expectedProductNames.size()))
                .shouldHave(CollectionCondition.exactTexts(expectedProductNames));

        return this;
    }

    public AllItemsPage checkItemAddedToBucket(String value) {
        $(".shopping_cart_badge").shouldBe(Condition.visible)
                .shouldHave(Condition.text(value));
        return this;
    }

    public AllItemsPage checkRemoveItemBtn(int btnPosition) {
        byPosition(addToCardBtnList, btnPosition)
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text(PageConstants.REMOVE_BTN));
        return this;
    }

    public CartPage clickOnBucketIcon() {
        $("#shopping_cart_container .shopping_cart_link")
                .shouldBe(Condition.visible, DefaultDuration.DEFAULT)
                .click();
        return CartPage.initCartPage();
    }

    public AllItemsPage clickAddToCartButton(int itemPosition) {
        byPosition(addToCardBtnList, itemPosition)
                .shouldBe(Condition.visible, DefaultDuration.DEFAULT)
                .shouldHave(Condition.text(PageConstants.ADD_TO_CART_BTN))
                .click();
        return this;
    }

    public AllItemsPage clickMenu() {
        menuIcon.shouldBe(Condition.visible, DefaultDuration.DEFAULT).click();
        return this;
    }

    public AllItemsPage verifyMenuItems() {
        List<String> listOfMenuItemLabels = Arrays.asList(PageConstants.MENU_ITEMS);

        $(".bm-item-list").$$(".menu-item")
                .shouldHave(CollectionCondition.size(listOfMenuItemLabels.size()))
                .shouldHave(CollectionCondition.sizeGreaterThan(0))
                .shouldHave(CollectionCondition.exactTexts(listOfMenuItemLabels));

        return this;
    }

    public AllItemsPage sortBy(String sortOption) {
        sortDropdown.shouldBe(Condition.visible, DefaultDuration.DEFAULT)
                .selectOptionContainingText(sortOption);
        return this;
    }

    public List<String> getProductNames() {
        return productNames.texts();
    }

    public List<Double> getProductPrices() {
        return productPrices.texts().stream()
                .map(price -> Double.parseDouble(price.replace("$", "")))
                .collect(Collectors.toList());
    }
}