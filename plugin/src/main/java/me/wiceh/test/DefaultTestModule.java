package me.wiceh.test;

import me.wiceh.oxygen.api.Oxygen;
import me.wiceh.oxygen.api.exceptions.ModuleException;
import me.wiceh.oxygen.api.module.ModuleDataProvider;
import me.wiceh.test.api.TestModule;
import me.wiceh.test.data.TestDataProvider;
import org.bukkit.plugin.Plugin;

import java.sql.SQLException;

public class DefaultTestModule extends TestModule {

    public DefaultTestModule(Plugin plugin, Oxygen oxygen) {
        super(plugin, oxygen, "test-module");
    }

    @Override
    protected ModuleDataProvider getProvider() {
        return new TestDataProvider();
    }

    @Override
    protected void onEnable() throws SQLException, ModuleException {
        System.out.println("Enabling module " + this.getName());
    }
}
