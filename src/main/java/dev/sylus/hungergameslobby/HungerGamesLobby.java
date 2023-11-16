package dev.sylus.hungergameslobby;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import dev.sylus.hungergameslobby.Files.Databases;
import dev.sylus.hungergameslobby.Files.Files;
import dev.sylus.hungergameslobby.commands.GiveMachineTrident;
import dev.sylus.hungergameslobby.commands.HologramTest;
import dev.sylus.hungergameslobby.commands.ScorebordTest;
import dev.sylus.hungergameslobby.enums.GameState;
import dev.sylus.hungergameslobby.events.PlayerJoin;
import dev.sylus.hungergameslobby.events.TridentMachineGun;
import dev.sylus.hungergameslobby.game.Game;
import dev.sylus.hungergameslobby.game.Hologram;
import dev.sylus.hungergameslobby.game.PositionManager;
import dev.sylus.hungergameslobby.game.Scorebord;
import dev.sylus.hungergameslobby.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public final class HungerGamesLobby extends JavaPlugin implements PluginMessageListener {
    Databases databases;
    Files files;
    Hologram hologram;
    PositionManager positionManager;
    Scorebord scorebord;
    Game game;
    Logging logging;
    ServerUtil serverUtil;

    @Override
    public void onEnable() {
        Logging.log(Level.INFO, "Plugin started and beginning initialising");
        // Plugin startup logic
        logging = new Logging();
        serverUtil = new ServerUtil(this);
        files = new Files(this, "worldData.yml");
        databases = new Databases(this, files);
        game = new Game();
        positionManager = new PositionManager(databases);
        scorebord = new Scorebord(game, files, positionManager, databases);
        hologram = new Hologram();

        databases.initialiseDatabase();

        getServer().getPluginManager().registerEvents(scorebord, this);
        getServer().getPluginManager().registerEvents(new PlayerJoin(game, databases), this);
        getServer().getPluginManager().registerEvents(new TridentMachineGun(this), this);

        getCommand("hologramTest").setExecutor(new HologramTest(hologram));
        getCommand("scoreboardTest").setExecutor(new ScorebordTest(scorebord));
        getCommand("giveMachineGun").setExecutor(new GiveMachineTrident());

        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);

        game.setState(GameState.PREGAME);
        Bukkit.getLogger().log(Level.INFO, "Lobby loaded");
        Bukkit.getLogger().log(Level.INFO, "Game state changed to: " + game.getState());
        Logging.log(Level.INFO, "Started initialised everything");

        try {
            File myObj = new File("filename.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        for (Player players: Bukkit.getOnlinePlayers()){ // Updates all points gotten in the game to the database
            databases.addPointsToDB(players.getUniqueId()); // Updates the database with the local data
        }
        this.getServer().getMessenger().unregisterOutgoingPluginChannel(this);
        this.getServer().getMessenger().unregisterIncomingPluginChannel(this);
        databases.closeConnection();
        Logging.log(Level.INFO, "Plugin stopped, ending");
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("BungeeCord")) {
            return;
        }
        Logging.log(Level.INFO, "Received a message");
        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subchannel = in.readUTF();
        if (subchannel.equals("SomeSubChannel")) {
            // Use the code sample in the 'Response' sections below to read
            // the data.
        }
    }
}
