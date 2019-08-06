package com.macanails.macanailsappointmentbooker.controller;

import com.macanails.macanailsappointmentbooker.model.PersonalInfoFormWrapper;
import com.macanails.macanailsappointmentbooker.model.Customer;
import com.macanails.macanailsappointmentbooker.api.ReservationService;
import com.macanails.macanailsappointmentbooker.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@CrossOrigin
@Slf4j
public class EventController {
        @Autowired
        Customer customer;

        @Autowired
        ReservationService reservationService;
        @Autowired
        CustomerService customerService;

        @PostMapping(value = "/personal")
        public Map<String, Object> saveEvent(@RequestBody PersonalInfoFormWrapper answers) throws IOException {

            customerService.setCustomerDetails(answers);
            log.info(answers.getSelectedSlot().toString());
            return reservationService.saveAppointment(answers.getSelectedSlot());

        }



}
