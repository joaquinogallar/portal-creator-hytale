package com.joaquinogallar.portalcreator.system;

import com.hypixel.hytale.component.ArchetypeChunk;
import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.tick.EntityTickingSystem;
import com.hypixel.hytale.math.vector.Vector3d;
import com.hypixel.hytale.server.core.modules.entity.component.TransformComponent;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.joaquinogallar.portalcreator.entity.Portal;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class PortalCreatorSystem extends EntityTickingSystem<EntityStore> {

    private static final List<Portal> portals = new ArrayList<>();

    public static boolean addPortal(String name, Vector3d p1, Vector3d p2, Vector3d destination) {
        for (Portal p : portals) {
            if(p.name().equals(name)) {
                return false;
            }
        }
        portals.add(new Portal(name, p1, p2, destination));
        return true;
    }

    @Override
    public void tick(float v, int i, @Nonnull ArchetypeChunk<EntityStore> archetypeChunk, @Nonnull Store<EntityStore> store, @Nonnull CommandBuffer<EntityStore> commandBuffer) {

    }

    @Nullable
    @Override
    public Query<EntityStore> getQuery() {
        return Query.and(
                PlayerRef.getComponentType(),
                TransformComponent.getComponentType()
        );
    }
}
