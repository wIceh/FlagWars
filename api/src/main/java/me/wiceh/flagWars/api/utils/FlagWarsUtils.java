package me.wiceh.flagWars.api.utils;

import me.wiceh.flagWars.api.constants.Palette;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class FlagWarsUtils {
    public final static Component PREFIX = Component.text()
            .append(Component.text("Flag", Palette.CRIMSON).decorate(TextDecoration.BOLD))
            .append(Component.text("Wars", Palette.COBALT).decorate(TextDecoration.BOLD))
            .append(Component.text(" »", Palette.DARK_GRAY).decorate(TextDecoration.BOLD))
            .append(Component.text(" ", NamedTextColor.GRAY).decoration(TextDecoration.BOLD, false))
            .build();
}
