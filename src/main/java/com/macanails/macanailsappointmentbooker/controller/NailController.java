package com.macanails.macanailsappointmentbooker.controller;


import com.macanails.macanailsappointmentbooker.service.NailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
public class NailController {
    @Autowired
    NailService nailService;

    @PostMapping(value = "/")
    public Map<String, String> getParameters(@RequestBody Map<String, String> answers) {

        return answers;
    }

    @PostMapping(value = "/personal")
    public Map<String, String> getPersonal(@RequestBody Map<String, String> personal) {

        return personal;
    }
}
