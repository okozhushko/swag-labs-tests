package org.example.base.tests;

import org.example.constants.TestConstants;
import org.example.pages.LoginPage;
import org.example.pages.Product;
import org.testng.annotations.Test;

public class TestCheckCartPage extends BaseTest {

    @Test
    public void testRemoveItemFromCart() {
        Product expectedProduct = new Product(
                TestConstants.FIRST_ITEM_NAME,
                TestConstants.FIRST_ITEM_DESCRIPTION,
                TestConstants.FIRST_ITEM_PRICE
        );

        LoginPage.loginViaCookie()
                .clickAddToCartButton(3)
                .checkItemAddedToBucket("1")
                .clickOnBucketIcon()
                .checkCartItem(1, expectedProduct)
                .clickRemoveItemBtn(1)
                .checkCartIsEmpty()
                .checkCartBadgeNotVisible();
    }
}
