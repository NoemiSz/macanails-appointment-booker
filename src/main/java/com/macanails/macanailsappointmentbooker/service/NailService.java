package com.macanails.macanailsappointmentbooker.service;

import com.macanails.macanailsappointmentbooker.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class NailService {
    @Autowired
    Nail nail;
    public void setNailDetailes(NailFormWrapper answers) {
        nail.setDecorEnum(answers.getDecorationUpperCase());
        nail.setTypeEnum(answers.getNailStyleUpperCase());
    }

    public int calculateNeededTime(Nail nail){
        float neededTime = 0f;
        neededTime+=nail.getDecor().getTime();
        neededTime+=nail.getType().getTime();
        return (int) Math.ceil(neededTime);
    }
}
