package com.macanails.macanailsappointmentbooker.controller;


import com.macanails.macanailsappointmentbooker.model.CalendarEvent;
import com.macanails.macanailsappointmentbooker.model.Nail;
import com.macanails.macanailsappointmentbooker.service.NailService;
import com.macanails.macanailsappointmentbooker.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class NailController {
    @Autowired
    NailService nailService;
    @Autowired
    Nail nail;
    @Autowired
    ReservationService reservationService;

    @GetMapping(value = "/")
    public Map<String, List<CalendarEvent>> getFreeSlots(@RequestBody Map<String, String> answers) {
        nail = nailService.createNail(answers);
        List<CalendarEvent> freeSlots = reservationService.getFreeSlots(nail);
        Map <String, List<CalendarEvent>> freeSlotsMap = new HashMap<String, List<CalendarEvent>>();
        freeSlotsMap.put("freeSlots",freeSlots );
        return freeSlotsMap;
    }

//    @PostMapping(value = "/personal")
//    public Map<String, String> getPersonal(@RequestBody Map<String, String> personal) {
//
//        return personal;
//    }
}
