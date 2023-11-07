package dev.sylus.hungergameslobby;

import dev.sylus.hungergameslobby.commands.HologramTest;
import org.bukkit.plugin.java.JavaPlugin;

public final class HungerGamesLobby extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("hologramTest").setExecutor(new HologramTest());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
