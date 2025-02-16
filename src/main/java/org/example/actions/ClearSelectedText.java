package org.example.actions;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.example.constants.DefaultDuration;
import org.openqa.selenium.Keys;


public class ClearSelectedText {


    public static void clearTextStepByStep(SelenideElement element) {
        int a = element.getText().length();
        int i = 0;
        while (i < a) {
            element.should(Condition.appear, DefaultDuration.DEFAULT).sendKeys(Keys.BACK_SPACE);
            i++;
        }
    }

    public static void clearValueStepByStep(SelenideElement element) {
        int a = element.val().length();
        int i = 0;
        while (i < a) {
            element.should(Condition.appear, DefaultDuration.DEFAULT).sendKeys(Keys.BACK_SPACE);
            i++;
        }
    }

    public static void jsClear(SelenideElement element) {
        Selenide.executeJavaScript("arguments[0].value = '';", element);
    }
}
