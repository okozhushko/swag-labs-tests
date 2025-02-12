package org.example.constants;

public class WaitForElement {

    public static final long TIMEOUT = TimeOut.HALF_MIN.getMills();
    public static final long POLLING_INTERVAL = TimeOut.SECOND.getMills();
    public static final long SHORT_POLLING_INTERVAL = TimeOut.SECOND.getMills() / 4;
    public static final long SHORT_TIMEOUT = TimeOut.FIVE_SECONDS.getMills();
    public static final long LONG_TIMEOUT = TimeOut.MINUTE.getMills();
}