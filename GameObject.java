
package com.csus.csc133;

import java.util.Random;
import java.util.Vector;
import com.codename1.charts.util.ColorUtil;

public abstract class GameObject {
    public int x;
    public int y;
    public int gamewidth;
    public int gameheight;
    public int color;
    public int size;
    
    private boolean selected = false;

    protected Vector<GameObject> colliders = new Vector<>();
    private Vector<GameObject> collisionVector = new Vector<>();

    public GameObject() {
        color = ColorUtil.rgb(255,  0,  0); //default color is red
    }

    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
        color = ColorUtil.rgb(255,  0,  0); //default color is red
    }

    public abstract void move(long elapsedTime);

    public abstract void handleCollide(Student s);

    public void setPosition(int width, int height) {
        Random random = new Random();
        x = random.nextInt(width);
        y = random.nextInt(height);
        gamewidth = width;
        gameheight = height;
    }

    @Override
    public String toString() {
        return "GameObject{" +
               "x=" + x +
               ", y=" + y +
               ", color=" + color +
               ", size=" + size +
               '}';
    }

    public boolean isCollidingWith(GameObject other) {
        return collisionVector.contains(other);
    }

    public void addCollision(GameObject other) {
        if (!collisionVector.contains(other)) {
            collisionVector.add(other);
        }
    }

    public void removeCollision(GameObject other) {
        collisionVector.remove(other);
    }

    public void clearCollisions() {
        collisionVector.clear();
    }
    
    public boolean containsInCollisionVector(GameObject otherObject) {
        return collisionVector.contains(otherObject);
    }
    

public boolean collidesWith(GameObject other) {
    // Check if this object's boundaries intersect with the other object's boundaries
    return this.x < other.x + other.size &&
           this.x + this.size > other.x &&
           this.y < other.y + other.size &&
           this.y + this.size > other.y;
}

    
    public void select() {
        this.selected = true;
    }

    public void deselect() {
        this.selected = false;
    }

    public boolean isSelected() {
        return this.selected;
    }
    
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
    
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSize() {
        return this.size;
    }
    
    
    
    
}
