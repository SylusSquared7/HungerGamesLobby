package dev.sylus.hungergameslobby.game;

import dev.sylus.hungergameslobby.Files.Databases;

import java.util.ArrayList;
import java.util.UUID;

public class PositionManager {
    Databases databases;
    UUID highestUUID;
    int highestPoints;
    Game game;
    ArrayList<String> newLeaderbord;
    ArrayList<String> playerLeaderbord = new ArrayList<>(); // I need to make this store players rather than strings

    public PositionManager(Databases databasesInstance, Game gameInstance){
        databases = databasesInstance;

        playerLeaderbord.add("Nobody");
        playerLeaderbord.add("Nobody");
        playerLeaderbord.add("Nobody");
        playerLeaderbord.add("Nobody");
        playerLeaderbord.add("Nobody"); // Initializes the array with dummy players
    }

    public ArrayList<String> getPlayerLeaderbord(){
        return playerLeaderbord;
    }

    public void updateLeaderbord(){
       newLeaderbord = databases.getLocalLeaderbord();
    }

}
