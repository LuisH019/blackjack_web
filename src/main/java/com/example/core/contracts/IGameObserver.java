package com.example.core.contracts;

import com.example.core.models.dtos.GameData;

public interface IGameObserver {
    void update(GameData data);
}