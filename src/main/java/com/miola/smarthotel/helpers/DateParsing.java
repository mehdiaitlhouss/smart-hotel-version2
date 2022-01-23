package com.miola.smarthotel.helpers;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Code created by Andrius on 2020-10-07
 */
public class DateParsing {

    private DateParsing() {
    }

    public static boolean parseDate(String date) {
        try {
            LocalDate.parse(date);
            return true;
        } catch (DateTimeParseException e) {

        }
        return false;
    }
}
