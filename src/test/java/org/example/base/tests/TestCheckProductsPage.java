package org.example.base.tests;

import org.example.constants.PageConstants;
import org.example.constants.TestConstants;
import org.example.helpers.Faker;
import org.example.pages.AllItemsPage;
import org.example.pages.CheckoutCompletePage;
import org.example.pages.LoginPage;
import org.example.pages.Product;
import org.example.pages.SocialLinks;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class TestCheckProductsPage extends BaseTest {

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

        AllItemsPage allItemsPage = LoginPage.loginViaCookie()
                .checkAllPageItemsAvailable()
                .clickMenu()
                .verifyMenuItems()
                .sortBy("Name (A to Z)");

        List<String> namesSortedAsc = allItemsPage.getProductNames();
        assertThat(namesSortedAsc).isEqualTo(namesSortedAsc.stream().sorted().collect(Collectors.toList()));

        allItemsPage.sortBy("Name (Z to A)");
        List<String> namesSortedDesc = allItemsPage.getProductNames();
        assertThat(namesSortedDesc).isEqualTo(namesSortedDesc.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()));

        allItemsPage.sortBy("Price (low to high)");
        List<Double> pricesSortedLow = allItemsPage.getProductPrices();
        assertThat(pricesSortedLow).isEqualTo(pricesSortedLow.stream().sorted().collect(Collectors.toList()));

        allItemsPage.sortBy("Price (high to low)");
        List<Double> pricesSortedHigh = allItemsPage.getProductPrices();
        assertThat(pricesSortedHigh).isEqualTo(pricesSortedHigh.stream().sorted((a, b) -> Double.compare(b, a)).collect(Collectors.toList()));

        allItemsPage.checkItemDetails(3, expectedProductFirst)
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
                .checkCompleteOrderInfo();

        CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage();

        String twitterUrl = checkoutCompletePage.clickSocialLinkAndGetRedirectedUrl("TWITTER");
        assertThat(twitterUrl).isEqualTo(SocialLinks.TWITTER.getRedirectUrl());

        String facebookUrl = checkoutCompletePage.clickSocialLinkAndGetRedirectedUrl("FACEBOOK");
        assertThat(facebookUrl).isEqualTo(SocialLinks.FACEBOOK.getRedirectUrl());

        String linkedinUrl = checkoutCompletePage.clickSocialLinkAndGetRedirectedUrl("LINKEDIN");
        assertThat(linkedinUrl).isEqualTo(SocialLinks.LINKEDIN.getRedirectUrl());

        checkoutCompletePage.checkCopyrightNotice()
                .clickBackHomeBtn();
    }
}
