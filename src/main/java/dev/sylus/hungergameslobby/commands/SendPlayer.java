package dev.sylus.hungergameslobby.commands;

import dev.sylus.hungergameslobby.utils.ServerUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SendPlayer implements CommandExecutor {
    public SendPlayer(){

    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (args.length < 1){
            sender.sendMessage(ChatColor.RED + "Incorrect usage. The correct useage is /sendplayer <PLAYERNAME or ALL> <SERVER NAME>");
        }
        if (args[1].isEmpty()){
            sender.sendMessage(ChatColor.RED + "Please include a server name");
        }

        if (args[0].equals("ALL")){
            for (Player players: Bukkit.getOnlinePlayers()){
               ServerUtil.sendPlayerToServer(players, args[1]);
            }
            return true;
        }
        String playerName = args[0];
        Player targetPlayer = Bukkit.getPlayer(playerName);

        if (targetPlayer == null){
            sender.sendMessage(ChatColor.RED + "Player not found or is not online");
            return true;
        }
        ServerUtil.sendPlayerToServer(targetPlayer, args[1]);
        return true;
    }
}
