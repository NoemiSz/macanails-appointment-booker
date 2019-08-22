package com.macanails.macanailsappointmentbooker;

import com.macanails.macanailsappointmentbooker.api.CalendarService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AppRunner implements CommandLineRunner {
    private final CalendarService calendarService;

    public AppRunner(CalendarService calendarService) {
        this.calendarService=calendarService;
    }

    @Override
    public void run(String... args) throws IOException, InterruptedException {
        calendarService.deleteAppintment();

    }
}
