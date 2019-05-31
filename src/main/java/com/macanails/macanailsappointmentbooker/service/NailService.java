package com.macanails.macanailsappointmentbooker.service;

import com.macanails.macanailsappointmentbooker.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class NailService {
    @Autowired
    Nail nail;

    public Nail createNail(Map<String, String> answers) {
        nail = new Nail(answers);
        return nail;
    }
}
