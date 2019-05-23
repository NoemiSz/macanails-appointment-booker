package com.macanails.macanailsappointmentbooker.service;

import com.macanails.macanailsappointmentbooker.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class FormService {
    @Autowired
    Form form;

    public Form createForm(Map<String, List<String>> answers) {
        List<String> letters = answers.get("answers");
        List<Enum> enums= matchWithEnums(letters);
        Form form = new Form(enums);
        return form;
    }

    private List<Enum> matchWithEnums(List<String> letters){
        List<Enum> enums = new ArrayList<>();
        for (String item : letters){
            if (item == "a"){
                enums.add(Type.FILLING);
            }else if(item == "b"){
                enums.add(Type.REMOVING);
            }else if(item == "c"){
                enums.add(Type.BUILDING);
            }else if(item == "d"){
                enums.add(Shape.SQUARE);
            }else if(item == "e"){
                enums.add(Shape.ROUND);
            }else if(item == "f"){
                enums.add(Decoration.YES);
            }else if(item == "g"){
                enums.add(Decoration.NO);
            }else if(item == "h"){
                enums.add(Length.SHORT);
            }else if(item == "i"){
                enums.add(Length.MEDIUM);
            }else if(item == "j") {
                enums.add(Length.LONG);
            }
        }
        return enums;
    }
}
