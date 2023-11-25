package dev.sylus.hungergameslobby.events;

import dev.sylus.hungergameslobby.Files.Files;
import dev.sylus.hungergameslobby.game.Game;
import dev.sylus.hungergameslobby.Files.Databases;
import dev.sylus.hungergameslobby.game.Scorebord;
import dev.sylus.hungergameslobby.utils.Logging;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.logging.Level;

public class PlayerJoin implements Listener {
    Game game;
    Databases databases;
    Files files;
    Scorebord scorebord;
    public PlayerJoin(Game gameInstance, Databases databasesInstance, Files filesInstance, Scorebord scorebordInstance){
        game = gameInstance;
        databases = databasesInstance;
        files = filesInstance;
        scorebord = scorebordInstance;
    }

    @EventHandler
    public void OnPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        if (!(databases.isInDatabase(player.getUniqueId()))){
            databases.addNewPlayer(player.getUniqueId(), player.getName());
        }

        if (!(databases.isPlayerInLocalData(player.getUniqueId()))){
            databases.addPlayerToLocalData(player.getUniqueId(), player.getName());
            player.sendMessage(ChatColor.LIGHT_PURPLE + "Welcome to the HungerGames \nThe first game will start soon \nPlease wait");
            Logging.log(Level.INFO, "Player: " + player.getName() + " with UUID: " + player.getUniqueId().toString() + " joined the server for the first time");
        } else {
            Logging.log(Level.INFO, "Player: " + player.getName() + " with UUID: " + player.getUniqueId().toString() + " joined");
        }

        scorebord.createBoard(player);

        double x = files.getConfig("worldData").getInt("worldData.spawnLocationX");
        double y = files.getConfig("worldData").getInt("worldData.spawnLocationY");
        double z = files.getConfig("worldData").getInt("worldData.spawnLocationZ");
        Location location = new Location(Bukkit.getWorld("world"), x, y, z);
        player.teleport(location);

    }
}
