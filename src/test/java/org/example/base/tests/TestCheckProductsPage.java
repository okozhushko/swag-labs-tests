package org.example.base.tests;

import org.example.constants.PageConstants;
import org.example.constants.TestConstants;
import org.example.helpers.Faker;
import org.example.pages.AllItemsPage;
import org.example.pages.LoginPage;
import org.example.pages.Product;
import org.testng.annotations.Test;

public class TestCheckProductsPage {

    @Test
    public void testLoginWithValidCredentials() {

        String firstName = Faker.getRandomFirstName();
        String lastName = Faker.getRandomLastName();
        String postalCode = String.valueOf(Faker.getRandomNumber(10000, 99999));

        Product expectedProductFirst = new Product(
                TestConstants.FIRST_ITEM_NAME,
                TestConstants.FIRST_ITEM_DESCRIPTION,
                TestConstants.FIRST_ITEM_PRICE
        );
        Product expectedProductSecond = new Product(
                TestConstants.SECOND_ITEM_NAME,
                TestConstants.SECOND_ITEM_DESCRIPTION,
                TestConstants.SECOND_ITEM_PRICE
        );

        LoginPage.login()
                .validateLoginSuccess();

        AllItemsPage.initAllItemsPage()
                .checkAllPageItemsAvailable()
                .clickMenu()
                .verifyMenuItems()
                .sortBy("Name (A to Z)").verifySortingByNameAscending()
                .sortBy("Name (Z to A)").verifySortingByNameDescending()
                .sortBy("Price (low to high)").verifySortingByPriceLowToHigh()
                .sortBy("Price (high to low)").verifySortingByPriceHighToLow()
                .checkItemDetails(3, expectedProductFirst)
                .clickAddToCartButton(3)
                .checkItemAddedToBucket("1")
                .checkRemoveItemBtn(3)
                .clickOnBucketIcon()
                .checkCartItem(1, expectedProductFirst)
                .clickCartItem(1)
                .verifyItemImage(1, TestConstants.ITEM_IMAGE_URL)
                .clickContinueShoppingBtn()
                .checkItemDetails(4, expectedProductSecond)
                .clickAddToCartButton(4)
                .checkItemAddedToBucket("2")
                .checkRemoveItemBtn(3)
                .clickOnBucketIcon()
                .checkCartItem(2, expectedProductSecond)
                .clickCheckoutBtn()
                .fillFirstName(TestConstants.EMPTY_VALUE)
                .fillLastName(firstName)
                .fillPostalCode(postalCode)
                .clickContinueBtn()
                .checkValidationMsg(TestConstants.FIRST_NAME_ERROR)
                .fillFirstName(firstName)
                .fillLastName(TestConstants.EMPTY_VALUE)
                .fillPostalCode(postalCode)
                .clickContinueBtn()
                .checkValidationMsg(TestConstants.LAST_NAME_ERROR)
                .fillFirstName(firstName)
                .fillLastName(lastName)
                .fillPostalCode(TestConstants.EMPTY_VALUE)
                .clickContinueBtn()
                .checkValidationMsg(TestConstants.POSTAL_CODE_ERROR)
                .fillFirstName(firstName)
                .fillLastName(lastName)
                .fillPostalCode(postalCode)
                .clickContinue()
                .checkOverviewItemInfo(1, TestConstants.FIRST_ITEM_NAME, TestConstants.FIRST_ITEM_DESCRIPTION)
                .checkOverviewItemInfo(2, TestConstants.SECOND_ITEM_NAME, TestConstants.SECOND_ITEM_DESCRIPTION)
                .checkShipmentInfo(1, PageConstants.PAYMENT_INFO_LABEL, TestConstants.PAYMENT_INFO)
                .checkShipmentInfo(2, PageConstants.SHIPPING_INFO_LABEL, TestConstants.SHIPPING_INFO)
                .checkPrice(3, TestConstants.ITEM_TOTAL, TestConstants.TAX_TOTAL, TestConstants.GRAND_TOTAL)
                .clickFinishBtn()
                .checkCompleteOrderInfo()
                .checkFooterSocialLinks("TWITTER")
                .checkFooterSocialLinks("FACEBOOK")
                .checkFooterSocialLinks("LINKEDIN")
                .checkCopyrightNotice()
                .clickBackHomeBtn();
    }
}
