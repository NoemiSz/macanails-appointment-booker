package com.macanails.macanailsappointmentbooker.model;

public enum Decoration {
    YES  (3),
    NO (1);


    private final int weight;

    Decoration(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return this.weight;
    }
}
