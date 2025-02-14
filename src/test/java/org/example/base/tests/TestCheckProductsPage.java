package org.example.base.tests;

import org.example.pages.AllItemsPage;
import org.example.pages.LoginPage;
import org.example.pages.Product;
import org.testng.annotations.Test;

public class TestCheckProductsPage {

    String description = "Get your testing superhero on with the Sauce Labs bolt T-shirt." +
            " From American Apparel, 100% ringspun combed cotton, heather gray with red bolt.";
    String productName = "Sauce Labs Bolt T-Shirt";
    String productPrice = "$15.99";

    @Test
    public void testLoginWithValidCredentials() {
        Product expectedProduct = new Product(productName, productPrice, description);
        LoginPage.login()
                .validateLoginSuccess();
        AllItemsPage.initAllItemsPage()
                .checkAllPageProductsAvailable()
                .addItemToCard(3, expectedProduct);
    }
}
