package dev.sylus.hungergameslobby.commands;

import dev.sylus.hungergameslobby.utils.Hologram;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.logging.Level;

public class HologramTest implements CommandExecutor {
    Hologram hologram;

    public HologramTest(Hologram hologramInstance){
        hologram = hologramInstance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "Only players can use this command");
            Bukkit.getLogger().log(Level.WARNING, "Non player tried to execute a player only command");
            return true;
        }
        Player player = (Player) sender;
        hologram.initialiseLeaderbord(player.getLocation());
        return true;
    }
}
