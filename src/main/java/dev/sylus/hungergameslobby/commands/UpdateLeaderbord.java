package dev.sylus.hungergameslobby.commands;

import dev.sylus.hungergameslobby.game.Hologram;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Level;

public class UpdateLeaderbord implements CommandExecutor {
    Hologram hologram;

    public UpdateLeaderbord(Hologram hologramInstance){
        hologram = hologramInstance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "Only players can use this command");
            Bukkit.getLogger().log(Level.WARNING, "Non player tried to execute a player only command");
            return true;
        }
        hologram.updateScores();
        sender.sendMessage(ChatColor.RED + "Updated the leaderboard");
        return true;
    }
}
