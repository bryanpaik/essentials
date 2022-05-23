package io.github.bryanpaik.essentials.events;

import io.github.bryanpaik.essentials.Essentials;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnSleepEvent implements Listener {
    private static int totalSleep = 0;
    private Essentials plugin;
    public OnSleepEvent(Essentials plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onSleep(PlayerBedEnterEvent e){
        this.totalSleep++;
        int minSleep = plugin.getServer().getOnlinePlayers().size()/2;
        if(minSleep == totalSleep){
            plugin.getServer().getWorld("world").setTime(0);
        }
    }

    @EventHandler
    public void onLeaveSleep(PlayerBedLeaveEvent e){
        this.totalSleep--;
    }
}
