package com.macanails.macanailsappointmentbooker.service;

import com.macanails.macanailsappointmentbooker.model.Customer;
import com.macanails.macanailsappointmentbooker.model.PersonalInfoFormWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    Customer customer;
    public void setCustomerDetails(PersonalInfoFormWrapper answers) {
        customer.setEmail(answers.getMail());
        customer.setName(answers.getName());
        customer.setPhone(answers.getPhone());
        answers.getSelectedSlot().setCustomer(customer);
        answers.getSelectedSlot().setDescription();
    }
}
