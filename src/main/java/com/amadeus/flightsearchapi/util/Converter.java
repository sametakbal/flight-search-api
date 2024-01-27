package com.amadeus.flightsearchapi.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Converter {

    private Converter() {
    }

    public static LocalDateTime dateFromString(String dateStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return LocalDateTime.parse(dateStr, dateTimeFormatter);
    }

    public static String dateToString(LocalDateTime date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return date.format(dateTimeFormatter);
    }
}