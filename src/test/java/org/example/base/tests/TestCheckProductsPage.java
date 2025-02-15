package org.example.base.tests;

import org.example.pages.AllItemsPage;
import org.example.pages.LoginPage;
import org.example.pages.Product;
import org.testng.annotations.Test;

public class TestCheckProductsPage {

    String itemDescription = "Get your testing superhero on with the Sauce Labs bolt T-shirt." +
            " From American Apparel, 100% ringspun combed cotton, heather gray with red bolt.";
    String itemName = "Sauce Labs Bolt T-Shirt";
    String itemPrice = "$15.99";

    @Test
    public void testLoginWithValidCredentials() {
        Product expectedProduct = new Product(itemName, itemDescription, itemPrice);
        LoginPage.login()
                .validateLoginSuccess();
        AllItemsPage.initAllItemsPage()
                .checkAllPageItemsAvailable()
                .checkItemDetails(3, expectedProduct)
                .clickAddItemBtn(3, "Add to cart")
                .checkItemAddedToBucket("1")
                .checkRemoveItemBtn(3)
                .clickOnBucketIcon()
                .checkCartItem(itemName, itemDescription, itemPrice)
                .checkCartItemDescription()
                .clickContinueShopingBtn()
                .checkItemDetails(4, expectedProduct) //TODO need to fix
                .clickAddItemBtn(4, "Add to cart");



        //.removeCartItem()

    }
}
