package io.github.bryanpaik.essentials;

import io.github.bryanpaik.essentials.commands.Home;
import io.github.bryanpaik.essentials.commands.TeleportAccept;
import io.github.bryanpaik.essentials.commands.TeleportRequest;
import io.github.bryanpaik.essentials.events.OnJoinEvent;
import io.github.bryanpaik.essentials.events.TeleportEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

/**
 * Class for the plugin includes start up and shut down logic.
 * @author Bryan Paik
 */
public final class Essentials extends JavaPlugin implements Listener {

    /**
     * TPA Requests are stored in this set
     */
    private HashMap<UUID,UUID> tpReq = new HashMap<UUID,UUID>();

    /**
     * Runs when plugin is started.
     */
    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Essentials is enabled!");
        this.getCommand("home").setExecutor(new Home(this));
        this.getCommand("tpa").setExecutor(new TeleportRequest(this));
        this.getCommand("tpaccept").setExecutor(new TeleportAccept(this));
        this.getServer().getPluginManager().registerEvents(new TeleportEvent(this),(this));
        this.getServer().getPluginManager().registerEvents(new OnJoinEvent(this),(this));
    }

    /**
     * Runs when plugin is shut down.
     */
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Essentials is disabled!");

    }

    public void addReq(UUID sender, UUID recip){
        tpReq.put(sender,recip);
    }

    public ArrayList<UUID> getRequests(UUID recip){
        ArrayList<UUID> names = new ArrayList<>();
        for(UUID name: tpReq.keySet()){
            if(tpReq.get(name) == recip)
             names.add(name);
        }
        return names;
    }

    public void removeReq(UUID player){
        tpReq.remove(player);
    }

    public boolean reqExists(UUID player){
        if(tpReq.containsValue(player)){
            return true;
        }
        return false;
    }

}
