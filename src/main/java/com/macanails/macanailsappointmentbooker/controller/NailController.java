package com.macanails.macanailsappointmentbooker.controller;

import com.macanails.macanailsappointmentbooker.model.*;
import com.macanails.macanailsappointmentbooker.api.CalendarService;
import com.macanails.macanailsappointmentbooker.service.NailService;
import com.macanails.macanailsappointmentbooker.api.ReservationService;
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
    @Autowired
    Nail nail;

    @PostMapping(value = "/")
    public Map<String, List<CalendarEvent>> getFreeSlots(@RequestBody NailFormWrapper answers) throws IOException {

        nailService.setNailDetailes(answers);
        return reservationService.getFreeSlotsMap(nail);
    }



}
