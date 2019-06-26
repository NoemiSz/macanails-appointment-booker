package com.macanails.macanailsappointmentbooker.controller;

import com.macanails.macanailsappointmentbooker.model.CalendarEvent;
import com.macanails.macanailsappointmentbooker.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@CrossOrigin
public class EventController {
        @Autowired
        CalendarEvent calendarEvent;

        @Autowired
        ReservationService reservationService;

        @PostMapping(value = "/")
        public void saveEvent(@RequestBody Map<String, String> answers) throws IOException {

        //TODO
            reservationService.saveAppointment(calendarEvent);
        }

    }
