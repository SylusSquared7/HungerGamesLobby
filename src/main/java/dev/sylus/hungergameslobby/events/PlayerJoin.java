package dev.sylus.hungergameslobby.events;

import dev.sylus.hungergameslobby.game.Game;
import dev.sylus.hungergameslobby.utils.Databases;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.logging.Level;

public class PlayerJoin implements Listener {
    Game game;
    Databases databases;
    public PlayerJoin(Game gameInstance, Databases databasesInstance){
        game = gameInstance;
        databases = databasesInstance;
    }

    @EventHandler
    public void OnPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        if (!(databases.isInDatabase(player.getUniqueId()))){
            databases.addNewPlayer(player.getUniqueId(), player.getName());
        }

        Bukkit.getLogger().log(Level.WARNING, "Trying to add player data to localData");
        if (!(databases.isPlayerInLocalData(player.getUniqueId()))){
            databases.addPlayerToLocalData(player.getUniqueId());
            Bukkit.getLogger().log(Level.SEVERE, String.valueOf(databases.getLocalPlayerData(player.getUniqueId()).getCurrentPoints()) + " This is from line 43 of JoinAndLeave");
        } else {
            Bukkit.getLogger().log(Level.WARNING, "Player already in the Local data system joined");
        }

        player.sendMessage(ChatColor.LIGHT_PURPLE + "Welcome to the server :)");
    }
}
