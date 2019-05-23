package com.macanails.macanailsappointmentbooker.model;

public enum Length {

    SHORT (1),
    MEDIUM (2),
    LONG (3);


    private final int weight;

    Length(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return this.weight;
    }
}
