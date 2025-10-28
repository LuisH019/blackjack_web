package com.example.core.managers;

import com.example.core.models.decking.Deck;
import com.example.core.contracts.IBlackjackRules;
import com.example.core.models.participants.Dealer;
import com.example.core.models.participants.Player;


public class BlackjackRulesEngine implements IBlackjackRules {
    @Override
    public void executeDealerTurn(Dealer dealer, Deck deck) {
        while (dealer.needsToHit()) {
            dealer.getHand().addCard(deck.dealCard());
        }
    }
    
    @Override
    public String determineWinnerStatus(Player player, Dealer dealer) {
        int playerValue = player.getHandValue();
        int dealerValue = dealer.getHandValue();
        
        if (player.isBusted()) {
            return "Você estourou (" + playerValue + ")! Dealer ganhou."; 
        }
        
        if (dealer.isBusted()) {
            return "Dealer estourou (" + dealerValue + ")! Você ganhou!";
        } else if (playerValue == dealerValue) {
            return "Empate (Push) com " + playerValue + "!";
        } else if (playerValue > dealerValue) {
            return "Você ganhou com " + playerValue + "!";
        } else {
            return "Dealer ganhou com " + dealerValue + ".";
        }
    }
    
    @Override
    public boolean shouldEndGameImmediately(Player player) {
        return player.isBusted() || player.getHandValue() >= 21;
    }
}