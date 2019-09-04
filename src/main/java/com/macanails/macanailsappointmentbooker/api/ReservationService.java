package com.macanails.macanailsappointmentbooker.api;

import com.google.api.client.util.DateTime;
import com.macanails.macanailsappointmentbooker.model.CalendarEvent;
import com.macanails.macanailsappointmentbooker.model.Nail;
import com.macanails.macanailsappointmentbooker.service.DateTimeService;
import com.macanails.macanailsappointmentbooker.service.NailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReservationService {

    @Autowired
    CalendarService calendarService;

    @Autowired
    NailService nailService;

    public List<CalendarEvent> getFreeSlots(Nail nail) throws IOException {
        int neededHours = nailService.calculateNeededTime(nail);
        LocalDateTime now = LocalDateTime.now().withNano(0);
        DateTime threeMothLater = DateTimeService.convertLocalDateTimeToDateTimeFromSec(now.plusMonths(3));

        List<CalendarEvent> freeEvents = calendarService.getFreeEvents(DateTimeService.convertLocalDateTimeToDateTimeFromSec(now), threeMothLater);
        List<CalendarEvent> suitableEvents = new ArrayList<>();
        for (int i = 0; i < freeEvents.size() - neededHours; i++) {
            if (freeEvents.get(i + neededHours).getStartTime().equals(freeEvents.get(i).getStartTime().plusHours(neededHours))) {
                freeEvents.get(i).setNail(nail);
                suitableEvents.add(freeEvents.get(i));
                freeEvents.get(i).setNail(nail);
                freeEvents.get(i).setNeededTime(nailService.calculateNeededTime(nail));
            }
        }
        return suitableEvents;
    }

    public Map<String, List<CalendarEvent>> getFreeSlotsMap(Nail nail) throws IOException {
        List<CalendarEvent> freeSlots = getFreeSlots(nail);
        Map<String, List<CalendarEvent>> freeSlotsMap = new HashMap<>();
        List<CalendarEvent> events;

        for (CalendarEvent freeSlot : freeSlots) {
            String dateMapKey = DateTimeService.convertLocalDateTimeToDateString(freeSlot);
            events = freeSlotsMap.computeIfAbsent(dateMapKey, k -> new ArrayList<>());
            events.add(freeSlot);
        }
        return freeSlotsMap;
    }



    public Map<String, Object> saveAppointment(CalendarEvent calendarEvent) throws IOException {

        Map<String, Object> answer = new HashMap<String, Object>() {{
            put("status","");
            put("message", "");
            put("slots", "");
        }};

        LocalDateTime startTime = calendarEvent.getStartTime();
        LocalDateTime endTime = calendarEvent.getStartTime().plusHours(calendarEvent.getNeededTime());
        List<CalendarEvent> freeSlots = calendarService.getFreeEvents(DateTimeService.convertLocalDateTimeToDateTimeFromMin(startTime),
                DateTimeService.convertLocalDateTimeToDateTimeFromMin(endTime));

        if (freeSlots.size() == calendarEvent.getNeededTime()) {
            calendarService.updateEvent(calendarEvent, calendarEvent.getNeededTime());
            for (int i = 1; i < calendarEvent.getNeededTime(); i++) {
                calendarService.deleteEvent(freeSlots.get(i).getId());
            }
            answer.put("status", "success");
            answer.put("message", "Időpont sikeresen mentve");
            return answer;
        }
        answer.put("status", "fail");
        answer.put("message", "Valaki megelőzött, kérlek válassz másik időpontot");
        answer.put("slots", getFreeSlotsMap(calendarEvent.getNail()));
        return answer;
    }


}
