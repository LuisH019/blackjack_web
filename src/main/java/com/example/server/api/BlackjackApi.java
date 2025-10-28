package com.example.server.api;

import com.example.core.contracts.IGameController;
import com.example.core.contracts.IGameObserver;
import com.example.core.managers.BlackjackGameManager;
import com.example.core.models.dtos.GameData;

import spark.ModelAndView;
import spark.TemplateViewRoute;
import spark.Route;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class BlackjackApi implements IGameObserver {
    
    private static final ThymeleafTemplateEngine engine = new ThymeleafTemplateEngine();

    private IGameController gameController;
    private BlackjackGameManager gameManager;
    
    private GameData currentData;

    private String ipAddress;
    private int port;

    public BlackjackApi(BlackjackGameManager gameManager) {
        this.gameController = gameManager; 
        this.gameManager = gameManager; 
        this.ipAddress = "0.0.0.0";
        this.port = 4567;
        
        this.gameManager.registerObserver(this);
    }
    
    
    @Override
    public void update(GameData data) {
        this.currentData = data;
    }
    
    public void run() {
        port(this.port);
        ipAddress(this.ipAddress);
        staticFiles.location("/public"); 
        configureRoutes();
    }

    private void configureRoutes() {
        get("/", showIndex, engine);
        post("/new-game", newGame);
        post("/hit", hit);
        post("/stand", stand);
    }

    private final TemplateViewRoute showIndex = (req, res) -> {
        Map<String, Object> model = new HashMap<>();
        
        GameData data = currentData != null ? currentData : this.gameManager.getCurrentState();
        
        model.put("gameData", data);
        
        return new ModelAndView(model, "index.html");
    };
    
    private final Route newGame = (req, res) -> {
        gameController.startNewGame();
        res.redirect("/"); 
        return "";
    };

    private final Route hit = (req, res) -> {
        gameController.hit();
        res.redirect("/"); 
        return "";
    };

    private final Route stand = (req, res) -> {
        gameController.stand();
        res.redirect("/"); 
        return "";
    };
}