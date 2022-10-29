package com.example.gerimedicaexam.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
    static public LocalDate parseDate(String source) {
        if (source.isEmpty())
            return  null;
        return LocalDate.parse(source, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
}
