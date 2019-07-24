package com.macanails.macanailsappointmentbooker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NailFormWrapper {
    private String nailStyle;
    private String decoration;


    public String getNailStyleUpperCase() {
        return nailStyle.replaceAll(" ", "_" ).toUpperCase();
    }

    public String getDecorationUpperCase() {
        return decoration.replaceAll(" ", "_" ).toUpperCase();
    }
}
