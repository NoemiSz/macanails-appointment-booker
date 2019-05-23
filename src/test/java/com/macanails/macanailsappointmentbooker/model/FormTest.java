package com.macanails.macanailsappointmentbooker.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FormTest {
    @Test
    public void existenceCheck() {
        Form form = new Form();
        assertThat(form).isNotNull();
    }

}