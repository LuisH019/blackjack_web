package com.example.core.factories;

import com.example.core.models.participants.Dealer;
import com.example.core.models.participants.Player;

public class PlayerFactory {
    public Player createPlayer(String name) {
        return new Player(name);
    }

    public Dealer createDealer() {
        return new Dealer();
    }
}