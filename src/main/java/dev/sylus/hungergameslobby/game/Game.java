package dev.sylus.hungergameslobby.game;

import dev.sylus.hungergameslobby.enums.GameState;

public class Game {
    GameState currentState = GameState.PREGAME;

    public GameState getState(){
        return currentState;
    }

    public void setState(GameState newState){
        currentState = newState;
    }
}
