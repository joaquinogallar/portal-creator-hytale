package com.joaquinogallar.portalcreator.command;

import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.math.vector.Vector3d;
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

public class CreatePortalCommand extends AbstractPlayerCommand {

    private final RequiredArg<String> name = this.withRequiredArg("name", "portal name, should be unique", ArgTypes.STRING);

    private final RequiredArg<Double> x1 = this.withRequiredArg("x1", "x position of the first range", ArgTypes.DOUBLE);
    private final RequiredArg<Double> y1 = this.withRequiredArg("y1", "z position of the first range", ArgTypes.DOUBLE);
    private final RequiredArg<Double> z1 = this.withRequiredArg("z1", "y position of the first range", ArgTypes.DOUBLE);

    private final RequiredArg<Double> x2 = this.withRequiredArg("x2", "x position of the second range", ArgTypes.DOUBLE);
    private final RequiredArg<Double> y2 = this.withRequiredArg("y2", "z position of the second range", ArgTypes.DOUBLE);
    private final RequiredArg<Double> z2 = this.withRequiredArg("z2", "y position of the second range", ArgTypes.DOUBLE);

    private final RequiredArg<Double> x3 = this.withRequiredArg("x3", "x position of the destination", ArgTypes.DOUBLE);
    private final RequiredArg<Double> y3 = this.withRequiredArg("y3", "z position of the destination", ArgTypes.DOUBLE);
    private final RequiredArg<Double> z3 = this.withRequiredArg("z3", "y position of the destination", ArgTypes.DOUBLE);

    public CreatePortalCommand(@Nonnull String name, @Nonnull String description, boolean requiresConfirmation) {
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

        Vector3d r1 = new Vector3d(
                x1.get(commandContext),
                y1.get(commandContext),
                z1.get(commandContext));

        Vector3d r2 = new Vector3d(
                x2.get(commandContext),
                y2.get(commandContext),
                z2.get(commandContext));

        Vector3d to = new Vector3d(
                x3.get(commandContext),
                y3.get(commandContext),
                z3.get(commandContext));

        commandContext.sendMessage(Message.raw(
                PortalCreatorSystem.addPortal(name, r1, r2, to) ?
                        "Portal '%s' created successfully.".formatted(name) :
                        "Cannot create portal: the name '%s' is already in use.".formatted(name)
                )
        );
    }
}
