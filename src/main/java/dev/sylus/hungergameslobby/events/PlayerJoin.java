package dev.sylus.hungergameslobby.events;

import dev.sylus.hungergameslobby.game.Game;
import dev.sylus.hungergameslobby.utils.Databases;
import dev.sylus.hungergameslobby.utils.Logging;
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

        if (!(databases.isPlayerInLocalData(player.getUniqueId()))){
            databases.addPlayerToLocalData(player.getUniqueId());
            player.sendMessage(ChatColor.LIGHT_PURPLE + "Welcome to the HungerGames \nThe first game will start soon \nPlease wait");
            Logging.log(Level.INFO, "Player: " + player.getName() + " with UUID: " + player.getUniqueId().toString() + " joined the server for the first time");
        } else {
            Logging.log(Level.INFO, "Player: " + player.getName() + " with UUID: " + player.getUniqueId().toString() + " joined");
        }

    }
}
