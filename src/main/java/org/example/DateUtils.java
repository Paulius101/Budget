package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    private static DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static String toFormattedString(LocalDateTime timestamp){
        return DATE_TIME_FORMAT.format(timestamp);
    }
}
