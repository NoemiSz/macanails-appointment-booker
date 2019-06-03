package com.macanails.macanailsappointmentbooker.service;

import com.google.api.client.util.DateTime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeService {
    public static LocalDateTime convertDateTimeToLocalDateTime(DateTime dateTime){
        String str = dateTime.toString();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        return LocalDateTime.parse(str, formatter);
    }

    public static DateTime convertLocalDateTimeToDateTime(LocalDateTime localDateTime){
        StringBuilder sb = new StringBuilder();
        sb.append(localDateTime).append(":00.000+02:00");
        return new DateTime(sb.toString());

    }
 }
