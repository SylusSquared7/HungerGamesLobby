package dev.sylus.hungergameslobby.game;

import dev.sylus.hungergameslobby.Files.Databases;

import java.util.ArrayList;

public class PositionManager {
    Databases databases;
    ArrayList<String> playerLeaderbord = new ArrayList<>(); // I need to make this store players rather than strings

    public PositionManager(Databases databasesInstance){
        databases = databasesInstance;

        playerLeaderbord.add("SylusSquared");
        playerLeaderbord.add("SylusSquared");
        playerLeaderbord.add("SylusSquared");
    }

    public ArrayList<String> getPlayerLeaderbord(){
        return playerLeaderbord;
    }

}
