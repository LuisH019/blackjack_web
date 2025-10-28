package com.example.core.models.entities;

import com.example.core.contracts.ICardValueCalculator;

import java.util.ArrayList;
import java.util.List;

public class Hand implements ICardValueCalculator {
    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    @Override
    public int calculateValue(Hand hand) {
        int total = 0;
        int aceCount = 0;

        for (Card card : hand.getCards()) {
            if (card.getRank() == Rank.AS) {
                aceCount++;
                total += 11;
            } else {
                total += card.getValue();
            }
        }

        // Se estourou 21 e há Ases, converte 11 para 1 até que a mão não estoure.
        while (total > 21 && aceCount > 0) {
            total -= 10; // 11 - 10 = 1
            aceCount--;
        }

        return total;
    }
    
    public int getValue() {
        return calculateValue(this);
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}