package com.macanails.macanailsappointmentbooker.service;

import com.macanails.macanailsappointmentbooker.model.CalendarEvent;
import com.macanails.macanailsappointmentbooker.model.Nail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {
    @Autowired
    CalendarEvent calendarEvent;

    @Autowired
    CalendarService calendarService;


    public List<CalendarEvent> getFreeSlots(Nail nail) {
        return null;
    }

    public String  saveAppointment(CalendarEvent calendarEvent) throws IOException {
        LocalDateTime startTime =calendarEvent.getStartTime();
        LocalDateTime endTime = calendarEvent.getStartTime().plusHours(calendarEvent.getNeededTime());
        List<CalendarEvent> freeSlots = calendarService.getFreeEvents(DateTimeService.convertLocalDateTimeToDateTime(startTime),
                DateTimeService.convertLocalDateTimeToDateTime(endTime));

        if (freeSlots.size() == calendarEvent.getNeededTime()) {
            calendarService.updateEvent(calendarEvent, calendarEvent.getNeededTime());
            for (int i = 1; i < calendarEvent.getNeededTime(); i++) {
                calendarService.deleteEvent(freeSlots.get(i));
            }
            return "Időpont sikeresen mentve";
        }
        return "Valaki megelőzött, kérlek válassz másik időpontot";

    }
}
