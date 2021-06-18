package io.github.bryanpaik.essentials.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Class for teleport requests.
 * @author Bryan Paik
 */
public class TeleportRequest implements CommandExecutor {

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

        }
        else{
            sender.sendMessage(ChatColor.RED + "You are not a player!");
        }
        return true;
    }
}
