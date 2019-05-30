package com.macanails.macanailsappointmentbooker.service;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.*;
import com.macanails.macanailsappointmentbooker.model.CalendarEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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

    public void addNewEvent(CalendarEvent calendarEvent) throws IOException {

        Event event = new Event()
                .setSummary("Köröm")
                .setLocation("Szalon")
                .setDescription(calendarEvent.getDescription());

        DateTime startDateTime = new DateTime("2019-05-24T19:00:00");
        EventDateTime start = new EventDateTime()
                .setDateTime(startDateTime)
                .setTimeZone("Europe/Budapest");

        event.setStart(start);

        DateTime endDateTime = new DateTime("2019-05-24T21:00:00");
        EventDateTime end = new EventDateTime()
                .setDateTime(endDateTime)
                .setTimeZone("Europe/Budapest");


        event.setEnd(end);


        EventAttendee[] attendees = new EventAttendee[] {
                new EventAttendee().setEmail("macanailstest@gmail.com"),
                new EventAttendee().setEmail("szajler.indira@gmail.com"),
        };
        event.setAttendees(Arrays.asList(attendees));


        EventReminder[] reminderOverrides = new EventReminder[] {
                new EventReminder().setMethod("email").setMinutes(24 * 60),

        };
        Event.Reminders reminders = new Event.Reminders()
                .setUseDefault(false)
                .setOverrides(Arrays.asList(reminderOverrides));
        event.setReminders(reminders);

        String calendarId = "primary";
        event = calendarConnection.service.events().insert(calendarId, event).execute();
        System.out.printf("Event created: %s\n", event.getHtmlLink());

    }

    public void updateEvent(CalendarEvent calendarEvent) throws IOException {
        Event event = calendarConnection.service.events().get("primary", calendarEvent.getId()).execute();


        event.setDescription("test");


        Event updatedEvent = calendarConnection.service.events().update("primary", event.getId(), event).execute();
    }

}
