package com.macanails.macanailsappointmentbooker.api;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.*;
import com.macanails.macanailsappointmentbooker.model.CalendarEvent;
import com.macanails.macanailsappointmentbooker.service.DateTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class CalendarService {
    @Autowired
    CalendarConnection calendarConnection;

    private String hostEmail = "macanailstest@gmail.com";


    public List<CalendarEvent> getFreeEvents(DateTime min, DateTime max) throws IOException {
        List<CalendarEvent> items = new ArrayList<>();

        Events events = getEvents(min, max);
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

    public void deleteEvent(String id) throws IOException {
        calendarConnection.service.events().delete("primary", id).execute();
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

    @Async("threadPoolTaskExecutor")
    public void deleteAppointment() throws IOException {

        Timer timer = new Timer();
        int begin = 0;
        int timeInterval = 3600000;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                LocalDateTime now = LocalDateTime.now().withSecond(1).withNano(0);
                DateTime threeMonthLater = DateTimeService.convertLocalDateTimeToDateTimeFromSec(now.plusMonths(3));
                DateTime nowDateTime = DateTimeService.convertLocalDateTimeToDateTimeFromSec(now);

                Events events = null;
                try {
                    events = getEvents(nowDateTime, threeMonthLater);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                List<Event> eventsToDelete = getEventsToDelete(events);

                try {

                    for (Event event : eventsToDelete) {
                        LocalDateTime start = DateTimeService.convertDateTimeToLocalDateTime(event.getStart().getDateTime());
                        LocalDateTime end = DateTimeService.convertDateTimeToLocalDateTime(event.getEnd().getDateTime());
                        int neededTime = end.getHour()-start.getHour();
                        calendarConnection.service.events().delete("primary", event.getId()).execute();
                        createNewSlots(neededTime, start);


                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }, begin, timeInterval);
    }

    private List<Event> getEventsToDelete(Events events) {
        List<Event> eventsToDelete = new ArrayList<>();
        for (Event event : events.getItems()) {
            if (event.getAttendees() != null) {
                System.out.println(event.getAttendees());
                if (event.getAttendees().get(1).get("responseStatus").equals("declined")) {
                    eventsToDelete.add(event);
                }
            }
        }
        return eventsToDelete;
    }

    private Events getEvents(DateTime now, DateTime threeMonthLater) throws IOException {
        return calendarConnection.service.events().list("primary")
                .setMaxResults(200)
                .setTimeMin(now)
                .setTimeMax(threeMonthLater)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
    }

    private void createNewSlots(int numberOfSlots, LocalDateTime firstStartTime) throws IOException {
        for (int i = 0; i < numberOfSlots; i++) {
            DateTime startTime = DateTimeService.convertLocalDateTimeToDateTimeFromMin(firstStartTime.plusHours(i));
            DateTime endTime = DateTimeService.convertLocalDateTimeToDateTimeFromMin(firstStartTime.plusHours(i + 1));


            Event event = new Event()
                    .setLocation("Szalon")
                    .setDescription("free");
            EventDateTime start = new EventDateTime()
                    .setDateTime(startTime);
            event.setStart(start);

            EventDateTime end = new EventDateTime()
                    .setDateTime(endTime);
            event.setEnd(end);

            String calendarId = "primary";
            calendarConnection.service.events().insert(calendarId, event).execute();

        }
    }


}
