package dev.sylus.hungergameslobby.utils;

import com.google.common.collect.Iterables;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import dev.sylus.hungergameslobby.HungerGamesLobby;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ServerUtil {
   static HungerGamesLobby main;
    public ServerUtil(HungerGamesLobby mainInstance){
        main = mainInstance;
    }

    public static void sendPlayerToServer(Player player, String serverName){
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(serverName);
        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        // Player player = Iterables.getFirst(Bukkit.getOnlinePlayers(), null); to do it for all players
        player.sendPluginMessage(main, "BungeeCord", out.toByteArray());
    }
    @Deprecated
    public void sendAllPlayers(String serverName){ // DO NOT USE THIS
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(serverName);
        Player player = Iterables.getFirst(Bukkit.getOnlinePlayers(), null); // do it for all players
        player.sendPluginMessage(main, "BungeeCord", out.toByteArray());
    }
}
