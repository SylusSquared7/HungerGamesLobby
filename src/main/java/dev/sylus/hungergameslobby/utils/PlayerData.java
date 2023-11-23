package dev.sylus.hungergameslobby.utils;

import java.util.UUID;

public class PlayerData {
    private UUID uuid; // Players UUID (Duh)
    private String name;
    private int overallPoints; // The points from other games (as well as this one)
    private int gamePoints; // The points earned in this game
    private int currentKills; // The kills from this game

    public PlayerData(UUID localUuid, String localName, int localOverallPoints, int localGamePoints, int localCurrentKills){
        this.uuid = localUuid;
        this.name = localName;
        this.overallPoints = localOverallPoints;
        this.gamePoints = localGamePoints;
        this.currentKills = localCurrentKills;
    }

    // Getters and stuff
    public UUID getUuid(){
        return uuid;
    }
    public String getName(){
        return name;
    }

    public int getCurrentPoints(){
        return overallPoints;
    }

    public int getGamePoints(){
        return gamePoints;
    }

    public int getCurrentKills(){
        return currentKills;
    }

    public void setOverallPoints(int newOverallPoints){
        this.overallPoints = newOverallPoints;
    }

    public void setGamePoints(int newGamePoints){
        this.gamePoints = newGamePoints;
    }

    public void  setCurrentKills(int newCurrentKills){
        this.currentKills = newCurrentKills;
    }

    public void setUuid (UUID newUUID){
        this.uuid = newUUID;
    }

    public void setName(String newName){
        name = newName;
    }
}
