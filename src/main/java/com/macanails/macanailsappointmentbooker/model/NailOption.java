package com.macanails.macanailsappointmentbooker.model;

import lombok.Data;


public enum NailOption {
    GÉLLAKK("Natúr körömre géllakk", 0.5f),
    GÉLLAKK_MEGERŐSÍTÉSSEL("Natúr körömre géllakk megerősítéssel", 0.75f),
    MŰKÖRÖM_S_MÉRET("Natúr körömre S műköröm", 1.33f),
    MŰKÖRÖM_M_MÉRET("Natúr körömre M műköröm", 1.5f),
    MŰKÖRÖM_L_MÉRET("Natúr körömre L műköröm", 1.66f),
    GÉLLAKK_ELTÁVOLÍTÁSA_MANIKŰRREL("Géllakk eltávolítás manikűrrel", 0.5f),
    GÉLLAKKCSERE("Géllakkcsere", 0.75f),
    MEGERŐSÍTETT_GÉLLAKK_CSERE("Megerősített géllakk csere", 0.83f),
    GÉLLAKK_ELTÁVOLÍTÁS_MAJD_ÉPÍTÉS_S_MÉRET("Géllakk eltávolítás, majd S építés", 1.66f),
    GÉLLAKK_ELTÁVOLÍTÁS_MAJD_ÉPÍTÉS_M_MÉRET("Géllakk eltávolítás, majd M építés", 1.83f),
    GÉLLAKK_ELTÁVOLÍTÁS_MAJD_ÉPÍTÉS_L_MÉRET("Géllakk eltávolítás, majd L építés", 2),
    MŰKÖRÖM_ELTÁVOLÍTÁSA_MANIKŰRREL("Műköröm eltávolítás manikűrrel", 0.5f),
    MŰKÖRÖM_ELTÁVOLÍTÁS_MAJD_GÉLLAKK("Műköröm eltávolítás, majd géllakk", 0.66f),
    TÖLTÉS_S_MÉRET("S töltés", 1.33f),
    TÖLTÉS_M_MÉRET("M töltés", 1.5f),
    TÖLTÉS_L_MÉRET("L töltés", 1.66f),
    EGY_KÖRÖM_JAVÍTÁSA("Javítás", 0.5f);

    private final String description;
    private final float time;

    NailOption(String description, float time) {

        this.description = description;
        this.time = time;
    }

    public float getTime() {
        return time;
    }
}
