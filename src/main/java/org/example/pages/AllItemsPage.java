package org.example.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.example.constants.DefaultDuration;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.$$x;
import static org.assertj.core.api.Assertions.*;


public class AllItemsPage {

    String removeBtnText = "Remove";

    private final ElementsCollection productsList = $$(".inventory_item"),
            productNamesList = $$x("//div[@class='inventory_item_name ']"),
            productPriceList = $$x("//div[@class='inventory_item_price']"),
            productDescriptionList = $$x("//div[@class='inventory_item_desc']"),
            addToCardBtnList = $$(".btn_inventory"),
            productNames = $$(".inventory_item_name"),
            productPrices = $$(".inventory_item_price");

    private final SelenideElement menuIcon = $("#react-burger-menu-btn"),
            sortDropdown = $(".product_sort_container");

    public static AllItemsPage initAllItemsPage() {
        $(".title").shouldBe(Condition.visible).shouldHave(Condition.text("Products"));
        return new AllItemsPage();
    }

    public AllItemsPage checkItemDetails(int itemIndex, Product expectedProduct) {
        SelenideElement product = productsList.get(itemIndex - 1).shouldBe(Condition.visible);
        product.shouldBe(Condition.visible, DefaultDuration.DEFAULT);

        productNamesList.get(itemIndex - 1).shouldHave(Condition.text(expectedProduct
                .getName()));
        productDescriptionList.get(itemIndex - 1).shouldHave(Condition.text(expectedProduct.getDescription()));
        productPriceList.get(itemIndex - 1).shouldHave(Condition.text(expectedProduct.getPrice()));
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
        $(".shopping_cart_badge").shouldBe(Condition.visible, DefaultDuration.DEFAULT)
                .shouldHave(Condition.text(value));
        return this;
    }

    public AllItemsPage checkRemoveItemBtn(int btnIndex) {
        addToCardBtnList.get(btnIndex - 1)
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text(removeBtnText));
        return this;
    }

    public CartPage clickOnBucketIcon() {
        $("#shopping_cart_container .shopping_cart_link")
                .shouldBe(Condition.visible, DefaultDuration.DEFAULT)
                .click();
        return CartPage.initCartPage();
    }

    public AllItemsPage clickAddToCartButton(int itemIndex, String btnName) {
        addToCardBtnList.get(itemIndex - 1).shouldBe(Condition.visible, DefaultDuration.DEFAULT)
                .shouldHave(Condition.text(btnName))
                .click();
        return this;
    }

    public AllItemsPage clickAndCheckMenuItems() {
        menuIcon.shouldBe(Condition.visible, DefaultDuration.DEFAULT).click();
        List<String> listOfMenuItemLabels =
                Arrays.asList("All Items", "About", "Logout", "Reset App State");

        $(".bm-item-list").$$(".menu-item")
                .shouldHave(CollectionCondition.size(listOfMenuItemLabels.size()))
                .shouldHave(CollectionCondition.exactTexts(listOfMenuItemLabels));
        return this;
    }

    public AllItemsPage sortBy(String sortOption) {
        sortDropdown.selectOption(sortOption);
        return this;
    }

    public AllItemsPage verifySortingByNameAscending() {
        List<String> actualNames = productNames.texts();
        List<String> sortedNames = actualNames.stream().sorted().collect(Collectors.toList());

        assertThat(actualNames).isEqualTo(sortedNames);
        return this;
    }

    public AllItemsPage verifySortingByNameDescending() {
        List<String> actualNames = productNames.texts();
        List<String> sortedNames = actualNames.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        assertThat(actualNames).isEqualTo(sortedNames);
        return this;
    }

    public AllItemsPage verifySortingByPriceLowToHigh() {
        List<Double> actualPrices = productPrices.texts().stream()
                .map(price -> Double.parseDouble(price.replace("$", "")))
                .collect(Collectors.toList());

        List<Double> sortedPrices = actualPrices.stream().sorted().collect(Collectors.toList());

        assertThat(actualPrices).isEqualTo(sortedPrices);
        return this;
    }

    public AllItemsPage verifySortingByPriceHighToLow() {
        List<Double> actualPrices = productPrices.texts().stream()
                .map(price -> Double.parseDouble(price.replace("$", "")))
                .collect(Collectors.toList());

        List<Double> sortedPrices = actualPrices.stream()
                .sorted((a, b) -> Double.compare(b, a))
                .collect(Collectors.toList());

        assertThat(actualPrices).isEqualTo(sortedPrices);
        return this;
    }
}
