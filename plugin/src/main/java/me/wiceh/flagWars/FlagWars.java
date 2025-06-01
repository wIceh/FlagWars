package me.wiceh.flagWars;

import me.wiceh.flagWars.api.FlagWarsAPI;
import me.wiceh.flagWars.api.managers.ArenaManager;
import me.wiceh.flagWars.managers.BaseArenaManager;
import me.wiceh.flagWars.server.commands.FlagWarsCommand;
import me.wiceh.flagWars.server.listeners.GameListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.ServicePriority;

public class FlagWars extends FlagWarsAPI {
    private final Plugin plugin;
    private final ArenaManager arenaManager;

    public FlagWars(Plugin plugin) {
        this.plugin = plugin;
        this.arenaManager = new BaseArenaManager(this);
    }

    public void enable() {
        Bukkit.getServicesManager().register(FlagWarsAPI.class, this, plugin, ServicePriority.Normal);
        plugin.saveDefaultConfig();

        // commands
        plugin.getServer().getPluginCommand("flagwars").setExecutor(new FlagWarsCommand(this));

        // listeners
        plugin.getServer().getPluginManager().registerEvents(new GameListener(this), plugin);
    }

    public void disable() {
    }

    public Plugin getPlugin() {
        return plugin;
    }

    @Override
    public ArenaManager getArenaManager() {
        return arenaManager;
    }
}
