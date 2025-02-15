package org.example.base.tests;

import org.example.pages.AllItemsPage;
import org.example.pages.LoginPage;
import org.example.pages.Product;
import org.testng.annotations.Test;

public class TestCheckProductsPage {

    String firstItemDescription = "Get your testing superhero on with the Sauce Labs bolt T-shirt." +
            " From American Apparel, 100% ringspun combed cotton, heather gray with red bolt.";
    String firstItemName = "Sauce Labs Bolt T-Shirt";
    String firstItemPrice = "$15.99";
    String secondItemDescription = "It's not every day that you come across a midweight quarter-zip" +
            " fleece jacket capable of handling everything from a relaxing day outdoors to a busy day at the office.";
    String secondItemName = "Sauce Labs Fleece Jacket";
    String secondItemPrice = "$49.99";

    @Test
    public void testLoginWithValidCredentials() {
        Product expectedProductFirst = new Product(firstItemName, firstItemDescription, firstItemPrice);
        Product expectedProductSecond = new Product(secondItemName, secondItemDescription, secondItemPrice);
        LoginPage.login()
                .validateLoginSuccess();
        AllItemsPage.initAllItemsPage()
                .checkAllPageItemsAvailable()
                .checkItemDetails(3, expectedProductFirst)
                .clickAddItemBtn(3, "Add to cart")
                .checkItemAddedToBucket("1")
                .checkRemoveItemBtn(3)
                .clickOnBucketIcon()
                .checkCartItem(firstItemName, firstItemDescription, firstItemPrice)
                .checkCartItemDescription()
                .clickContinueShopingBtn()
                .checkItemDetails(4, expectedProductSecond)
                .clickAddItemBtn(4, "Add to cart")
                .checkItemAddedToBucket("2")
                .checkRemoveItemBtn(3)
                .clickOnBucketIcon()
                .checkCartItem(secondItemName, secondItemDescription, secondItemPrice);//TODO Add switch case





        //.removeCartItem()

    }
}
