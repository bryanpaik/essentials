package io.github.bryanpaik.essentials.events;

import io.github.bryanpaik.essentials.Essentials;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnJoinEvent implements Listener {

    private Essentials plugin;
    public OnJoinEvent(Essentials plugin){
        this.plugin = plugin;
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        p.sendMessage(ChatColor.AQUA + "Welcome to the brain pike's server");
        p.sendMessage(ChatColor.AQUA + "There are " + plugin.getServer().getOnlinePlayers().size() + "players online");
    }

}
