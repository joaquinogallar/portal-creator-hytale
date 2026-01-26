package com.joaquinogallar.portalcreator.command;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg;
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.World;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.joaquinogallar.portalcreator.system.PortalCreatorSystem;

import javax.annotation.Nonnull;

public class DeletePortalCommand extends AbstractPlayerCommand {

    private final RequiredArg<String> name = this.withRequiredArg("name", "Name of the portal to be removed", ArgTypes.STRING);

    public DeletePortalCommand(@Nonnull String name, @Nonnull String description, boolean requiresConfirmation) {
        super(name, description, requiresConfirmation);
    }

    @Override
    protected void execute(
            @Nonnull CommandContext commandContext,
            @Nonnull Store<EntityStore> store,
            @Nonnull Ref<EntityStore> ref,
            @Nonnull PlayerRef playerRef,
            @Nonnull World world) {

        String name = this.name.get(commandContext);

        Message.raw(PortalCreatorSystem.removePortal(name) ?
                "Portal '%s' removed successfully.".formatted(name) :
                "Cannot delete portal: portal '%s' not found.".formatted(name)
        );
    }
}
