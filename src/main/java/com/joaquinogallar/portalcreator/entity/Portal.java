package com.joaquinogallar.portalcreator.entity;

import com.hypixel.hytale.math.vector.Vector3d;

public record Portal(
        String name,
        Vector3d p1,
        Vector3d p2,
        Vector3d destination
) {
}
