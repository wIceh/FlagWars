package me.wiceh.flagWars;

import me.wiceh.flagWars.api.FlagWarsAPI;
import org.bukkit.plugin.Plugin;

public class FlagWars extends FlagWarsAPI {
    private final Plugin plugin;

    public FlagWars(Plugin plugin) {
        this.plugin = plugin;
    }

    public void enable() {

    }

    public void disable() {

    }

    public Plugin getPlugin() {
        return plugin;
    }
}
