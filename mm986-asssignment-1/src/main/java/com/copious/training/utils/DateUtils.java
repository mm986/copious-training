package com.copious.training.utils;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

import java.util.concurrent.atomic.AtomicReference;

public class DateUtils {

    public static LocalDate startDate(LocalDate date) {
        AtomicReference<LocalDate> startDate = new AtomicReference<>(new LocalDate());
        if (startDate.get().getDayOfWeek() < DateTimeConstants.MONDAY) {
            startDate.set(date.minusWeeks(1).withDayOfWeek(DateTimeConstants.MONDAY));
        } else {
            startDate.set(date.withDayOfWeek(DateTimeConstants.MONDAY));
        }
        return startDate.get();
    }

    public static LocalDate resolveSystemDate(LocalDate date) {
        return date == null ? LocalDate.now() : date;
    }
}