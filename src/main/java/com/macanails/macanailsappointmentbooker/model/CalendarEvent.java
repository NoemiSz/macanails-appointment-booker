package com.macanails.macanailsappointmentbooker.model;

import com.google.api.client.util.DateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class CalendarEvent {
    private String id;
    private DateTime startTime;
    private DateTime endTime;
    private Customer customer;
    private Nail nail;
    private String description;

    public CalendarEvent(DateTime startTime, DateTime endTime, String id) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.id = id;
    }


    @Override
    public String toString() {
        return "CalendarEvent{" +
                "id='" + id + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
