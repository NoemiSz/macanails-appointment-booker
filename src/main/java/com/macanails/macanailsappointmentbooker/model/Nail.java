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
    private NailDecoration decor;
    private NailType type;

    public Nail(){}

    public Nail(Map<String, String> answers){
        String dec = answers.get("decoration");
        this.decor = NailDecoration.valueOf(dec);
        String ty = answers.get("type");
        this.type = NailType.valueOf(ty);

    }

    @Override
    public String toString() {
        return getType().getDescription()+"\n"
                +getDecor().getDescription();
    }

}
