package io.github.bryanpaik.essentials.commands;

import io.github.bryanpaik.essentials.Essentials;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

/**
 * Class for teleport requests.
 * @author Bryan Paik
 */
public class TeleportRequest implements CommandExecutor {

    /**
     * The plugin main.
     */
    private Essentials plugin;

    /**
     * Constructor.
     * @param plugin is the main runner
     */
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

                // message that request has been sent
                if(playerRecipent == null){
                    playerSender.sendMessage(ChatColor.RED + "That player is not online!");
                    return true;
                }
                // if player has already sent out a request
                else if(plugin.reqExists(playerSender.getUniqueId())){
                    playerSender.sendMessage(ChatColor.RED + "You already have an existing request!");
                    return true;
                }
                else if(playerSender.getUniqueId().equals(playerRecipent.getUniqueId())){
                    playerSender.sendMessage(ChatColor.RED + "You cannot send a request to yourself!");
                    return true;
                }
                // successful request
                else{
                    playerSender.sendMessage(ChatColor.GREEN + "Request sent!");
                }

                // store destination name in set (if that name exists)
                playerRecipent.sendMessage(ChatColor.GREEN + "You have been sent a tp request from " + playerSender.getDisplayName());
                playerRecipent.sendMessage(ChatColor.GREEN + "Please type /tpaccept to accept request");
                playerRecipent.sendMessage(ChatColor.RED + "Request will expire in 10 seconds");

                // add request to list
                plugin.addReq(playerSender.getUniqueId(),playerRecipent.getUniqueId());

                // Remove player from teleport request list if past 10 seconds
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        plugin.removeReq(playerSender.getUniqueId());
                    }
                }.runTaskLater(plugin, 20L * 10);




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
