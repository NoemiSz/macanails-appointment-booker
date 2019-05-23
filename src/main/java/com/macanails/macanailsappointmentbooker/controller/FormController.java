package com.macanails.macanailsappointmentbooker.controller;


import com.macanails.macanailsappointmentbooker.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class FormController {
    @Autowired
    FormService formService;

    @PostMapping(value = "/")
    public Map<String, List<String>> getParameters(@RequestBody Map<String, List<String>> answers) {

        return answers;
    }

    @PostMapping(value = "/personal")
    public Map<String, List<String>> getPersonal(@RequestBody Map<String, List<String>> personal) {

        return personal;
    }
}
