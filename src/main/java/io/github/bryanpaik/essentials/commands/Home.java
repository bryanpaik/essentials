package io.github.bryanpaik.essentials.commands;

import io.github.bryanpaik.essentials.Essentials;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Home implements CommandExecutor {
    Essentials plugin;
    public Home(Essentials plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            Location startLoc = player.getLocation();
            for(int i = 0; i < 5; i++){
                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                    @Override
                    public void run() {
                        sender.sendMessage("Test!");
                    }

                }, 20 * i); // 1 Second delay
            }

        }
        else{
            sender.sendMessage(ChatColor.RED + "You are not a player!");
        }
        return false;
    }

}
