package com.macanails.macanailsappointmentbooker.service;

import com.macanails.macanailsappointmentbooker.model.CalendarEvent;
import com.macanails.macanailsappointmentbooker.model.Nail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    public List<CalendarEvent> getFreeSlots(Nail nail) {
        return null;
    }
}
