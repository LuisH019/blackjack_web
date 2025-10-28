package com.example.core.contracts;

import com.example.core.models.participants.Dealer;
import com.example.core.models.participants.Player;
import com.example.core.models.decking.Deck;

public interface IBlackjackRules {
    void executeDealerTurn(Dealer dealer, Deck deck);
    
    String determineWinnerStatus(Player player, Dealer dealer);
    
    boolean shouldEndGameImmediately(Player player);
}