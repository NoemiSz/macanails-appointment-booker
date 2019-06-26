package com.macanails.macanailsappointmentbooker.service;

import com.google.api.client.util.DateTime;
import com.macanails.macanailsappointmentbooker.model.CalendarEvent;
import com.macanails.macanailsappointmentbooker.model.Nail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {
    @Autowired
    CalendarEvent calendarEvent;

    @Autowired
    CalendarService calendarService;

    @Autowired
    NailService nailService;

    public List<CalendarEvent> getFreeSlots(Nail nail) throws IOException {
        int neededHours= nailService.calculateNeededTime(nail);
        System.out.println(new DateTime(System.currentTimeMillis()));
        LocalDateTime now = LocalDateTime.now().withNano(0);
        LocalDateTime three = now.plusMonths(3);

        DateTime threeMothLater = DateTimeService.convertLocalDateTimeToDateTime(three);
        System.out.println(threeMothLater);
        List<CalendarEvent> freeEvents = calendarService.getFreeEvents(DateTimeService.convertLocalDateTimeToDateTime(now), threeMothLater);
        List<CalendarEvent> suitableEvents = new ArrayList<>();
        for (int i = 0; i < freeEvents.size()-neededHours; i++) {
            if(freeEvents.get(i+neededHours).getEndTime() == freeEvents.get(i).getStartTime().plusHours(neededHours)){
                freeEvents.get(i).setNail(nail);
                suitableEvents.add(freeEvents.get(i));
            }

        }       return suitableEvents;
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
