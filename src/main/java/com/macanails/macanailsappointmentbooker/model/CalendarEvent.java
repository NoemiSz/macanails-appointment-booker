package com.macanails.macanailsappointmentbooker.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class CalendarEvent {
    private String id;
    private LocalDateTime startTime;
    private String startHour;
    private LocalDateTime endTime;
    private Customer customer;
    private Nail nail;
    private String description;
    private int neededTime;

    public void setDescription() {
        this.description = nail.toString() +"\n"+ customer.toString();
    }
}
