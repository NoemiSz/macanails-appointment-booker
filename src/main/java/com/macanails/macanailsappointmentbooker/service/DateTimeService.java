package com.macanails.macanailsappointmentbooker.service;

import com.google.api.client.util.DateTime;
import com.macanails.macanailsappointmentbooker.model.CalendarEvent;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeService {
    public static LocalDateTime convertDateTimeToLocalDateTime(DateTime dateTime){
        String str = dateTime.toString();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        return LocalDateTime.parse(str, formatter);
    }

    public static DateTime convertLocalDateTimeToDateTimeFromSec(LocalDateTime localDateTime){
        StringBuilder sb = new StringBuilder();
        sb.append(localDateTime).append(".000+02:00");
        return new DateTime(sb.toString());

    }
    public static String convertLocalDateTimeToDateString(CalendarEvent freeSlot) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return freeSlot.getStartTime().format(formatter);
    }

    public static String convertLocalDateTimeToTimeString(LocalDateTime startTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return startTime.format(formatter);

    }

    public static DateTime convertLocalDateTimeToDateTimeFromMin(LocalDateTime localDateTime) {
        StringBuilder sb = new StringBuilder();
        sb.append(localDateTime).append(":00.000+02:00");
        return new DateTime(sb.toString());

    }
}
