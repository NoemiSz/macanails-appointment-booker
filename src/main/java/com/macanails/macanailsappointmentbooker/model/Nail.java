package com.macanails.macanailsappointmentbooker.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Data
@AllArgsConstructor
@Builder
@Component
public class Nail {
    private Decoration  decor;
    private Length  length;
    private Shape  shape;
    private Type type;

    public Nail(){}

    public Nail(Map<String, String> answers){
        String dec = answers.get("decoration");
        this.decor =Decoration.valueOf(dec);
        String sh = answers.get("shape");
        this.shape = Shape.valueOf(sh);
        String ty = answers.get("type");
        this.type = Type.valueOf(ty);

    }

}
