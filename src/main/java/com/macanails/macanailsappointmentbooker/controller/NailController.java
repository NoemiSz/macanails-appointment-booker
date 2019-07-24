package com.macanails.macanailsappointmentbooker.controller;


import com.macanails.macanailsappointmentbooker.model.*;
import com.macanails.macanailsappointmentbooker.service.CalendarService;
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
                .decor(Decoration.valueOf(answers.get("decoration")))
                .type(NailOption.valueOf(nailStyle))
                .build();

        return reservationService.getFreeSlotsMap(nail);
    }

}
