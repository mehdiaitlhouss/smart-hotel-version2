package com.miola.smarthotel.helpers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Code created by Andrius on 2020-09-30
 */
public class CurrentTime {

    public static String getTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }

}
