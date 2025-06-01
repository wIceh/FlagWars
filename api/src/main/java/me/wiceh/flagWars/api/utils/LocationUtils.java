package me.wiceh.flagWars.api.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

import java.util.Objects;

public class LocationUtils {

    public static Location fromSection(ConfigurationSection section) {
        String world = section.getString("world");
        double x = section.getDouble("x");
        double y = section.getDouble("y");
        double z = section.getDouble("z");
        return new Location(Bukkit.getWorld(Objects.requireNonNull(world)), x, y, z);
    }
}
