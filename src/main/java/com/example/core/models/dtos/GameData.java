package com.example.core.models.dtos;

import java.util.List;
import java.util.stream.Collectors;

import com.example.core.models.entities.Card;
import com.example.core.models.participants.Dealer;
import com.example.core.models.participants.Player;


public class GameData {
    
    private final Player player;
    private final Dealer dealer;
    private final String statusMessage; 
    private final boolean isGameOver;
    private final boolean isPlayersTurn;

    public GameData(Player player, Dealer dealer, String statusMessage, boolean isGameOver, boolean isPlayersTurn) {
        this.player = player;
        this.dealer = dealer;
        this.statusMessage = statusMessage;
        this.isGameOver = isGameOver;
        this.isPlayersTurn = isPlayersTurn;
    }

    public String getPlayerHandDetails() {
        List<String> cardRanks = player.getHand().getCards().stream().map(Card::toString).collect(Collectors.toList());
        return String.join(", ", cardRanks);
    }
    
    public int getPlayerValue() {
        return player.getHandValue();
    }


    public String getDealerHandDetails(boolean isPlayerTurn) {
        List<Card> dealerCards = dealer.getHand().getCards();
        if (dealerCards.isEmpty()) {
            return "";
        }
        
        if (isGameOver || !isPlayerTurn) {
            return dealerCards.stream().map(Card::toString).collect(Collectors.joining(", "));
        }
        
        String firstCard = dealerCards.get(0).toString();
        
        if (dealerCards.size() > 1) {
            return firstCard + ", [Carta Oculta]";
        }
        return firstCard;
    }
    
    public int getDealerValue(boolean isPlayerTurn) {
        if (isGameOver || !isPlayerTurn) {
            return dealer.getHandValue();
        }
        
        if (dealer.getHand().getCards().isEmpty()) return 0;
        return dealer.getHand().getCards().get(0).getValue();
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public boolean isGameOver() {
        return isGameOver;
    }
    
    public boolean isPlayersTurn() {
        return isPlayersTurn;
    }
}