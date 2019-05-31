package com.macanails.macanailsappointmentbooker.model;

import com.macanails.macanailsappointmentbooker.service.NailService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
class NailTest {

    @Test
    public void createNail() {
        Map<String, String> answers = new HashMap();
        answers.put("shape", "ROUND");
        answers.put("type", "FILLING");
        answers.put("decoration", "YES");

        NailService nailService = new NailService();
        Nail nail = nailService.createNail(answers);
        assertThat(nail).isNotNull();
    }

    @Test
    public void createNailWithEnums() {
        Map<String, String> answers = new HashMap();
        answers.put("shape", "ROUND");
        answers.put("type", "FILLING");
        answers.put("decoration", "YES");

        NailService nailService = new NailService();
        Nail nail = new Nail();
        nail = nailService.createNail(answers);
        assertEquals(Type.FILLING, nail.getType());
    }
}