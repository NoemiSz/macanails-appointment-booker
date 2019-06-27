package com.macanails.macanailsappointmentbooker.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Map;


@Data
@AllArgsConstructor
@Builder
@Component
public class Nail {
    private Decoration  decor;
    private NailOption type;

    public Nail(){}

    public Nail(Map<String, String> answers){
//        String dec = answers.get("decoration");
//        this.decor =Decoration.valueOf(dec);
        String ty = answers.get("type");
        this.type = NailOption.valueOf(ty);

    }

}
