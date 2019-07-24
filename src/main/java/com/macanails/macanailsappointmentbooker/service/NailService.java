package com.macanails.macanailsappointmentbooker.service;

import com.macanails.macanailsappointmentbooker.model.*;
import org.springframework.stereotype.Service;

@Service
public class NailService {

    public int calculateNeededTime(Nail nail){
        float neededTime = 0f;
        neededTime+=nail.getDecor().getTime();
        neededTime+=nail.getType().getTime();
        return (int) Math.ceil(neededTime);
    }
}
