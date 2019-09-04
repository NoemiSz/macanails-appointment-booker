package com.macanails.macanailsappointmentbooker.service;

import com.macanails.macanailsappointmentbooker.model.Customer;
import com.macanails.macanailsappointmentbooker.model.PersonalInfoFormWrapper;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    public void setCustomerDetails(PersonalInfoFormWrapper answers) {
        Customer customer = new Customer();
        customer.setEmail(answers.getMail());
        customer.setName(answers.getName());
        customer.setPhone(answers.getPhone());
        answers.getSelectedSlot().setCustomer(customer);
        answers.getSelectedSlot().setDescription();
    }
}
