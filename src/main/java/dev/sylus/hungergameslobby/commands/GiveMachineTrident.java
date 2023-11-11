package dev.sylus.hungergameslobby.commands;

import dev.sylus.hungergameslobby.game.Scorebord;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class GiveMachineTrident implements CommandExecutor {

    public GiveMachineTrident(){

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage(ChatColor.RED + "Only players can use this command");
            Bukkit.getLogger().log(Level.WARNING, "Non player tried to execute a player only command");
            return true;
        }
        ItemStack trident = new ItemStack(Material.TRIDENT);
        ItemMeta meta = trident.getItemMeta();
        meta.setDisplayName("§9MACHINE GUNDENT");
        List<String> lore = new ArrayList<>();
        lore.add("§9Machine gun trident");
        lore.add("§eHOLD RIGHT CLICK§f: shoots a LOT of tridents");
        lore.add("§cADMIN WEAPON");
        meta.setLore(lore);
        ((Player) sender).getInventory().addItem(trident);
        return true;
    }
}
