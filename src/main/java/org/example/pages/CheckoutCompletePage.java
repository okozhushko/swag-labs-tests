package org.example.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.example.constants.DefaultDuration;
import org.example.constants.PageConstants;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CheckoutCompletePage {

    private final SelenideElement successIcon = $(".pony_express"),
            successTitle = $(".complete-header"),
            successMessage = $(".complete-text"),
            backHomeBtn = $("#back-to-products"),
            footerInfo = $(".footer_copy");

    private final ElementsCollection socialIndex = $$(".social li a");

    public static CheckoutCompletePage initCheckoutCompletePage() {
        $(".title").shouldBe(Condition.visible).shouldHave(Condition.text(PageConstants.CHECKOUT_COMPLETE_TITLE));
        return new CheckoutCompletePage();
    }

    public CheckoutCompletePage checkCompleteOrderInfo() {
        checkElementVisibilityAndText(successIcon, "");
        checkElementVisibilityAndText(successTitle, PageConstants.SUCCESS_TITLE);
        checkElementVisibilityAndText(successMessage, PageConstants.SUCCESS_MESSAGE);
        return this;
    }

    private void checkElementVisibilityAndText(SelenideElement element, String expectedText) {
        element.shouldBe(Condition.visible, DefaultDuration.DEFAULT);
        if (!expectedText.isEmpty()) {
            element.shouldHave(Condition.text(expectedText));
        }
    }

    public CheckoutCompletePage clickBackHomeBtn() {
        backHomeBtn.shouldBe(Condition.visible, DefaultDuration.DEFAULT)
                .shouldHave(Condition.text(PageConstants.BACK_HOME_BTN))
                .click();
        return this;
    }

    public CheckoutCompletePage checkCopyrightNotice() {
        footerInfo.shouldBe(Condition.visible, DefaultDuration.DEFAULT)
                .shouldHave(Condition.text(PageConstants.FOOTER_COPYRIGHT));
        return this;
    }
    public String clickSocialLinkAndGetRedirectedUrl(String socialName) {
        SocialLinks social = SocialLinks.fromString(socialName);

        SelenideElement socialLink = $$("ul.social a")
                .filter(Condition.text(socialName))
                .filter(Condition.attribute("href", social.getIconUrl()))
                .first()
                .shouldBe(Condition.visible, DefaultDuration.DEFAULT);

        socialLink.click();
        return closePopupAndGetRedirectedUrl();
    }

    private static String closePopupAndGetRedirectedUrl() {
        String currentWindow = WebDriverRunner.getWebDriver().getWindowHandle();
        Selenide.switchTo().window(1);
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        WebDriverRunner.getWebDriver().close();
        Selenide.switchTo().window(currentWindow);
        return currentUrl;
    }
}

