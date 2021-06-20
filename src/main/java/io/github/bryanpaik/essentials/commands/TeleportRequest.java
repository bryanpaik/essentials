package io.github.bryanpaik.essentials.commands;

import io.github.bryanpaik.essentials.Essentials;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Class for teleport requests.
 * @author Bryan Paik
 */
public class TeleportRequest implements CommandExecutor {

    private Essentials plugin;
    public TeleportRequest(Essentials plugin){
        this.plugin = plugin;
    }
    /**
     * Command to send a teleport request.
     * @param sender is where the request came from
     * @param command is the request sent
     * @param label an optional alias
     * @param args is the additional arguments after the command
     * @return whether command was successful
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            if(args.length == 1){
                Player playerSender = (Player) sender;
                Player playerRecipent = plugin.getServer().getPlayer(args[0]);


            }
            else{
                sender.sendMessage(ChatColor.RED + "Invalid Arguments, please enter a player name");
            }
        }
        else{
            sender.sendMessage(ChatColor.RED + "You are not a player!");
        }
        return true;
    }
}
