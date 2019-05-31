package com.macanails.macanailsappointmentbooker.controller;

import com.macanails.macanailsappointmentbooker.model.CalendarEvent;
import com.macanails.macanailsappointmentbooker.model.Nail;
import com.macanails.macanailsappointmentbooker.service.CalendarService;
import com.macanails.macanailsappointmentbooker.service.NailService;
import com.macanails.macanailsappointmentbooker.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class EventController {

        @Autowired
        CalendarService calendarService;

        @PostMapping(value = "/")
        public void saveEvent(@RequestBody Map<String, String> answers) {

        //TODO
            calendarService.saveEvent();
        }

    }
