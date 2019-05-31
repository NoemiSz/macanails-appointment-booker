package com.macanails.macanailsappointmentbooker.model;

public enum Shape {
    SQUARE  (3),
    ROUND(2);


    private final int weight;

    Shape(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return this.weight;
    }
}
