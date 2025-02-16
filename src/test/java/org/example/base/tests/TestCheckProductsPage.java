package org.example.base.tests;

import org.apache.commons.lang3.StringUtils;
import org.example.helpers.Faker;
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
    String addToCartText = "Add to cart";
    String emptyValue = StringUtils.EMPTY;
    ;
    String firstNameErrorMsg = "First Name is required";
    String lastNameErrorMsg = "Last Name is required";
    String postalCodeErrorMsg = "Postal Code is required";
    String price = "65.98";
    String tax = "5.28";
    String total = "71.26";

    @Test
    public void testLoginWithValidCredentials() {
        String firstName = Faker.getRandomFirstName();
        String lastName = Faker.getRandomLastName();
        String postalCode = String.valueOf(Faker.getRandomNumber(10000, 99999));

        Product expectedProductFirst = new Product(firstItemName, firstItemDescription, firstItemPrice);
        Product expectedProductSecond = new Product(secondItemName, secondItemDescription, secondItemPrice);
        LoginPage.login()
                .validateLoginSuccess();
        AllItemsPage.initAllItemsPage()
                .checkAllPageItemsAvailable()
                .clickAndCheckMenuItems()
                .sortBy("Name (A to Z)").verifySortingByNameAscending()
                .sortBy("Name (Z to A)").verifySortingByNameDescending()
                .sortBy("Price (low to high)").verifySortingByPriceLowToHigh()
                .sortBy("Price (high to low)").verifySortingByPriceHighToLow()
                .checkItemDetails(3, expectedProductFirst)
                .clickAddItemBtn(3, addToCartText)
                .checkItemAddedToBucket("1")
                .checkRemoveItemBtn(3)
                .clickOnBucketIcon()
                .checkCartItem(1, expectedProductFirst)
                .checkCartItemDescription(1)
                .clickContinueShoppingBtn()
                .checkItemDetails(4, expectedProductSecond)
                .clickAddItemBtn(4, addToCartText)
                .checkItemAddedToBucket("2")
                .checkRemoveItemBtn(3)
                .clickOnBucketIcon()
                .checkCartItem(2, expectedProductSecond)
                .clickCheckoutBtn()
                .checkAndFillFirstNameFld(emptyValue)
                .checkAndFillLastNameFld(firstName)
                .checkAndFillPostalCodeFld(postalCode)
                .clickContinueBtn()
                .checkValidationMsg(firstNameErrorMsg)
                .clickCloseError()
                .checkAndFillFirstNameFld(firstName)
                .checkAndFillLastNameFld(emptyValue)
                .checkAndFillPostalCodeFld(postalCode)
                .clickContinueBtn()
                .checkValidationMsg(lastNameErrorMsg)
                .clickCloseError()
                .checkAndFillFirstNameFld(firstName)
                .checkAndFillLastNameFld(lastName)
                .checkAndFillPostalCodeFld(emptyValue)
                .clickContinueBtn()
                .checkValidationMsg(postalCodeErrorMsg)
                .clickCloseError()
                .checkAndFillFirstNameFld(firstName)
                .checkAndFillLastNameFld(lastName)
                .checkAndFillPostalCodeFld(postalCode)
                .clickContinue()
                .checkPrice(3, price, tax, total)
                .clickFinishBtn()
                .checkCompleteOrderInfo()
                .checkFooterSocialLinks(1, "TWITTER")
                .checkFooterSocialLinks(2, "FACEBOOK")
                .checkFooterSocialLinks(3, "LINKEDIN")
                .clickBackHomeBtn();
    }
}
