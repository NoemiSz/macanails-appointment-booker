package com.macanails.macanailsappointmentbooker.service;

import com.macanails.macanailsappointmentbooker.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class NailService {
    @Autowired
    Nail nail;

    public Nail createNail(Map<String, String> answers) {
        nail = new Nail(answers);
        return nail;
    }

    public int calculateNeededTime(Nail nail){
        float neededTime = 0f;
        neededTime+=nail.getDecor().getTime();
        neededTime+=nail.getType().getTime();
        return (int) Math.ceil(neededTime);
    }
}
