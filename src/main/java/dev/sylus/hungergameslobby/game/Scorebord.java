package dev.sylus.hungergameslobby.game;

import dev.sylus.hungergameslobby.Files.Databases;
import dev.sylus.hungergameslobby.Files.Files;
import dev.sylus.hungergameslobby.utils.Logging;
import org.bukkit.Bukkit;
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
    ScoreboardManager manager;
    Scoreboard board;
    Objective obj;

    public Scorebord(Game gameInstance, Files filesInstance, PositionManager positionManagerInstance, Databases databasesInstance){
        game = gameInstance;
        files = filesInstance;
        positionManager = positionManagerInstance;
        databases = databasesInstance;
        gameNumber = 1; // Remember to change this
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!(databases.isInDatabase(event.getPlayer().getUniqueId()))){
            databases.addPlayerToLocalData(event.getPlayer().getUniqueId());
        }
        if (!(databases.isPlayerInLocalData(event.getPlayer().getUniqueId()))){
            databases.addPlayerToLocalData(event.getPlayer().getUniqueId());
        }
        createBoard(event.getPlayer());
    }

    public void createBoard(Player player) { // If im calling referesh scorebord anyway, then why don't I just use refresh scorebord anyway
        manager = Bukkit.getScoreboardManager();
        board = manager.getNewScoreboard();
        obj = board.registerNewObjective("HungerGamesScorebord-1", "dummy","§6§lThe Hunger Games");
        // obj.setDisplayName("");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score score7 = obj.getScore("§fNext map: " + nextMap);
        score7.setScore(7);

        Score score6 = obj.getScore("§fGame: §a" + gameNumber + "/3");
        score6.setScore(6);

        Score score5 = obj.getScore("§1"); // New line
        score5.setScore(5);

        if (positionManager.getPlayerLeaderbord().get(0).equals(player.getName())){
            Score score4 = obj.getScore("§6§kA§r§e1st §f" + positionManager.getPlayerLeaderbord().get(0) + " §a" + databases.getLocalPlayerData(Bukkit.getPlayer(positionManager.getPlayerLeaderbord().get(0)).getUniqueId()).getCurrentPoints() + "§6§kA");
            score4.setScore(4);
        } else {
            Score score4 = obj.getScore("§e1st §f" + positionManager.getPlayerLeaderbord().get(0) + " §a" + databases.getLocalPlayerData(Bukkit.getPlayer(positionManager.getPlayerLeaderbord().get(0)).getUniqueId()).getCurrentPoints());
            score4.setScore(4);
        }

        if (positionManager.getPlayerLeaderbord().get(1).equals(player.getName())){
            Score score3 = obj.getScore("§6§kA §r§e2nd §f" + positionManager.getPlayerLeaderbord().get(1) + " §a" + databases.getLocalPlayerData(Bukkit.getPlayer(positionManager.getPlayerLeaderbord().get(1)).getUniqueId()).getCurrentPoints() + "§6§kA");
            score3.setScore(3);
        } else {
            Score score3 = obj.getScore("§e2nd §f" + positionManager.getPlayerLeaderbord().get(1) + " §a" + databases.getLocalPlayerData(Bukkit.getPlayer(positionManager.getPlayerLeaderbord().get(1)).getUniqueId()).getCurrentPoints());
            score3.setScore(3);
        }

        if (positionManager.getPlayerLeaderbord().get(2).equals(player.getName())){
            Score score2 = obj.getScore("§6§kA§r§e3rd §f" + positionManager.getPlayerLeaderbord().get(2) + " §a" + databases.getLocalPlayerData(Bukkit.getPlayer(positionManager.getPlayerLeaderbord().get(2)).getUniqueId()).getCurrentPoints() + "§6§kA");
            score2.setScore(2);
        } else {
            Score score2 = obj.getScore("§e3rd §f" + positionManager.getPlayerLeaderbord().get(2) + " §a" + databases.getLocalPlayerData(Bukkit.getPlayer(positionManager.getPlayerLeaderbord().get(2)).getUniqueId()).getCurrentPoints());
            score2.setScore(2);  // Score2 is never set if this condition fails
        }
      //  if (!(positionManager.getPlayerLeaderbord().contains(player.getName()))){
            Score score1 = obj.getScore("§7.......");
            score1.setScore(1);

            Score score0 = obj.getScore("§e9th §f" + player.getName() + " §a" + databases.getLocalPlayerData(player.getUniqueId()).getCurrentPoints()); // Points gained during this game
            score0.setScore(0);
    //    }

        player.setScoreboard(board);
        refreshScorebordAll();
        Logging.log(Level.INFO, "Created the scoreboard");
    }

    public void refreshScorebordAll() {
        for (Player players : Bukkit.getOnlinePlayers()) {
            Score score7 = obj.getScore("§fNext map: " + nextMap);
            score7.setScore(7);

            Score score6 = obj.getScore("§fGame: §a" + gameNumber + "/3");
            score6.setScore(6);

            Score score5 = obj.getScore("§1"); // New line
            score5.setScore(5);

            if (positionManager.getPlayerLeaderbord().get(0).equals(players.getName())){
                Score score4 = obj.getScore("§6§kA§r§e1st §f" + positionManager.getPlayerLeaderbord().get(0) + " §a" + databases.getLocalPlayerData(Bukkit.getPlayer(positionManager.getPlayerLeaderbord().get(0)).getUniqueId()).getCurrentPoints() + "§6§kA");
                score4.setScore(4);
            } else {
                Score score4 = obj.getScore("§e1st §f" + positionManager.getPlayerLeaderbord().get(0) + " §a" + databases.getLocalPlayerData(Bukkit.getPlayer(positionManager.getPlayerLeaderbord().get(0)).getUniqueId()).getCurrentPoints());
                score4.setScore(4);
            }

            if (positionManager.getPlayerLeaderbord().get(1).equals(players.getName())){
                Score score3 = obj.getScore("§6§kA §r§e2nd §f" + positionManager.getPlayerLeaderbord().get(1) + " §a" + databases.getLocalPlayerData(Bukkit.getPlayer(positionManager.getPlayerLeaderbord().get(1)).getUniqueId()).getCurrentPoints() + "§6§kA");
                score3.setScore(3);
            } else {
                Score score3 = obj.getScore("§e2nd §f" + positionManager.getPlayerLeaderbord().get(1) + " §a" + databases.getLocalPlayerData(Bukkit.getPlayer(positionManager.getPlayerLeaderbord().get(1)).getUniqueId()).getCurrentPoints());
                score3.setScore(3);
            }

            if (positionManager.getPlayerLeaderbord().get(2).equals(players.getName())){
                Score score2 = obj.getScore("§6§kA§r§e3rd §f" + positionManager.getPlayerLeaderbord().get(2) + " §a" + databases.getLocalPlayerData(Bukkit.getPlayer(positionManager.getPlayerLeaderbord().get(2)).getUniqueId()).getCurrentPoints() + "§6§kA");
                score2.setScore(2);
            } else {
                Score score2 = obj.getScore("§e3rd §f" + positionManager.getPlayerLeaderbord().get(2) + " §a" + databases.getLocalPlayerData(Bukkit.getPlayer(positionManager.getPlayerLeaderbord().get(2)).getUniqueId()).getCurrentPoints());
                score2.setScore(2);  // Score2 is never set if this condition fails
            }
            //  if (!(positionManager.getPlayerLeaderbord().contains(player.getName()))){
            Score score1 = obj.getScore("§7.......");
            score1.setScore(1);

            Score score0 = obj.getScore("§e9th §f" + players.getName() + " §a" + databases.getLocalPlayerData(players.getUniqueId()).getCurrentPoints()); // Points gained during this game
            score0.setScore(0);
            //    }

            players.setScoreboard(board);
            refreshScorebordAll();
            Logging.log(Level.INFO, "Created the scoreboard");

            players.setScoreboard(board);
        }
    }
}
