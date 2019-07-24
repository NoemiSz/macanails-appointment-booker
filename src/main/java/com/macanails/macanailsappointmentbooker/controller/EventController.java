package com.macanails.macanailsappointmentbooker.controller;

import com.macanails.macanailsappointmentbooker.model.BookingFormAnswerWrapper;
import com.macanails.macanailsappointmentbooker.model.Customer;
import com.macanails.macanailsappointmentbooker.service.ReservationService;
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

        @PostMapping(value = "/personal")
        public Map<String, Object> saveEvent(@RequestBody BookingFormAnswerWrapper answers) throws IOException {

            customer.setEmail(answers.getMail());
            customer.setName(answers.getName());
            customer.setPhone(answers.getPhone());
            answers.getSelectedSlot().setCustomer(customer);
            answers.getSelectedSlot().setDescription();
            log.info(answers.getSelectedSlot().toString());
            return reservationService.saveAppointment(answers.getSelectedSlot());

        }

    }
