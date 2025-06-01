package me.wiceh.flagWars.server.listeners;

import me.wiceh.flagWars.FlagWars;
import org.bukkit.event.Listener;

public class GameListener implements Listener {
    private final FlagWars plugin;

    public GameListener(FlagWars plugin) {
        this.plugin = plugin;
    }
}
