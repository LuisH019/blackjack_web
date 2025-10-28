package com.example.core.models.participants;

public class Dealer extends Player {

    public Dealer() {
        super("Dealer");
    }

    public boolean needsToHit() {
        return getHandValue() <= 16;
    }
    
    public String getFirstCardDescription() {
        if (hand.getCards().isEmpty()) {
            return "Nenhuma carta";
        }
        
        return hand.getCards().get(0).toString();
    }
}