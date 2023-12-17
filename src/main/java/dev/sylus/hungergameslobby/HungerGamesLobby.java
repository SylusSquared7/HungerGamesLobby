package dev.sylus.hungergameslobby;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import dev.sylus.hungergameslobby.Files.Databases;
import dev.sylus.hungergameslobby.Files.Files;
import dev.sylus.hungergameslobby.commands.*;
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
    NpcUTIL npcUTIL;

    @Override
    public void onEnable() {
        Logging.log(Level.INFO, "Plugin started and is beginning initialising");
        // Plugin startup logic
        logging = new Logging();
        serverUtil = new ServerUtil(this);
        game = new Game();
        files = new Files(this, "worldData.yml");
        databases = new Databases(this, files);
        positionManager = new PositionManager(databases, game);
        hologram = new Hologram(databases, positionManager);
        scorebord = new Scorebord(game, files, positionManager, databases);
        npcUTIL = new NpcUTIL();


        getServer().getPluginManager().registerEvents(new PlayerJoin(game, databases, files, scorebord), this);
        getServer().getPluginManager().registerEvents(new TridentMachineGun(this), this);

        getCommand("hologramTest").setExecutor(new HologramTest(hologram)); // Test command please remove
        getCommand("giveMachineGun").setExecutor(new GiveMachineTrident());
        getCommand("sendPlayer").setExecutor(new SendPlayer());
        getCommand("setState").setExecutor(new SetState(game, scorebord));
        getCommand("updateLeaderboard").setExecutor(new UpdateLeaderbord(hologram, positionManager));
        getCommand("npcTest").setExecutor(new CreateNPC(npcUTIL));

        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);

        game.setState(GameState.PREGAME);
        Bukkit.getLogger().log(Level.INFO, "Lobby loaded");
        Logging.log(Level.INFO, "Finished initialised everything");

       /* try {
            File myObj = new File("filename.txt");
            if (myObj.createNewFile()) {
                Logging.log(Level.INFO, "File created: " + myObj.getName());
            } else {
            }
        } catch (IOException exception) {
            Logging.log(Level.SEVERE, "An error occurred while trying to create a file: " + exception);
            Bukkit.getLogger().log(Level.SEVERE, "An error occurred while trying to create a file: " + exception);
        }
        
        */
    }

    @Override
    public void onDisable() {
        Logging.log(Level.INFO, "Plugin disabled, de-initialising");
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
