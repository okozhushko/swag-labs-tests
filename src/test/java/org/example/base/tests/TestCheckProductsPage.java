package org.example.base.tests;

import org.example.pages.AllItemsPage;
import org.example.pages.LoginPage;
import org.example.pages.Product;
import org.testng.annotations.Test;

public class TestCheckProductsPage {

    String description = "Get your testing superhero on with the Sauce Labs bolt T-shirt." +
            " From American Apparel, 100% ringspun combed cotton, heather gray with red bolt.";

    @Test
    public void testLoginWithValidCredentials() {
        Product expectedProduct = new Product("Sauce Labs Bolt T-Shirt", "$15.99", "Get your testing superhero on with the Sauce Labs bolt T-shirt. " +
                "From American Apparel, 100% ringspun combed cotton, heather gray with red bolt");
        LoginPage.login()
                .validateLoginSuccess();
        AllItemsPage.initAllItemsPage()
                .addItemToCard(3, expectedProduct);

    }
}
