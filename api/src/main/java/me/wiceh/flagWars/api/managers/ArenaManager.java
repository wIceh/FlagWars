package me.wiceh.flagWars.api.managers;

import me.wiceh.flagWars.api.objects.Arena;

import java.util.Optional;
import java.util.Set;

public abstract class ArenaManager {
    public abstract Optional<Arena> getArenaFromName(String name);

    public abstract Arena getCurrentArena();

    public abstract void setCurrentArena(Arena currentArena);

    public abstract Set<Arena> getArenas();
}
