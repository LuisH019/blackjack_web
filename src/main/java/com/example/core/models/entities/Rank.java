package com.example.core.models.entities;


public enum Rank {
    DOIS("2", 2),
    TRES("3", 3),
    QUATRO("4", 4),
    CINCO("5", 5),
    SEIS("6", 6),
    SETE("7", 7),
    OITO("8", 8),
    NOVE("9", 9),
    DEZ("10", 10),
    VALETE("J", 10),
    RAINHA("Q", 10),
    REI("K", 10),
    AS("A", 11);

    private final String displayValue;
    private final int baseValue;

    Rank(String displayValue, int baseValue) {
        this.displayValue = displayValue;
        this.baseValue = baseValue;
    }

    public int getBaseValue() {
        return baseValue;
    }
    
    @Override
    public String toString() {
        return displayValue;
    }
}