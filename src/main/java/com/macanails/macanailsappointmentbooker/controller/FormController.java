package com.macanails.macanailsappointmentbooker.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
public class FormController {

    @PostMapping(value = "/")
    public List<String> reactSg(@RequestParam(value = "answers") List<String> choosenOptions) {

        List<String> back = new ArrayList<>(Arrays.asList("Geeks",
                "for",
                "Geeks"));
        return back;
    }
}
