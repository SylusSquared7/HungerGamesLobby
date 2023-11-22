package dev.sylus.hungergameslobby.commands;

import dev.sylus.hungergameslobby.enums.GameState;
import dev.sylus.hungergameslobby.game.Game;
import dev.sylus.hungergameslobby.game.Scorebord;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class SetState implements TabCompleter, CommandExecutor {
    Game game;
    Scorebord scorebord;
    public SetState(Game gameInstance, Scorebord scorebordInstance){
        game = gameInstance;
        scorebord = scorebordInstance;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            Bukkit.getLogger().log(Level.WARNING, "Non player tried to execute a player only command");
            return true;
        }

        if (args == null){
            sender.sendMessage(ChatColor.RED + "Please include an argument");
        }
        try {
            game.setState(GameState.valueOf(args[0]));
            sender.sendMessage(ChatColor.RED + "State set to: " + ChatColor.YELLOW + args[0]);
            scorebord.refreshScorebordAll();
        } catch (IllegalArgumentException exception){
            sender.sendMessage(ChatColor.RED + exception.toString());
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        List<String> suggestions = new ArrayList<>();
        // Add argument suggestions here
        if (args.length == 1) {
            suggestions.add(String.valueOf(GameState.PREGAME));
            suggestions.add(String.valueOf(GameState.GAMEONE));
            suggestions.add(String.valueOf(GameState.INTERMISSIONONE));
            suggestions.add(String.valueOf(GameState.GAMETWO));
            suggestions.add(String.valueOf(GameState.INTERMISSIONTWO));
            suggestions.add(String.valueOf(GameState.GAMETHREE));
            suggestions.add(String.valueOf(GameState.CREDITS));
        }
        return suggestions;
    }
}
