package dev.sylus.hungergameslobby;

import dev.sylus.hungergameslobby.commands.HologramTest;
import dev.sylus.hungergameslobby.utils.Databases;
import dev.sylus.hungergameslobby.utils.Files;
import dev.sylus.hungergameslobby.utils.Hologram;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class HungerGamesLobby extends JavaPlugin {
    Databases databases;
    Files files;
    Hologram hologram;

    @Override
    public void onEnable() {
        // Plugin startup logic
        files = new Files(this, "worldData.yml");
        databases = new Databases(this, files);
        hologram = new Hologram();


        getCommand("hologramTest").setExecutor(new HologramTest(hologram));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        databases.closeConnection();
        for (Player players: Bukkit.getOnlinePlayers()){ // Updates all points gotten in the game to the database
            databases.addPointsToDB(players.getUniqueId()); // Updates the database with the local data
        }
        this.getServer().getMessenger().unregisterOutgoingPluginChannel(this);
        this.getServer().getMessenger().unregisterIncomingPluginChannel(this);
    }
}
