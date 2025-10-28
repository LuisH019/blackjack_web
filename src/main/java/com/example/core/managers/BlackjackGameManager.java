package com.example.core.managers;

import com.example.core.contracts.IBlackjackRules;
import com.example.core.contracts.IGameController;
import com.example.core.contracts.IGameObserver;

import com.example.core.factories.PlayerFactory;

import com.example.core.models.decking.Deck;
import com.example.core.models.dtos.GameData;
import com.example.core.models.participants.Dealer;
import com.example.core.models.participants.Player;

import java.util.ArrayList;
import java.util.List;

public class BlackjackGameManager implements IGameController {
    private final Deck deck;
    private final PlayerFactory playerFactory;
    private final IBlackjackRules rulesEngine;
    private final List<IGameObserver> observers;

    private Player player;
    private Dealer dealer;
    private String statusMessage;
    private boolean isGameOver;
    private boolean isPlayersTurn;


    public BlackjackGameManager(Deck deck, PlayerFactory playerFactory, IBlackjackRules rulesEngine) {
        this.deck = deck;
        this.playerFactory = playerFactory;
        this.rulesEngine = rulesEngine;

        this.observers = new ArrayList<>();

        this.statusMessage = "Bem-vindo ao Blackjack! Clique em Novo Jogo.";
        this.isGameOver = true; 
        this.isPlayersTurn = false;
        
        this.player = this.playerFactory.createPlayer("Jogador");
        this.dealer = this.playerFactory.createDealer();
    }


    public void registerObserver(IGameObserver observer) {
        observers.add(observer);

        notifyObservers();
    }

    private void notifyObservers() {
        GameData data = new GameData(player, dealer, statusMessage, isGameOver, isPlayersTurn);

        for (IGameObserver observer : observers) {
            observer.update(data);
        }
    }

    @Override
    public void startNewGame() {
        deck.resetAndShuffle();

        player.resetHand();
        dealer.resetHand();

        dealInitialCards();

        this.statusMessage = "Sua vez. Escolha HIT ou STAND.";
        this.isGameOver = false;
        this.isPlayersTurn = true;
        
        if (rulesEngine.shouldEndGameImmediately(player)) {
            stand(); 
            return;
        }
        
        notifyObservers();
    }

    private void dealInitialCards() {
        player.getHand().addCard(deck.dealCard());
        dealer.getHand().addCard(deck.dealCard());

        player.getHand().addCard(deck.dealCard());
        dealer.getHand().addCard(deck.dealCard());
    }

    @Override
    public void hit() {
        if (isGameOver || !isPlayersTurn) return;

        player.getHand().addCard(deck.dealCard());

        if (rulesEngine.shouldEndGameImmediately(player)) {
            if (player.isBusted()) {
                this.statusMessage = rulesEngine.determineWinnerStatus(player, dealer);
                this.isGameOver = true;
                this.isPlayersTurn = false;
            } else {
                stand();
                return;
            }
        }

        notifyObservers();
    }

    @Override
    public void stand() {
        if (isGameOver || !isPlayersTurn) return;

        this.isPlayersTurn = false;
        
        rulesEngine.executeDealerTurn(dealer, deck);
        
        this.statusMessage = rulesEngine.determineWinnerStatus(player, dealer);
        this.isGameOver = true;

        notifyObservers();
    }
    
    public GameData getCurrentState() {
        return new GameData(player, dealer, statusMessage, isGameOver, isPlayersTurn);
    }
}