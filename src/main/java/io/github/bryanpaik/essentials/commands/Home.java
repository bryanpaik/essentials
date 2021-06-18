package io.github.bryanpaik.essentials.commands;

import io.github.bryanpaik.essentials.Essentials;
import io.github.bryanpaik.essentials.events.TeleportEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;

public class Home implements CommandExecutor{

    /**
     * The main runner of this plugin.
     */
    private Essentials plugin;

    /**
     * Constructor for this class.
     * @param plugin is from main
     */
    public Home(Essentials plugin){
        this.plugin = plugin;
    }

    /**
     * Command to send player home.
     * @param sender is where the request came from
     * @param command is the request sent
     * @param label an optional alias
     * @param args is the additional arguments after the command
     * @return whether command was successful
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;

            // if player does not have a bed
            if (player.getBedSpawnLocation() == null){
                player.sendMessage(ChatColor.RED + "You do not have a bed!");
                return true;
            }

            TeleportEvent tp = new TeleportEvent(plugin);
            tp.teleportPlayer(player,player.getBedSpawnLocation());

            return true;
        }
        else{
            sender.sendMessage(ChatColor.RED + "You are not a player!");
        }
        return false;
    }

}
