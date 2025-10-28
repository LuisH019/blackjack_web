package com.example.core.contracts;

import com.example.core.models.entities.Hand;

public interface ICardValueCalculator {
    int calculateValue(Hand hand);
}