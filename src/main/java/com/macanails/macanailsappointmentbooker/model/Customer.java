package com.macanails.macanailsappointmentbooker.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
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
