package dev.sylus.hungergameslobby.commands;

import dev.sylus.hungergameslobby.game.Scorebord;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Level;

public class ScorebordTest implements CommandExecutor {
    Scorebord scorebord;

    public ScorebordTest(Scorebord scorebordInstance){
        scorebord = scorebordInstance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "Only players can use this command");
            Bukkit.getLogger().log(Level.WARNING, "Non player tried to execute a player only command");
            return true;
        }
        scorebord.createBoard(((Player) sender).getPlayer());
        return true;
    }
}
