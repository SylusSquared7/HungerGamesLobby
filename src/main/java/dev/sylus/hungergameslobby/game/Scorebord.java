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

    public void createBoard(Player player) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective obj = board.registerNewObjective("HungerGamesScorebord-1", "dummy","§6§lThe Hunger Games");
        // obj.setDisplayName("");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score score = obj.getScore("§fNext map: " + nextMap);
        score.setScore(7);

        Score score1 = obj.getScore("§fGame: §a" + gameNumber + "/3");
        score1.setScore(6);

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
            Score score3 = obj.getScore("§e3rd §f" + positionManager.getPlayerLeaderbord().get(2) + " §a" + databases.getLocalPlayerData(Bukkit.getPlayer(positionManager.getPlayerLeaderbord().get(2)).getUniqueId()).getCurrentPoints());
            score3.setScore(2);  // Score2 is never set if this condition fails
        }
      //  if (!(positionManager.getPlayerLeaderbord().contains(player.getName()))){
            Score score6 = obj.getScore("§7.......");
            score6.setScore(1);

            Score score7 = obj.getScore("§e9th §f" + player.getName() + " §a" + databases.getLocalPlayerData(player.getUniqueId()).getCurrentPoints()); // Points gained during this game
            score7.setScore(0);
    //    }

        player.setScoreboard(board);
        refreshScorebordAll();
        Logging.log(Level.INFO, "Created the scoreboard");
    }

    public void refreshScorebordAll() {
        for (Player players : Bukkit.getOnlinePlayers()) {
            ScoreboardManager manager = Bukkit.getScoreboardManager();
            Scoreboard board = manager.getNewScoreboard();
            Objective obj = board.registerNewObjective("HungerGamesScorebord-1", "dummy","§6§lThe Hunger Games");
            // obj.setDisplayName("");
            obj.setDisplaySlot(DisplaySlot.SIDEBAR);

            Score score = obj.getScore("§fNext map: " + nextMap);
            score.setScore(7);

            Score score1 = obj.getScore("§fGame: §a" + gameNumber + "/3");
            score1.setScore(6);

            Score score5 = obj.getScore("§1"); // New line
            score5.setScore(5);

            if (positionManager.getPlayerLeaderbord().get(0).equals(players.getName())){
                Score score4 = obj.getScore("§6§kA" +"§e1st §f" + positionManager.getPlayerLeaderbord().get(0) + " §a" + databases.getLocalPlayerData(Bukkit.getPlayer(positionManager.getPlayerLeaderbord().get(0)).getUniqueId()).getCurrentPoints() + "§6§kA");
                score4.setScore(4);
            } else {
                Score score4 = obj.getScore("§e1st §f" + positionManager.getPlayerLeaderbord().get(0) + " §a" + databases.getLocalPlayerData(Bukkit.getPlayer(positionManager.getPlayerLeaderbord().get(0)).getUniqueId()).getCurrentPoints());
                score4.setScore(4);
            }

            if (positionManager.getPlayerLeaderbord().get(1).equals(players.getName())){
                Score score3 = obj.getScore("§6§kA" +"§e2nd §f" + positionManager.getPlayerLeaderbord().get(1) + " §a" + databases.getLocalPlayerData(Bukkit.getPlayer(positionManager.getPlayerLeaderbord().get(1)).getUniqueId()).getCurrentPoints() + "§6§kA");
                score3.setScore(3);
            } else {
                Score score3 = obj.getScore("§e2nd §f" + positionManager.getPlayerLeaderbord().get(1) + " §a" + databases.getLocalPlayerData(Bukkit.getPlayer(positionManager.getPlayerLeaderbord().get(1)).getUniqueId()).getCurrentPoints());
                score3.setScore(3);
            }

            if (positionManager.getPlayerLeaderbord().get(2).equals(players.getName())){
                Score score2 = obj.getScore("§6§kA" + "§e3rd §f" + positionManager.getPlayerLeaderbord().get(2) + " §a" + databases.getLocalPlayerData(Bukkit.getPlayer(positionManager.getPlayerLeaderbord().get(2)).getUniqueId()).getCurrentPoints() + "§6§kA");
                score2.setScore(2);
            } else {
                Score score3 = obj.getScore("§e3rd §f" + positionManager.getPlayerLeaderbord().get(2) + " §a" + databases.getLocalPlayerData(Bukkit.getPlayer(positionManager.getPlayerLeaderbord().get(2)).getUniqueId()).getCurrentPoints());
                score3.setScore(2);
            }
            if (!(positionManager.getPlayerLeaderbord().contains(players.getName()))){
                Score score6 = obj.getScore("§7.......");
                score6.setScore(1);

                Score score7 = obj.getScore("§e9th §f" + players.getName() + " §a" + databases.getLocalPlayerData(players.getUniqueId()).getCurrentPoints()); // Points gained during this game
                score7.setScore(0);
            }

            players.setScoreboard(board);
        }
    }
}
