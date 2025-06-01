package me.wiceh.test.server.commands;

import me.wiceh.commandapi.CommandAPICommand;
import me.wiceh.oxygen.api.command.CommandSupplier;
import me.wiceh.oxygen.api.constants.Palette;
import net.kyori.adventure.text.Component;

public class TestCommand extends CommandSupplier {
    public TestCommand() {
        super("test");
    }

    @Override
    public CommandAPICommand[] get() {
        return new CommandAPICommand[]{
                create(name)
                        .executesPlayer((player, args) -> {
                            player.sendMessage(Component.text("Test command!", Palette.SUCCESS));
                })
        };
    }
}
