package org.example.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.example.constants.DefaultDuration;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CheckoutCompletePage {
    String successTitleText = "Thank you for your order!";
    String successMessageText = "Your order has been dispatched, and will arrive just as fast as the pony can get there!";
    String footerText = "© 2025 Sauce Labs. All Rights Reserved. Terms of Service | Privacy Policy";

    private final SelenideElement successIcon = $(".pony_express"),
            successTitle = $(".complete-header"),
            successMessage = $(".complete-text"),
            backHomeBtn = $("#back-to-products"),
            footerInfo = $(".footer_copy");

    private final ElementsCollection socialIndex = $$(".social li a");

    public static CheckoutCompletePage initCheckoutCompletePage() {
        $(".title").shouldBe(Condition.visible).shouldHave(Condition.text("Checkout: Complete!"));
        return new CheckoutCompletePage();
    }

    public CheckoutCompletePage checkCompleteOrderInfo() {
        successIcon.shouldBe(Condition.visible, DefaultDuration.DEFAULT);
        successTitle.shouldBe(Condition.visible, DefaultDuration.DEFAULT)
                .shouldHave(Condition.text(successTitleText));
        successMessage.shouldBe(Condition.visible, DefaultDuration.DEFAULT)
                .shouldHave(Condition.text(successMessageText));
        return this;
    }

    public CheckoutCompletePage clickBackHomeBtn() {
        backHomeBtn.shouldBe(Condition.visible, DefaultDuration.DEFAULT)
                .shouldHave(Condition.text("Back Home"))
                .click();
        return this;
    }

    public CheckoutCompletePage checkFooterInfo() {
        footerInfo.shouldBe(Condition.visible, DefaultDuration.DEFAULT)
                .shouldHave(Condition.text(footerText));
        return this;
    }

    public CheckoutCompletePage checkFooterSocialLinks(int linkIndex, String socialLink) {
        SocialLinks social = SocialLinks.fromString(socialLink);
        socialIndex.get(linkIndex - 1)
                .shouldBe(Condition.visible, DefaultDuration.DEFAULT)
                .shouldHave(Condition.attribute("href", social.getIconUrl()))
                .click();
        return checkUserRedirected(social.getRedirectUrl());
    }

    public static CheckoutCompletePage checkUserRedirected(String link) {
        String currentWindow = WebDriverRunner.getWebDriver().getWindowHandle();
        Selenide.switchTo().window(1);
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        if (!currentUrl.equals(link)) {
            throw new AssertionError("Expected URL to be " + link + " but found " + currentUrl);
        }
        WebDriverRunner.getWebDriver().close();
        Selenide.switchTo().window(currentWindow);
        return new CheckoutCompletePage();
    }
}

