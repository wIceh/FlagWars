package me.wiceh.test.api;

import me.wiceh.oxygen.api.Oxygen;
import me.wiceh.oxygen.api.module.BaseModule;
import org.bukkit.plugin.Plugin;

public abstract class TestModule extends BaseModule {
    protected TestModule(Plugin plugin, Oxygen oxygen, String name) {
        super(plugin, oxygen, name);
    }
}
