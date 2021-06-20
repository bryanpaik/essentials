package io.github.bryanpaik.essentials.commands;

import io.github.bryanpaik.essentials.Essentials;
import io.github.bryanpaik.essentials.events.TeleportEvent;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Class for teleport accept.
 * @author Bryan Paik
 */
public class TeleportAccept implements CommandExecutor {

    /**
     * The plugin main.
     */
    private Essentials plugin;

    /**
     * Constructor.
     * @param plugin is the main runner
     */
    public TeleportAccept(Essentials plugin){
        this.plugin = plugin;
    }

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
        Player player = (Player) sender;
        ArrayList<UUID> names = plugin.getRequests(player.getUniqueId());
        if(names.size() == 0){
            player.sendMessage(ChatColor.RED + "You don't have any requests!");
            return true;
        }
        player.sendMessage(ChatColor.GREEN + "Request Accepted!");
        for(UUID name: names)
        {
            TeleportEvent tp = new TeleportEvent(plugin);
            tp.teleportPlayer(plugin.getServer().getPlayer(name),player.getLocation());
        }
        return true;
    }
}
