package com.macanails.macanailsappointmentbooker.model;

public enum NailDecoration {
    TRANSZFERFÓLIA_VAGY_CUKORPOR("Transzferfólia vagy cukorpor", 0.084f),
    EFFEKT_POROK("Effekt porok", 0.17f),
    INDA_MINTA("Inda minta", 0.17f),
    FESTETT_3D_VIRÁGOK("Festett 3D virágok", 0.33f),
    KÖVEK("Kövek", 0.25f),
    KOMPLEX_DÍSZÍTÉS("Komplex Díszítés", 0.5f),
    REALISZTIKUS_FESTÉS("Realisztikus festés", 1f),
    NINCS_DÍSZÍTÉS("Nincs díszítés",0f);

    private final String description;
    private final float time;

    NailDecoration(String description, float time) {
        this.description = description;
        this.time = time;
    }

    public float getTime() {
        return this.time;
    }

    public String getDescription() {
        return description;
    }
}
