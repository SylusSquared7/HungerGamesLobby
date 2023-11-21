package dev.sylus.hungergameslobby.commands;

import dev.sylus.hungergameslobby.Files.Databases;
import dev.sylus.hungergameslobby.game.Scorebord;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Level;

public class ResetPoints implements CommandExecutor {
    Databases databases;
    Scorebord scorebord;
    public ResetPoints(Databases databasesInstance, Scorebord scoreboardInstance){
        databases = databasesInstance;
        scorebord = scoreboardInstance;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (args.length < 1){
            sender.sendMessage(ChatColor.RED + "Incorrect use of the command");
            Bukkit.getLogger().log(Level.SEVERE, "Warning, resetpoints command used incorectley");
        }
        if (args[0].equals("ALL")){
            for (Player players: Bukkit.getOnlinePlayers()){
                databases.resetPoints(players.getUniqueId());
            }
            Bukkit.getLogger().log(Level.WARNING, "Reset points for all players");
            scorebord.refreshScorebordAll();
            return true;
        }
        String playerName = args[0];
        Player targetPlayer = Bukkit.getPlayer(playerName);

        if (targetPlayer == null){
            sender.sendMessage(ChatColor.RED + "Player not found or is not online");
            scorebord.refreshScorebordAll();
            return true;
        }
        if (!(databases.isInDatabase(targetPlayer.getUniqueId()))){
            sender.sendMessage(ChatColor.RED + "Player is not in the database");
            scorebord.refreshScorebordAll();
            return true;
        }
        databases.resetPoints(targetPlayer.getUniqueId());
        Bukkit.getLogger().log(Level.WARNING, "Reset points for: " + playerName);
        scorebord.refreshScorebordAll();
        return true;
    }
}
