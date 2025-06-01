package me.wiceh.flagWars.api.objects;

import me.wiceh.flagWars.api.constants.Palette;
import me.wiceh.flagWars.api.constants.TeamColor;
import me.wiceh.flagWars.api.utils.FlagWarsUtils;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static net.kyori.adventure.text.Component.text;

public class Arena {
    private final String name;

    private final Set<Player> redTeam = new HashSet<>();
    private final Set<Player> blueTeam = new HashSet<>();

    private int maxPlayersPerTeam = 2;

    private Location redFlagLocation;
    private Location blueFlagLocation;

    public Arena(String name) {
        this.name = name;
    }

    public void joinTeam(Player player, TeamColor teamColor) {
        switch (teamColor) {
            case RED -> {
                if (redTeam.size() >= maxPlayersPerTeam) {
                    player.sendMessage(FlagWarsUtils.PREFIX.append(text("The red team is full.", NamedTextColor.RED)));
                    return;
                }

                redTeam.add(player);
                getInGamePlayers().forEach(p -> p.sendMessage(FlagWarsUtils.PREFIX
                        .append(text(player.getName() + " joined the game. (" + getInGamePlayers().size() + "/" + (maxPlayersPerTeam * 2) + ")",
                                Palette.LIGHT_GRAY))));
            }
            case BLUE -> {
                if (blueTeam.size() >= maxPlayersPerTeam) {
                    player.sendMessage(FlagWarsUtils.PREFIX.append(text("The blue team is full.", NamedTextColor.RED)));
                    return;
                }

                blueTeam.add(player);
                getInGamePlayers().forEach(p -> p.sendMessage(FlagWarsUtils.PREFIX
                        .append(text(player.getName() + " joined the game. (" + getInGamePlayers().size() + "/" + (maxPlayersPerTeam * 2) + ")",
                                Palette.LIGHT_GRAY))));
            }
        }
    }

    public void leave(Player player) {
        blueTeam.remove(player);
        redTeam.remove(player);
        getInGamePlayers().forEach(p -> p.sendMessage(FlagWarsUtils.PREFIX
                .append(text(player.getName() + " left the game. (" + getInGamePlayers().size() + "/" + (maxPlayersPerTeam * 2) + ")", Palette.LIGHT_GRAY))));
    }

    private Set<Player> getInGamePlayers() {
        Set<Player> players = new HashSet<>();
        players.addAll(blueTeam);
        players.addAll(redTeam);
        return players;
    }

    public TeamColor getTeamWithFewerPlayers() {
        int redSize = redTeam.size();
        int blueSize = blueTeam.size();

        if (redSize < blueSize) return TeamColor.RED;
        if (blueSize < redSize) return TeamColor.BLUE;

        TeamColor[] teamColors = TeamColor.values();
        return teamColors[new Random().nextInt(teamColors.length)];
    }

    public boolean isInGame(Player player) {
        return redTeam.contains(player) || blueTeam.contains(player);
    }

    public String getName() {
        return name;
    }

    public Set<Player> getRedTeam() {
        return redTeam;
    }

    public Set<Player> getBlueTeam() {
        return blueTeam;
    }

    public Location getRedFlagLocation() {
        return redFlagLocation;
    }

    public void setRedFlagLocation(Location redFlagLocation) {
        this.redFlagLocation = redFlagLocation;
    }

    public Location getBlueFlagLocation() {
        return blueFlagLocation;
    }

    public void setBlueFlagLocation(Location blueFlagLocation) {
        this.blueFlagLocation = blueFlagLocation;
    }

    public int getMaxPlayersPerTeam() {
        return maxPlayersPerTeam;
    }

    public void setMaxPlayersPerTeam(int maxPlayersPerTeam) {
        this.maxPlayersPerTeam = maxPlayersPerTeam;
    }
}
