package io.github.bryanpaik.essentials;

import io.github.bryanpaik.essentials.commands.Home;
import io.github.bryanpaik.essentials.events.TeleportEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for the plugin includes start up and shut down logic.
 * @author Bryan Paik
 */
public final class Essentials extends JavaPlugin implements Listener {


    /**
     * Runs when plugin is started.
     */
    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Essentials is enabled!");
        this.getCommand("home").setExecutor(new Home(this));
        this.getServer().getPluginManager().registerEvents(new TeleportEvent(this),(this));
    }

    /**
     * Runs when plugin is shut down.
     */
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Essentials is disabled!");

    }
}
