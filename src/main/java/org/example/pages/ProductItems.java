package org.example.pages;

public enum ProductItems {

    SAUCE_LABS_BACKPACK(1),
    SAUCE_LABS_BIKE_LIGHT(2),
    SAUCE_LABS_BOLT_SHIRT(3),
    SAUCE_LABS_FLEECE_JACKET(4),
    SAUCE_LABS_ONESIE(5),
    SAUCE_LABS__SHIRT(6);

    private final int listIndex;

    ProductItems(int listIndex) {
        this.listIndex = listIndex;
    }

    public int getListIndex() {
        return listIndex;
    }
}

