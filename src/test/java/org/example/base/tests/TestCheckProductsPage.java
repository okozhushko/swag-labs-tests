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
                .addItemToCard(3, expectedProduct)
                .checkItemAddedToBucket("1")
                .clickRemoveItem(3)
                .addItemToCard(3, expectedProduct)
                .clickOnBucketIcon()
                .checkCartItem(itemName, itemDescription, itemPrice);

    }
}
