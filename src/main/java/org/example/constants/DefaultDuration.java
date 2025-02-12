package org.example.constants;

import java.time.Duration;

public class DefaultDuration {
    public static Duration DEFAULT = Duration.ofMillis(TimeOut.HALF_MIN.getMills());
    public static Duration LONG = Duration.ofMillis(TimeOut.MINUTE.getMills());
}