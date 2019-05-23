package com.macanails.macanailsappointmentbooker.service;

import com.macanails.macanailsappointmentbooker.model.Form;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class FormService {
    @Autowired
    Form form;

    public Form createForm(Map<String, Object> answers) {
        Form form = new Form();
        return form;
    }
}
