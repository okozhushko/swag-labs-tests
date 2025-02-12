package org.example.constants;

public enum TimeOut {

    FIVE_MINS(300),
    MINUTE(60),
    HALF_MIN(30),
    FIFTEEN_SECONDS(15),
    TEN_SECONDS(10),
    FIVE_SECONDS(5),
    TWO_SECONDS(2),
    SECOND(1),
    TREE_SECONDS(3);

    TimeOut(int seconds) {
        this.seconds = seconds;
    }

    private int seconds;
    private long mills_scale = 1000L;

    public int getSeconds() {
        return seconds;
    }

    public long getMills() {
        return getSeconds() * mills_scale;
    }
}
