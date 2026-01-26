package com.joaquinogallar.portalcreator.system;

import com.hypixel.hytale.component.ArchetypeChunk;
import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.component.system.tick.EntityTickingSystem;
import com.hypixel.hytale.math.vector.Vector3d;
import com.hypixel.hytale.server.core.modules.entity.component.TransformComponent;
import com.hypixel.hytale.server.core.modules.entity.teleport.Teleport;
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

    public static boolean removePortal(String name) {
        for (Portal p : portals) {
            if(p.name().equals(name)) {
                portals.remove(p);
                return true;
            }
        }
        return false;
    }

    @Override
    public void tick(float v, int i,
                     @Nonnull ArchetypeChunk<EntityStore> archetypeChunk,
                     @Nonnull Store<EntityStore> store,
                     @Nonnull CommandBuffer<EntityStore> commandBuffer) {
        Ref<EntityStore> ref = archetypeChunk.getReferenceTo(i);
        TransformComponent transform = archetypeChunk.getComponent(i, TransformComponent.getComponentType());

        if (transform == null) return;

        Vector3d currentEntityLocation = transform.getPosition();

        for (Portal p : portals) {
            if (isInside(currentEntityLocation, p.p1(), p.p2())) {
                Teleport teleport = new Teleport(
                        p.destination(),
                        transform.getRotation()
                );

                commandBuffer.putComponent(ref, Teleport.getComponentType(), teleport); // thread-safe, applied end of tick

                return;
            }
        }
    }

    @Nullable
    @Override
    public Query<EntityStore> getQuery() {
        return Query.and(
                PlayerRef.getComponentType(),
                TransformComponent.getComponentType()
        );
    }

    private boolean isInside(Vector3d origin, Vector3d p1, Vector3d p2) {

        double minX = Math.min(p1.x, p2.x);
        double maxX = Math.max(p1.x, p2.x);

        double minY = Math.min(p1.y, p2.y);
        double maxY = Math.max(p1.y, p2.y);

        double minZ = Math.min(p1.z, p2.z);
        double maxZ = Math.max(p1.z, p2.z);

        return origin.x >= minX && origin.x <= maxX
                && origin.y >= minY && origin.y <= maxY
                && origin.z >= minZ && origin.z <= maxZ;
    }
}
