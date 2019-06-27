package com.macanails.macanailsappointmentbooker.controller;


import com.macanails.macanailsappointmentbooker.model.*;
import com.macanails.macanailsappointmentbooker.service.CalendarService;
import com.macanails.macanailsappointmentbooker.service.DateTimeService;
import com.macanails.macanailsappointmentbooker.service.NailService;
import com.macanails.macanailsappointmentbooker.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@RestController
@CrossOrigin
public class NailController {
    @Autowired
    NailService nailService;
    @Autowired
    ReservationService reservationService;
    @Autowired
    CalendarService calendarService;

    @PostMapping(value = "/")
    public Map<String, List<CalendarEvent>> getFreeSlots(@RequestBody Map<String, String> answers) throws IOException {
        String nailStyle = answers.get("nailStyle").replaceAll(" ", "_" ).toUpperCase();
        Nail nail = Nail.builder()
//                .decor(Decoration.valueOf(answers.get("decoration")))
                .type(NailOption.valueOf(nailStyle))
                .build();

        List<CalendarEvent> freeSlots = reservationService.getFreeSlots(nail);
        Map<String, List<CalendarEvent>> freeSlotsMap = new HashMap<String, List<CalendarEvent>>();
        List<CalendarEvent> events;

        for (CalendarEvent freeSlot : freeSlots) {
            String dateMapKey = DateTimeService.convertLocalDateTimeToDateString(freeSlot);
            events = freeSlotsMap.computeIfAbsent(dateMapKey, k -> new ArrayList<>());
            events.add(freeSlot);
        }

        return freeSlotsMap;
    }


//    @PostMapping(value = "/personal")
//    public Map<String, String> getPersonal(@RequestBody Map<String, String> personal) {
//
//        return personal;
//    }
}
