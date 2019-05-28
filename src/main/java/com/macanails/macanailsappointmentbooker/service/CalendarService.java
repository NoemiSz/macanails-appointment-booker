package com.macanails.macanailsappointmentbooker.service;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import com.macanails.macanailsappointmentbooker.model.CalendarEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CalendarService {
    @Autowired
    CalendarConnection calendarConnection;

    @Autowired
    CalendarEvent calendarEvent;

    public List<CalendarEvent> getFreeEvents() throws IOException {
        List<CalendarEvent> items = new ArrayList<>();


        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = calendarConnection.service.events().list("primary")
                .setMaxResults(10)
                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
        for (Event event : events.getItems()) {
            if (event.getDescription() != null && event.getDescription().equals("free")) {
                CalendarEvent calendarEvent = CalendarEvent.builder()
                        .startTime(event.getStart().getDate())
                        .endTime(event.getEnd().getDateTime())
                        .build();
                items.add(calendarEvent);
            }

        }
        return items;
    }

}
