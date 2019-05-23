package com.macanails.macanailsappointmentbooker.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;


@Data
@AllArgsConstructor
@Builder
@Component
public class Form {
    private Enum  decor;
    private Enum  length;
    private Enum  shape;
    private Enum type;

    public Form(){}

    public Form(List<Enum>enums){
        this.type = enums.get(0);
        this.shape = enums.get(1);
        this.decor = enums.get(2);
        //this.length = enums.get(3);
    }

    public Enum getType() {
        return type;
    }
}
