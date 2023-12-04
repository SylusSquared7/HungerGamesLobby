package dev.sylus.hungergameslobby.game;

import dev.sylus.hungergameslobby.Files.Databases;
import dev.sylus.hungergameslobby.Files.Files;
import dev.sylus.hungergameslobby.enums.GameState;
import dev.sylus.hungergameslobby.utils.Logging;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.*;

import java.util.logging.Level;

public class Scorebord implements Listener {
    String nextMap;
    Game game;
    PositionManager positionManager;
    Files files;
    Databases databases;
    int gameNumber;

    public Scorebord(Game gameInstance, Files filesInstance, PositionManager positionManagerInstance, Databases databasesInstance){
        game = gameInstance;
        files = filesInstance;
        positionManager = positionManagerInstance;
        databases = databasesInstance;
        gameNumber = 1; // Remember to change this
    }

    public void createBoard(Player player) { // If im calling referesh scorebord anyway, then why don't I just use refresh scorebord anyway
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = scoreboard.registerNewObjective("HungerGamesLobbyScoreboard-1", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName("§6§lThe Hunger Games");

        if (game.getState() == GameState.PREGAME){
            nextMap = "Breeze Island";
        } else if (game.getState() == GameState.GAMEONE || game.getState() == GameState.INTERMISSIONONE) {
            nextMap = "Survival Games 4";
        } else if (game.getState() == GameState.GAMETWO || game.getState() == GameState.INTERMISSIONTWO) {
            nextMap = "Chernobyl 2015";
        } else {
            nextMap = "Game end";
        }

        Team team7 = scoreboard.registerNewTeam("team7");
        team7.addEntry(ChatColor.GOLD.toString() + "" + ChatColor.WHITE.toString());
        team7.setPrefix(nextMap);
        obj.getScore(ChatColor.GOLD + "" + ChatColor.WHITE).setScore(7);

        Team team6 = scoreboard.registerNewTeam("team6");
        team6.addEntry(ChatColor.RED.toString() + "" + ChatColor.WHITE.toString());
        team6.setPrefix("§fGame: §a" + gameNumber + "/3");
        obj.getScore(ChatColor.RED.toString() + "" + ChatColor.WHITE).setScore(6);

        Score score5 = obj.getScore("§1"); // New line
        score5.setScore(5);

        Team team4 = scoreboard.registerNewTeam("team4");
        team4.addEntry(ChatColor.DARK_PURPLE.toString() + "" + ChatColor.WHITE.toString());

        if (positionManager.getPlayerLeaderbord().get(0).equals(player.getName())){
            team4.setPrefix("§6§kA§r§e1st §f" + positionManager.getPlayerLeaderbord().get(0) + " §a" + databases.getLocalPlayerData(Bukkit.getPlayer(positionManager.getPlayerLeaderbord().get(0)).getUniqueId()).getCurrentPoints() + "§6§kA");
            obj.getScore(ChatColor.DARK_PURPLE.toString() + "" + ChatColor.WHITE).setScore(4);
        } else if (positionManager.getPlayerLeaderbord().get(0).equals("Nobody")) {
            team4.setPrefix("§e1st §f" + positionManager.getPlayerLeaderbord().get(0) + " §a0");
            obj.getScore(ChatColor.DARK_PURPLE.toString() + "" + ChatColor.WHITE).setScore(4);
        } else {
            team4.setPrefix("§e1st §f" + positionManager.getPlayerLeaderbord().get(0) + " §a" + databases.getLocalPlayerData(Bukkit.getPlayer(positionManager.getPlayerLeaderbord().get(0)).getUniqueId()).getCurrentPoints());
            obj.getScore(ChatColor.DARK_PURPLE.toString() + "" + ChatColor.WHITE).setScore(4);
        }

        Team team3 = scoreboard.registerNewTeam("team3");
        team3.addEntry(ChatColor.DARK_PURPLE.toString() + "" + ChatColor.RED.toString());

        if (positionManager.getPlayerLeaderbord().get(0).equals(player.getName())){
            team4.setPrefix("§6§kA§r§e2nd §f" + positionManager.getPlayerLeaderbord().get(0) + " §a" + databases.getLocalPlayerData(Bukkit.getPlayer(positionManager.getPlayerLeaderbord().get(0)).getUniqueId()).getCurrentPoints() + "§6§kA");
            obj.getScore(ChatColor.DARK_PURPLE.toString() + "" + ChatColor.RED).setScore(3);
        } else if (positionManager.getPlayerLeaderbord().get(0).equals("Nobody")) {
            team4.setPrefix("§e2nd §f" + positionManager.getPlayerLeaderbord().get(0) + " §a0");
            obj.getScore(ChatColor.DARK_PURPLE.toString() + "" + ChatColor.RED).setScore(3);
        } else {
            team4.setPrefix("§e2nd §f" + positionManager.getPlayerLeaderbord().get(0) + " §a" + databases.getLocalPlayerData(Bukkit.getPlayer(positionManager.getPlayerLeaderbord().get(0)).getUniqueId()).getCurrentPoints());
            obj.getScore(ChatColor.DARK_PURPLE.toString() + "" + ChatColor.RED).setScore(3);
        }

        Team team2 = scoreboard.registerNewTeam("team2");
        team2.addEntry(ChatColor.GREEN.toString().toString() + "" + ChatColor.RED.toString());

        if (positionManager.getPlayerLeaderbord().get(0).equals(player.getName())){
            team4.setPrefix("§6§kA§r§e3rd §f" + positionManager.getPlayerLeaderbord().get(0) + " §a" + databases.getLocalPlayerData(Bukkit.getPlayer(positionManager.getPlayerLeaderbord().get(0)).getUniqueId()).getCurrentPoints() + "§6§kA");
            obj.getScore(ChatColor.GREEN.toString() + "" + ChatColor.RED).setScore(2);
        } else if (positionManager.getPlayerLeaderbord().get(0).equals("Nobody")) {
            team4.setPrefix("§e3rd §f" + positionManager.getPlayerLeaderbord().get(0) + " §a0");
            obj.getScore(ChatColor.GREEN.toString() + "" + ChatColor.RED).setScore(2);
        } else {
            team4.setPrefix("§e3rd §f" + positionManager.getPlayerLeaderbord().get(0) + " §a" + databases.getLocalPlayerData(Bukkit.getPlayer(positionManager.getPlayerLeaderbord().get(0)).getUniqueId()).getCurrentPoints());
            obj.getScore(ChatColor.GREEN.toString() + "" + ChatColor.RED).setScore(2);
        }

        Score score1 = obj.getScore("§7.......");
        score1.setScore(1);

        Team team0 = scoreboard.registerNewTeam("team9");
        team0.addEntry(ChatColor.GOLD.toString() + "" + ChatColor.GRAY.toString());
        team0.setPrefix("§e4th §f" + player.getName() + " §a" + databases.getLocalPlayerData(player.getUniqueId()).getCurrentPoints());
        obj.getScore(ChatColor.GOLD + "" + ChatColor.WHITE).setScore(0);

        player.setScoreboard(scoreboard);
        refreshScorebordAll();
        Logging.log(Level.INFO, "Created the scoreboard");
    }

    public void refreshScorebordAll() {
        if (game.getState() == GameState.PREGAME){
            nextMap = "Breeze Island";
        } else if (game.getState() == GameState.GAMEONE || game.getState() == GameState.INTERMISSIONONE) {
            nextMap = "Survival Games 4";
        } else if (game.getState() == GameState.GAMETWO || game.getState() == GameState.INTERMISSIONTWO) {
            nextMap = "Chernobyl 2015";
        } else {
            nextMap = "Game end";
        }

        for (Player players : Bukkit.getOnlinePlayers()) {
            Scoreboard scoreboard = players.getScoreboard();

            if (game.getState() == GameState.PREGAME){
                nextMap = "Breeze Island";
            } else if (game.getState() == GameState.GAMEONE || game.getState() == GameState.INTERMISSIONONE) {
                nextMap = "Survival Games 4";
            } else if (game.getState() == GameState.GAMETWO || game.getState() == GameState.INTERMISSIONTWO) {
                nextMap = "Chernobyl 2015";
            } else {
                nextMap = "Game end";
            }

            Team team7 = scoreboard.getTeam("team7");
            team7.setPrefix(nextMap);

            Team team6 = scoreboard.getTeam("team6");
            team6.setPrefix("§fGame: §a" + gameNumber + "/3");

            Team team4 = scoreboard.getTeam("team4");
            if (positionManager.getPlayerLeaderbord().get(0).equals(players.getName())){
                team4.setPrefix("§6§kA§r§e1st §f" + positionManager.getPlayerLeaderbord().get(0) + " §a" + databases.getLocalPlayerData(Bukkit.getPlayer(positionManager.getPlayerLeaderbord().get(0)).getUniqueId()).getCurrentPoints() + "§6§kA");
            } else if (positionManager.getPlayerLeaderbord().get(0).equals("Nobody")) {
                team4.setPrefix("§e1st §f" + positionManager.getPlayerLeaderbord().get(0) + " §a0");
            } else {
                team4.setPrefix("§e1st §f" + positionManager.getPlayerLeaderbord().get(0) + " §a" + databases.getLocalPlayerData(Bukkit.getPlayer(positionManager.getPlayerLeaderbord().get(0)).getUniqueId()).getCurrentPoints());
            }

            Team team3 = scoreboard.registerNewTeam("team3");
            if (positionManager.getPlayerLeaderbord().get(0).equals(players.getName())){
                team4.setPrefix("§6§kA§r§e2nd §f" + positionManager.getPlayerLeaderbord().get(0) + " §a" + databases.getLocalPlayerData(Bukkit.getPlayer(positionManager.getPlayerLeaderbord().get(0)).getUniqueId()).getCurrentPoints() + "§6§kA");
            } else if (positionManager.getPlayerLeaderbord().get(0).equals("Nobody")) {
                team4.setPrefix("§e2nd §f" + positionManager.getPlayerLeaderbord().get(0) + " §a0");
            } else {
                team4.setPrefix("§e2nd §f" + positionManager.getPlayerLeaderbord().get(0) + " §a" + databases.getLocalPlayerData(Bukkit.getPlayer(positionManager.getPlayerLeaderbord().get(0)).getUniqueId()).getCurrentPoints());
            }

            Team team2 = scoreboard.registerNewTeam("team2");
            if (positionManager.getPlayerLeaderbord().get(0).equals(players.getName())){
                team4.setPrefix("§6§kA§r§e3rd §f" + positionManager.getPlayerLeaderbord().get(0) + " §a" + databases.getLocalPlayerData(Bukkit.getPlayer(positionManager.getPlayerLeaderbord().get(0)).getUniqueId()).getCurrentPoints() + "§6§kA");
            } else if (positionManager.getPlayerLeaderbord().get(0).equals("Nobody")) {
                team4.setPrefix("§e3rd §f" + positionManager.getPlayerLeaderbord().get(0) + " §a0");
            } else {
                team4.setPrefix("§e3rd §f" + positionManager.getPlayerLeaderbord().get(0) + " §a" + databases.getLocalPlayerData(Bukkit.getPlayer(positionManager.getPlayerLeaderbord().get(0)).getUniqueId()).getCurrentPoints());
            }

            Team team0 = scoreboard.registerNewTeam("team9");
            team0.addEntry(ChatColor.GOLD.toString() + "" + ChatColor.GRAY.toString());
            team0.setPrefix("§e4th §f" + players.getName() + " §a" + databases.getLocalPlayerData(players.getUniqueId()).getCurrentPoints());
        }
        Logging.log(Level.INFO, "Updated the scoreboard for all players");
    }
}
