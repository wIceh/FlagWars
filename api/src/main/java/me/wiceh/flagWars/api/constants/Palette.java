package me.wiceh.flagWars.api.constants;

import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;

public interface Palette {
    TextColor CRIMSON = TextColor.fromHexString("#DC143C");
    TextColor COBALT = TextColor.fromHexString("#0047AB");

    TextColor ORANGE = TextColor.fromHexString("#FF6F00");
    TextColor DEEP_SKY_BLUE = TextColor.fromHexString("#00BFFF");
    TextColor MEDIUM_PURPLE = TextColor.fromHexString("#9370DB");
    TextColor WHITE = NamedTextColor.WHITE;

    TextColor DARK_GRAY = NamedTextColor.DARK_GRAY;
    TextColor LIGHT_GRAY = TextColor.fromHexString("#D3D3D3");
}
