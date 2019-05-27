package com.macanails.macanailsappointmentbooker.model;

import com.macanails.macanailsappointmentbooker.service.NailService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
class NailTest {
    @Autowired
    NailService nailService;
    @Autowired
    Nail nail;

    @Test
    public void existenceCheck() {
        nail = new Nail();
        assertThat(nail).isNotNull();
    }

    @Test
    public void createNail() {
        Map<String, String> answers = new HashMap();
        answers.put("shape", "ROUND");
        answers.put("type", "FILLING");
        answers.put("decoration", "YES");

        nailService = new NailService();
        nail = nailService.createNail(answers);
        assertThat(nail).isNotNull();
    }

    @Test
    public void createNailWithEnums() {
        Map<String, String> answers = new HashMap();
        answers.put("shape", "ROUND");
        answers.put("type", "FILLING");
        answers.put("decoration", "YES");

        nailService = new NailService();
        nail = nailService.createNail(answers);
        assertEquals(Type.FILLING, nail.getType());
    }
}