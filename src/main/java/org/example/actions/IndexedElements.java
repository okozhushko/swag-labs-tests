package org.example.actions;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

public class IndexedElements {

    public static SelenideElement byPosition(ElementsCollection collection, int position) {
        if (position < 1) {
            throw new IllegalArgumentException("Position must be 1-based (>= 1), but was: " + position);
        }
        return collection.get(position - 1);
    }
}
