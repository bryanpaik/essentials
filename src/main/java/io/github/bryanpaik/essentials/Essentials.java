package io.github.bryanpaik.essentials;

import io.github.bryanpaik.essentials.commands.Home;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Essentials extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Essentials is enabled!");
        this.getCommand("home").setExecutor(new Home(this));
        this.getServer().getPluginManager().registerEvents(new Home(this),(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Essentials is disabled!");

    }
}
