package com.macanails.macanailsappointmentbooker.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class Customer {
    private String email;
    private String name;
    private String phone;
 @Override
    public String toString(){
     return name+"\n"+
     phone+"\n"+
     email;
 }

}
