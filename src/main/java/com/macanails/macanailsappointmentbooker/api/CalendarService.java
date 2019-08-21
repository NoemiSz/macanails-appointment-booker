package com.macanails.macanailsappointmentbooker.api;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.*;
import com.macanails.macanailsappointmentbooker.model.CalendarEvent;
import com.macanails.macanailsappointmentbooker.service.DateTimeService;
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

    @Autowired
    ReservationService reservationService;

    private String hostEmail = "macanailstest@gmail.com";


    public List<CalendarEvent> getFreeEvents(DateTime min, DateTime max) throws IOException {
        List<CalendarEvent> items = new ArrayList<>();

        Events events = calendarConnection.service.events().list("primary")
                .setMaxResults(200)
                .setTimeMin(min)
                .setTimeMax(max)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
        for (Event event : events.getItems()) {
            if (event.getDescription() != null && event.getDescription().equals("free")) {
                CalendarEvent calendarEvent = CalendarEvent.builder()
                        .startTime(DateTimeService.convertDateTimeToLocalDateTime(event.getStart().getDateTime()))
                        .endTime(DateTimeService.convertDateTimeToLocalDateTime(event.getEnd().getDateTime()))
                        .startHour(DateTimeService.convertLocalDateTimeToTimeString(DateTimeService.convertDateTimeToLocalDateTime(event.getStart().getDateTime())))
                        .id(event.getId())
                        .build();
                items.add(calendarEvent);
            }

        }
        return items;
    }



    public void updateEvent(CalendarEvent calendarEvent, int neededHours) throws IOException {
        Event event = calendarConnection.service.events().get("primary", calendarEvent.getId()).execute();
        addAttendees(event, calendarEvent.getCustomer().getEmail());
        calendarConnection.service.events().update("primary", event.getId(), event).execute();

        modifyEndTime(event, calendarEvent, neededHours);
        addReminders(event);
        event.setDescription(calendarEvent.getDescription())
                .setLocation("Szalon")
                .setGuestsCanModify(false)
                .setGuestsCanInviteOthers(false)
                .setSummary("Időpont a kedvenc körmösömhöz");

        calendarConnection.service.events().update("primary", event.getId(), event).setSendNotifications(true).execute();
    }

    public void deleteEvent(CalendarEvent calendarEvent) throws IOException {
        calendarConnection.service.events().delete("primary", calendarEvent.getId()).execute();
    }

    private void addReminders(Event event) {
        EventReminder[] reminderOverrides = new EventReminder[]{
                new EventReminder().setMethod("email").setMinutes(24 * 60),

        };
        Event.Reminders reminders = new Event.Reminders()
                .setUseDefault(false)
                .setOverrides(Arrays.asList(reminderOverrides));
        event.setReminders(reminders);
    }

    private void addAttendees(Event event, String email) {
        EventAttendee[] attendees = new EventAttendee[]{
                new EventAttendee().setEmail(hostEmail),
                new EventAttendee().setEmail(email),
        };
        event.setAttendees(Arrays.asList(attendees));

    }

    private void modifyEndTime(Event event, CalendarEvent calendarEvent, int neededHours) {
        calendarEvent.setEndTime(calendarEvent.getStartTime().plusHours(neededHours));

        DateTime endDateTime = DateTimeService.convertLocalDateTimeToDateTimeFromMin(calendarEvent.getEndTime());
        EventDateTime eventEndTime = new EventDateTime()
                .setDateTime(endDateTime)
                .setTimeZone("Europe/Budapest");
        event.setEnd(eventEndTime);

    }
}
