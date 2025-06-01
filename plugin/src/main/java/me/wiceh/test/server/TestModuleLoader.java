package me.wiceh.test.server;

import me.wiceh.oxygen.api.exceptions.ModuleException;
import me.wiceh.oxygen.api.module.BaseModule;
import me.wiceh.oxygen.api.module.ModuleLoader;
import me.wiceh.test.DefaultTestModule;

import java.sql.SQLException;

public final class TestModuleLoader extends ModuleLoader {

    @Override
    protected BaseModule provider() throws SQLException, ModuleException {
        return new DefaultTestModule(this, oxygen);
    }
}
