package me.wiceh.flagWars.server;

import me.wiceh.flagWars.FlagWars;
import org.bukkit.plugin.java.JavaPlugin;

public final class FlagWarsLoader extends JavaPlugin {
    private final FlagWars flagWars;

    public FlagWarsLoader() {
        this.flagWars = new FlagWars(this);
    }

    @Override
    public void onEnable() {
        this.flagWars.enable();
    }

    @Override
    public void onDisable() {
        this.flagWars.disable();
    }
}
