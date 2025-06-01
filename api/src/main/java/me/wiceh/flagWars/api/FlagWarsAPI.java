package me.wiceh.flagWars.api;

import me.wiceh.flagWars.api.managers.ArenaManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

public abstract class FlagWarsAPI {
    public static FlagWarsAPI getInstance() {
        RegisteredServiceProvider<FlagWarsAPI> registration = Bukkit.getServicesManager().getRegistration(FlagWarsAPI.class);
        if (registration == null) return null;
        return registration.getProvider();
    }

    public abstract ArenaManager getArenaManager();
}
