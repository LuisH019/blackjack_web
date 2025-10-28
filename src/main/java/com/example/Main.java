package com.example;


import com.example.core.contracts.IBlackjackRules;

import com.example.core.factories.PlayerFactory;

import com.example.core.managers.BlackjackGameManager;
import com.example.core.managers.BlackjackRulesEngine;

import com.example.core.models.decking.Deck; 

import com.example.server.api.BlackjackApi;

public class Main {
    public static void main(String[] args) {
        Deck deck = Deck.getInstance();

        PlayerFactory playerFactory = new PlayerFactory(); 
        
        IBlackjackRules rulesEngine = new BlackjackRulesEngine();
        
        BlackjackGameManager gameManager = new BlackjackGameManager(deck, playerFactory, rulesEngine);
        
        BlackjackApi api = new BlackjackApi(gameManager); 

        api.run();
    }
}