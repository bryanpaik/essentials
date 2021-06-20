package io.github.bryanpaik.essentials.events;

import io.github.bryanpaik.essentials.Essentials;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for all teleport events.
 * @author Bryan Paik
 */
public class TeleportEvent implements Listener {

    /**
     * Stores all tasks in a static map.
     */
    private static Map<Player, BukkitTask> tasks = new HashMap<>();

    /**
     * Main runner for this plugin.
     */
    private Essentials plugin;

    /**
     * Constructor for this class.
     * @param plugin is from main.
     */
    public TeleportEvent(Essentials plugin){
        this.plugin = plugin;
    }

    /**
     * Does the actual action of teleporting the player.
     * Also, includes a delay and cancels teleport when player moves
     * @param player is the player being teleported
     * @param dest is the destination of the teleport
     */
    public void teleportPlayer(Player player, Location dest){
        // initiates the teleport
        player.sendMessage(ChatColor.RED + "Do not move or teleport will be canceled!");

        if (!tasks.containsKey(player)){
            tasks.put(player, (new BukkitRunnable() {
                @Override
                public void run() {
                    player.sendMessage(ChatColor.GREEN + "Teleporting Player!");
                    tasks.remove(player);
                    player.teleport(dest);
                }
            }).runTaskLater(plugin, 20L * 5));
        }
    }

    /**
     * Catches any movement from the player and cancels the task when moving.
     * @param e is the actual event
     */
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){
        Player p = e.getPlayer();

        if((e.getTo().getBlockX() != e.getFrom().getBlockX()) || (e.getTo().getBlockY() != e.getFrom().getBlockY()) || (e.getTo().getBlockZ() != e.getFrom().getBlockZ())){
            BukkitTask task = tasks.get(p);

            if(task != null){
                task.cancel();
                tasks.remove(p);
                p.sendMessage(ChatColor.RED + "Teleport Canceled");
            }

        }
    }
}
