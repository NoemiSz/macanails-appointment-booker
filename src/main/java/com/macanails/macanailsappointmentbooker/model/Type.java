package com.macanails.macanailsappointmentbooker.model;

public enum Type {
    FILLING (1),
    REMOVING(2),
    BUILDING(3);


    private final int weight;

    Type(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return this.weight;
    }

}
