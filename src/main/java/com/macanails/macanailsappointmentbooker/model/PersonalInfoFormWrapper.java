package com.macanails.macanailsappointmentbooker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalInfoFormWrapper {
    private CalendarEvent selectedSlot;
    private String name;
    private String mail;
    private String phone;
}
