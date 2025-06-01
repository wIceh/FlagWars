package me.wiceh.flagWars.api.permissions;

import org.bukkit.entity.Player;

public enum FlagWarsPermission {
    HOST("host"),
    START("start"),
    STOP("stop")
    ;

    private final String permission;

    FlagWarsPermission(String permission) {
        this.permission = "flagwars." + permission;
    }

    public String getPermission() {
        return permission;
    }

    public boolean has(Player player) {
        return player.hasPermission(this.permission);
    }
}
