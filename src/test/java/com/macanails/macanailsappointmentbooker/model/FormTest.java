package com.macanails.macanailsappointmentbooker.model;

import com.macanails.macanailsappointmentbooker.service.FormService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
class FormTest {
    @Autowired
    FormService formService;
    @Autowired
    Form form;

    @Test
    public void existenceCheck() {
        form = new Form();
        assertThat(form).isNotNull();
    }

    @Test
    public void createForm() {
        Map<String, Object> answers = new HashMap();
        List<String> list = new ArrayList(){
            {
                add("a");
                add("e");
                add("g");
            }
        };
        answers.put("answers",list);
        formService = new FormService();
        form = formService.createForm(answers);
        assertThat(form).isNotNull();
    }

}