package dev.sylus.hungergameslobby.game;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.*;

public class Scorebord {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        createBoard(event.getPlayer());
    }

    public void createBoard(Player player) {
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = manager.getNewScoreboard();
        Objective obj = board.registerNewObjective("HungerGamesScorebord-1", "dummy", ChatColor.translateAlternateColorCodes('&', ChatColor.BOLD + "&6The Hunger Games"));
        // obj.setDisplayName("");

        Score score = obj.getScore(ChatColor.WHITE + "━━━━━━━━━━━━━━━━━━§7");
        score.setScore(10);

        Score score1 = obj.getScore("§bGame§f: " + "/§b3");
        score1.setScore(9);

        Score score2 = obj.getScore("§bPlayers alive§f: " + ChatColor.GREEN);
        score2.setScore(8);

        Score score3 = obj.getScore("§6Kills§f: ");
        score3.setScore(7);

        Score score4 = obj.getScore("§fNext event");
        score4.setScore(6);

        Score score5 = obj.getScore(ChatColor.RED + "test");
        score5.setScore(5);

        Score score6 = obj.getScore(ChatColor.WHITE + "━━━━━━━━━━━━━━━━━━");
        score6.setScore(4);

        Score score7 = obj.getScore("§aGames score§f: "); // Points gained during this game
        score7.setScore(3);

        Score score8 = obj.getScore("§aTotal score§f: "); // All points for all games
        score8.setScore(2);

        Score score9 = obj.getScore(ChatColor.WHITE + "━━━━━━━━━━━━━━━━━━§c");
        score9.setScore(1);

        Score score10 = obj.getScore(ChatColor.BLUE + "test");
        score10.setScore(0);

        player.setScoreboard(board);
        refreshScorebordAll();
    }

    public void refreshScorebordAll() {
        for (Player players : Bukkit.getOnlinePlayers()) {
            ScoreboardManager manager = Bukkit.getScoreboardManager();
            Scoreboard board = manager.getNewScoreboard();
            Objective obj = board.registerNewObjective("HungerGamesScorebord-1", "dummy", ChatColor.translateAlternateColorCodes('&', "&6The Hunger Games"));
            // obj.setDisplayName("");
            obj.setDisplaySlot(DisplaySlot.SIDEBAR);

            Score score = obj.getScore(ChatColor.WHITE + "━━━━━━━━━━━━━━━━━━━━━§7");
            score.setScore(10);

            Score score1 = obj.getScore("§bGame§f: " + "numberString" + "/§b3");
            score1.setScore(9);

            Score score2 = obj.getScore("§bPlayers alive§f: " + ChatColor.GREEN + "test");
            score2.setScore(8);

            Score score3 = obj.getScore("§6Kills§f: " + "test");
            score3.setScore(7);

            Score score4 = obj.getScore("§fNext event");
            score4.setScore(6);

            Score score5 = obj.getScore(ChatColor.RED + "test");
            score5.setScore(5);

            Score score6 = obj.getScore(ChatColor.WHITE + "━━━━━━━━━━━━━━━━━━━━━"); // This is not showing for some reason
            score6.setScore(4);

            Score score7 = obj.getScore("§aGames score§f: " + "test");
            score7.setScore(3);

            Score score8 = obj.getScore("§aTotal score§f: " + "test");
            score8.setScore(2);

            Score score9 = obj.getScore(ChatColor.WHITE + "━━━━━━━━━━━━━━━━━━━━━§c");
            score9.setScore(1);

            Score score10 = obj.getScore(ChatColor.BLUE + "serverCode");
            score10.setScore(0);

            players.setScoreboard(board);
        }
    }


}
