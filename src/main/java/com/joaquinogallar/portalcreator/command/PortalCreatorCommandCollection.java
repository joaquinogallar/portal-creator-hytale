package com.joaquinogallar.portalcreator.command;

import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection;

import javax.annotation.Nonnull;

public class PortalCreatorCommandCollection extends AbstractCommandCollection {
    public PortalCreatorCommandCollection(@Nonnull String name, @Nonnull String description) {
        super(name, description);

        this.addSubCommand(new CreatePortalCommand("create", "Create a portal", true));
    }
}
