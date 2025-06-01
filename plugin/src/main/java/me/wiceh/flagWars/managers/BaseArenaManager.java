package me.wiceh.flagWars.managers;

import me.wiceh.flagWars.FlagWars;
import me.wiceh.flagWars.api.managers.ArenaManager;
import me.wiceh.flagWars.api.objects.Arena;
import me.wiceh.flagWars.api.utils.LocationUtils;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

public class BaseArenaManager extends ArenaManager {
    private final FlagWars plugin;

    private final Set<Arena> arenas;
    private Arena currentArena = null;

    public BaseArenaManager(FlagWars plugin) {
        this.plugin = plugin;

        this.arenas = new HashSet<>();
        loadArenas();
    }

    private void loadArenas() {
        FileConfiguration config = plugin.getPlugin().getConfig();

        ConfigurationSection arenasSection = config.getConfigurationSection("arenas");
        if (arenasSection == null) return;

        Set<String> keys = arenasSection.getKeys(false);
        for (String arenaName : keys) {
            ConfigurationSection section = arenasSection.getConfigurationSection(arenaName);
            if (section == null) continue;

            int maxPlayersPerTeam = section.getInt("max-players-per-team");
            ConfigurationSection blueFlagLocation = section.getConfigurationSection("blue-flag-location");
            ConfigurationSection redFlagLocation = section.getConfigurationSection("blue-flag-location");

            Arena arena = new Arena(arenaName);
            arena.setMaxPlayersPerTeam(maxPlayersPerTeam);
            arena.setBlueFlagLocation(LocationUtils.fromSection(Objects.requireNonNull(blueFlagLocation)));
            arena.setRedFlagLocation(LocationUtils.fromSection(Objects.requireNonNull(redFlagLocation)));

            this.arenas.add(arena);
        }
    }

    @Override
    public Optional<Arena> getArenaFromName(String name) {
        return arenas.stream()
                .filter(arena -> arena.getName().equals(name))
                .findFirst();
    }

    @Override
    public Arena getCurrentArena() {
        return currentArena;
    }

    @Override
    public void setCurrentArena(Arena currentArena) {
        this.currentArena = currentArena;
    }

    @Override
    public Set<Arena> getArenas() {
        return arenas;
    }
}
