package com.example.core.models.entities;


public enum Suit {
    COPAS("Copas"),
    OUROS("Ouros"),
    PAUS("Paus"),
    ESPADAS("Espadas");

    private final String displayValue;

    Suit(String displayValue) {
        this.displayValue = displayValue;
    }

    @Override
    public String toString() {
        return displayValue;
    }
}