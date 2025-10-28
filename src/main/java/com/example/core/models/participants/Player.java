package com.example.core.models.participants;

import com.example.core.models.entities.Hand;

public class Player {
    
    private final String name;
    protected final Hand hand; 

    public Player(String name) {
        this.name = name;
        this.hand = new Hand();
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }
    
    public int getHandValue() {
        return hand.getValue();
    }
    
    public boolean isBusted() {
        return getHandValue() > 21;
    }
    
    public void resetHand() {
        hand.getCards().clear();
    }
}