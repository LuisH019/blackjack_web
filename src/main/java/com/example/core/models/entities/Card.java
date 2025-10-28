package com.example.core.models.entities;


public class Card {
    private final Suit suit;
    private final Rank rank;

    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }
    
    public int getValue() {
        return rank.getBaseValue();
    }

    @Override
    public String toString() {
        return rank.toString() + " de " + suit.toString();
    }
}