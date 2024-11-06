package com.csus.csc133;


import java.util.HashSet;
import java.util.Set;

public class CollisionVector {
    private Set<String> collisions;

    public CollisionVector() {
        this.collisions = new HashSet<>();
    }

    public void addCollision(GameObject obj1, GameObject obj2) {
        String collisionKey = generateCollisionKey(obj1, obj2);
        collisions.add(collisionKey);
    }

    public boolean hasCollisionOccurred(GameObject obj1, GameObject obj2) {
        String collisionKey = generateCollisionKey(obj1, obj2);
        return collisions.contains(collisionKey);
    }

    public void clear() {
        collisions.clear();
    }

    private String generateCollisionKey(GameObject obj1, GameObject obj2) {
        // Ensure the key is the same regardless of the order of obj1 and obj2
        if (obj1.hashCode() < obj2.hashCode()) {
            return obj1.hashCode() + ":" + obj2.hashCode();
        } else {
            return obj2.hashCode() + ":" + obj1.hashCode();
        }
    }
}
