package me.wiceh.test.data;

import me.wiceh.oxygen.api.command.CommandSupplier;
import me.wiceh.oxygen.api.listener.ModuleListener;
import me.wiceh.oxygen.api.module.ModuleDataProvider;
import me.wiceh.test.server.commands.TestCommand;

import java.util.List;

public class TestDataProvider extends ModuleDataProvider {

    @Override
    protected CommandSupplier[] getCommands() {
        return new CommandSupplier[]{
                new TestCommand()
        };
    }

    @Override
    protected List<ModuleListener<?>> getListeners() {
        return List.of(

        );
    }
}
