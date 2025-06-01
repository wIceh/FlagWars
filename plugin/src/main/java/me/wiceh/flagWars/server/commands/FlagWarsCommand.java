package me.wiceh.flagWars.server.commands;

import me.wiceh.flagWars.FlagWars;
import me.wiceh.flagWars.api.constants.Palette;
import me.wiceh.flagWars.api.managers.ArenaManager;
import me.wiceh.flagWars.api.objects.Arena;
import me.wiceh.flagWars.api.permissions.FlagWarsPermission;
import me.wiceh.flagWars.api.utils.FlagWarsUtils;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static net.kyori.adventure.text.Component.empty;
import static net.kyori.adventure.text.Component.text;

public class FlagWarsCommand implements CommandExecutor, TabCompleter {
    private final FlagWars plugin;
    private final ArenaManager manager;

    public FlagWarsCommand(FlagWars plugin) {
        this.plugin = plugin;
        this.manager = plugin.getArenaManager();
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) return List.of();

        if (args.length == 1) {
            List<String> subcommands = new ArrayList<>();

            if (FlagWarsPermission.HOST.has(player))
                subcommands.add("host");

            if (FlagWarsPermission.START.has(player))
                subcommands.add("start");

            subcommands.add("join");
            subcommands.add("leave");

            if (FlagWarsPermission.STOP.has(player))
                subcommands.add("stop");


            return subcommands;
        }

        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("host") && FlagWarsPermission.HOST.has(player))
                return manager.getArenas().stream()
                        .map(Arena::getName)
                        .toList();
        }

        return List.of();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) return true;

        if (args.length == 0) {
            sendHelpMessage(player);
            return true;
        }

        String action = args[0].toLowerCase();
        switch (action) {
            case "host" -> handleHostCommand(player, args);
            case "join" -> handleJoinCommand(player, args);
            case "leave" -> handleLeaveCommand(player, args);
            case "start" -> handleStartCommand(player, args);
            case "stop" -> handleStopCommand(player, args);
            default -> sendHelpMessage(player);
        }

        return true;
    }

    private void handleHostCommand(Player player, String[] args) {
        if (!FlagWarsPermission.HOST.has(player)) {
            player.sendMessage(FlagWarsUtils.PREFIX.append(text("You don't have the permission to execute this command.", NamedTextColor.RED)));
            return;
        }

        String arenaName = args[1];
        Arena arena = manager.getArenaFromName(arenaName).orElse(null);
        if (arena == null) {
            player.sendMessage(FlagWarsUtils.PREFIX.append(text("Arena not found.", NamedTextColor.RED)));
            return;
        }

        if (manager.getCurrentArena() != null) {
            player.sendMessage(FlagWarsUtils.PREFIX.append(text("There is already a game in progress.", NamedTextColor.RED)));
            return;
        }

        manager.setCurrentArena(arena);
        Bukkit.getOnlinePlayers().forEach(p ->
                p.sendMessage(FlagWarsUtils.PREFIX.append(text(player.getName() + " hosted a game!", Palette.ORANGE)
                        .hoverEvent(HoverEvent.showText(text("Click to join", Palette.LIGHT_GRAY)))
                        .clickEvent(ClickEvent.runCommand("/flagwars join"))))
        );
    }

    private void handleJoinCommand(Player player, String[] args) {
        Arena arena = manager.getCurrentArena();
        if (arena == null) {
            player.sendMessage(FlagWarsUtils.PREFIX.append(text("There is no game in progress.", NamedTextColor.RED)));
            return;
        }

        if (arena.isInGame(player)) {
            player.sendMessage(FlagWarsUtils.PREFIX.append(text("You are already inside the game.", NamedTextColor.RED)));
            return;
        }

        arena.joinTeam(player, arena.getTeamWithFewerPlayers());
    }

    private void handleLeaveCommand(Player player, String[] args) {
        Arena arena = manager.getCurrentArena();
        if (arena == null) {
            player.sendMessage(FlagWarsUtils.PREFIX.append(text("There is no game in progress.", NamedTextColor.RED)));
            return;
        }

        if (!arena.isInGame(player)) {
            player.sendMessage(FlagWarsUtils.PREFIX.append(text("You are not inside a game.", NamedTextColor.RED)));
            return;
        }

        arena.leave(player);
        player.sendMessage(FlagWarsUtils.PREFIX.append(text("You left the game.", Palette.LIGHT_GRAY)));
    }

    private void handleStartCommand(Player player, String[] args) {
        if (!FlagWarsPermission.START.has(player)) {
            player.sendMessage(FlagWarsUtils.PREFIX.append(text("You don't have the permission to execute this command.", NamedTextColor.RED)));
            return;
        }
    }

    private void handleStopCommand(Player player, String[] args) {
        if (!FlagWarsPermission.STOP.has(player)) {
            player.sendMessage(FlagWarsUtils.PREFIX.append(text("You don't have the permission to execute this command.", NamedTextColor.RED)));
            return;
        }
    }

    private void sendHelpMessage(Player player) {
        player.sendMessage(empty());
        player.sendMessage(text("═══════ ", Palette.DARK_GRAY)
                .append(text("FlagWars", Palette.COBALT).decorate(TextDecoration.BOLD))
                .append(text(" ═══════", Palette.DARK_GRAY)));

        sendHelpValue(player, "/flagwars host <arena>", "Host a game", FlagWarsPermission.HOST.getPermission());
        sendHelpValue(player, "/flagwars join", "Join the game");
        sendHelpValue(player, "/flagwars leave", "Leave the game");
        sendHelpValue(player, "/flagwars start", "Start the game", FlagWarsPermission.START.getPermission());
        sendHelpValue(player, "/flagwars stop", "Stop the game", FlagWarsPermission.STOP.getPermission());

        player.sendMessage(text("═════════════════════", Palette.DARK_GRAY));
        player.sendMessage(empty());
    }

    private void sendHelpValue(Player player, String command, String description, String permission) {
        if (permission == null || player.hasPermission(permission))
            player.sendMessage(text("» ", Palette.DARK_GRAY).append(text(command, Palette.DEEP_SKY_BLUE)
                    .hoverEvent(HoverEvent.showText(text(description, Palette.LIGHT_GRAY)))
                    .clickEvent(ClickEvent.suggestCommand(command))));
    }

    private void sendHelpValue(Player player, String command, String description) {
        sendHelpValue(player, command, description, null);
    }
}
