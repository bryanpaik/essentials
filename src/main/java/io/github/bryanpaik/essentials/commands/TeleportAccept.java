package io.github.bryanpaik.essentials.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Class for teleport accept.
 * @author Bryan Paik
 */
public class TeleportAccept implements CommandExecutor {

    /**
     * Command to accept a teleport request.
     * @param sender is where the request came from
     * @param command is the request sent
     * @param label an optional alias
     * @param args is the additional arguments after the command
     * @return whether command was successful
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return true;
    }
}
