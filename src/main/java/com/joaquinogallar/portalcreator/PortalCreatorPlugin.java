package com.joaquinogallar.portalcreator;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.joaquinogallar.portalcreator.command.PortalCreatorCommandCollection;
import com.joaquinogallar.portalcreator.system.PortalCreatorSystem;

import javax.annotation.Nonnull;

public class PortalCreatorPlugin extends JavaPlugin {
    public PortalCreatorPlugin(@Nonnull JavaPluginInit init) {
        super(init);
    }

    @Override
    protected void setup() {
        this.getCommandRegistry()
                .registerCommand(new PortalCreatorCommandCollection("pcreator", "Commands to operate and configure teleporters."));
        this.getEntityStoreRegistry().registerSystem(new PortalCreatorSystem());
    }
}
