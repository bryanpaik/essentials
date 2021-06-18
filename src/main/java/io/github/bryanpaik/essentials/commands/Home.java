package io.github.bryanpaik.essentials.commands;

import io.github.bryanpaik.essentials.Essentials;
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

public class Home implements CommandExecutor, Listener {

    private static Map<Player, BukkitTask> tasks = new HashMap<>();

    Essentials plugin;

    public Home(Essentials plugin){
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;

            // if player does not have a bed
            if (player.getBedSpawnLocation() == null){
                player.sendMessage(ChatColor.RED + "You do not have a bed!");
                return true;
            }

            // initiates the teleport
            player.sendMessage(ChatColor.RED + "Do not move or teleport will be canceled!");

            if (!tasks.containsKey(player)){
                tasks.put(player, (new BukkitRunnable() {
                    @Override
                    public void run() {
                        player.sendMessage(ChatColor.GREEN + "Teleporting Player!");
                        player.teleport(player.getBedSpawnLocation());
                    }
                }).runTaskLater(plugin, 20L * 5));
            }
            return true;
        }
        else{
            sender.sendMessage(ChatColor.RED + "You are not a player!");
        }
        return false;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){
        Player p = e.getPlayer();

        if((e.getTo().getX() != e.getFrom().getX()) || (e.getTo().getY() != e.getFrom().getY()) || (e.getTo().getZ() != e.getFrom().getZ())){
            BukkitTask task = tasks.get(p);

            if(task != null){
                task.cancel();
                tasks.remove(p);
                p.sendMessage(ChatColor.RED + "Teleport Canceled");
            }

        }
    }
}
