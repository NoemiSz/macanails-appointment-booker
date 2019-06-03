package com.macanails.macanailsappointmentbooker.service;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.*;
import com.macanails.macanailsappointmentbooker.model.CalendarEvent;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CalendarService {
    @Autowired
    CalendarConnection calendarConnection;

    @Autowired
    CalendarEvent calendarEvent;

    @PostConstruct
    public void test() throws IOException {
        DateTime time = new DateTime(System.currentTimeMillis());
        LocalDateTime now = DateTimeService.convertDateTimeToLocalDateTime(time).withSecond(0).withNano(0);
        DateTime max=DateTimeService.convertLocalDateTimeToDateTime(calculateDateTime(3, now));

        LocalDateTime date = calculateDateTime(2, LocalDateTime.now().withNano(0).withSecond(0));

        updateEvent(getFreeEvents(DateTimeService.convertLocalDateTimeToDateTime(now), DateTimeService.convertLocalDateTimeToDateTime(date)).get(0), 1);
    }

    public List<CalendarEvent> getFreeEvents(DateTime min, DateTime max) throws IOException {
        List<CalendarEvent> items = new ArrayList<>();

        Events events = calendarConnection.service.events().list("primary")
                .setMaxResults(20)
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
                        .id(event.getId())
                        .build();
                items.add(calendarEvent);
            }

        }
        return items;
    }


//    public void addNewEvent() throws IOException {
//
//        Event event = new Event()
//                .setSummary("Köröm")
//                .setLocation("Szalon");
////                .setDescription(calendarEvent.getDescription());
//
//        DateTime startDateTime = new DateTime("2019-05-30T19:00:00");
//        EventDateTime start = new EventDateTime()
//                .setDateTime(startDateTime)
//                .setTimeZone("Europe/Budapest");
//
//        event.setStart(start);
//
//        DateTime endDateTime = new DateTime("2019-05-30T21:00:00");
//        EventDateTime end = new EventDateTime()
//                .setDateTime(endDateTime)
//                .setTimeZone("Europe/Budapest");
//
//
//        event.setEnd(end);
//
//
//        addAttendees(event, "szajler.indira@gmail.com");
//
//
//        addReminders(event);
//
//        String calendarId = "primary";
//        event = calendarConnection.service.events().insert(calendarId, event).execute();
//        System.out.printf("Event created: %s\n", event.getHtmlLink());
//
//    }

    public void updateEvent(CalendarEvent calendarEvent, int neededHours) throws IOException {
        Event event = calendarConnection.service.events().get("primary", calendarEvent.getId()).execute();

        modifyEndTime(event,calendarEvent, neededHours);
//        addAttendees(event, calendarEvent.getCustomer().getEmail());
//        addReminders(event);
//        event.setDescription(calendarEvent.getDescription())
//                .setLocation("Szalon")
//                .setGuestsCanModify(false);

        calendarConnection.service.events().update("primary", event.getId(), event).execute();
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
                new EventAttendee().setEmail("macanailstest@gmail.com"),
                new EventAttendee().setEmail(email),
        };
        event.setAttendees(Arrays.asList(attendees));

    }

    private void modifyEndTime(Event event, CalendarEvent calendarEvent, int neededHours) {
        DateTime endDateTime = DateTimeService.convertLocalDateTimeToDateTime(calculateDateTime(neededHours, calendarEvent.getEndTime()));
        EventDateTime eventEndTime= new EventDateTime()
                .setDateTime(endDateTime)
                .setTimeZone("Europe/Budapest");
        event.setEnd(eventEndTime);

    }







    private LocalDateTime calculateDateTime(int neededHours,  LocalDateTime dateTime) {

        return dateTime.plusHours(neededHours);
    }

    public void saveEvent() {
    }
}
