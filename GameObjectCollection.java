package com.csus.csc133;

import java.util.ArrayList;
import java.util.List;

public class GameObjectCollection {
    private List<GameObject> gameObjects;
    
    public GameObject get(int index) {
        return gameObjects.get(index);
    }

    public GameObjectCollection() {
        gameObjects = new ArrayList<>();
    }

    public void add(GameObject gameObject) {
        gameObjects.add(gameObject);
    }

    public Iterator getIterator() {
        return new Iterator();
    }

    public class Iterator {
        private int currentIndex = 0;

        public boolean hasNext() {
            return currentIndex < gameObjects.size();
        }

        public GameObject getNext() {
            if (hasNext()) {
                return gameObjects.get(currentIndex++);
            } else {
                return null;
            }
        }
    }
}