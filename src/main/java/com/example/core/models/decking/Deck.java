package com.example.core.models.decking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.example.core.models.entities.Card;
import com.example.core.models.entities.Rank;
import com.example.core.models.entities.Suit;

public final class Deck {
    private static Deck instance;
    private List<Card> cards;

    private Deck() {
        resetAndShuffle();
    }

    public static Deck getInstance() {
        if (instance == null) {
            synchronized (Deck.class) {
                if (instance == null) {
                    instance = new Deck();
                }
            }
        }
        return instance;
    }

    public void resetAndShuffle() {
        cards = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                cards.add(new Card(suit, rank));
            }
        }
        Collections.shuffle(cards, new Random());
    }

    public Card dealCard() {
        if (cards.isEmpty()) {
            resetAndShuffle(); 
        }
        return cards.remove(0);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Clonagem de Deck não permitida (Singleton)");
    }
}