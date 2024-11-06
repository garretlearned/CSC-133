
package com.csus.csc133;

import java.util.Arrays;
import java.util.Random;
import java.util.Observable;
import java.util.List;
import java.util.ArrayList;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;


public class GameModel extends Observable{
    private int width;
    private int height;
    private GameObjectCollection gameObjects;
    private long gameTime;
    private StudentPlayer player;
    private Lecture lecture;
    private String collisionMessage;
    private CollisionVector collisionVector;
    private boolean isPaused = false;
    private boolean changePositionMode = false;
    private List<LectureHall> lectureHalls;
    private Random random = new Random();
    private int moveCounter = 0; // initialize moveCounter
    private static final int MOVE_THRESHOLD = 10; // initialize MOVE_THRESHOLD
    private List<GameObject> collidingObjects = new ArrayList<>();

    public GameModel() {
        width = 1024;
        height = 768;
        lectureHalls = Arrays.asList(
                new LectureHall("LectureHall1", 100, 100),
                new LectureHall("LectureHall2", 200, 200),
                new LectureHall("LectureHall3", 300, 300));
        gameObjects = new GameObjectCollection();
        this.lecture = Lecture.getInstance();
        gameTime = 0;
        
        this.collisionVector = new CollisionVector();

        Random random = new Random();
        
        // Spawn 2-4 LectureHalls
        int numLectureHalls = random.nextInt(3) + 2;
		for (int i = 0; i < numLectureHalls; i++) {
			gameObjects.add(new LectureHall("Lecture Hall " + i, random.nextInt(width), random.nextInt(height)));
		}

        // Spawn 2-4 Restrooms
        int numRestrooms = random.nextInt(3) + 2;
        for (int i = 0; i < numRestrooms; i++) {
            gameObjects.add(new Restroom(random.nextInt(width), random.nextInt(height)));
        }

        // Spawn 2-4 WaterDispensers
        int numWaterDispensers = random.nextInt(3) + 2;
        for (int i = 0; i < numWaterDispensers; i++) {
            gameObjects.add(new WaterDispenser(random.nextInt(width), random.nextInt(height)));
        }

        //creating student objects

        int StudentAngry = random.nextInt(2) + 1;
        for (int i = 0; i < StudentAngry; i++) {
            gameObjects.add(new StudentAngry(random.nextInt(width), random.nextInt(height)));
        }

        int StudentBiking = random.nextInt(2) + 1;
        for (int i = 0; i < StudentBiking; i++) {
            gameObjects.add(new StudentBiking(random.nextInt(width), random.nextInt(height)));
        }

        int StudentCar = random.nextInt(2) + 1;
        for (int i = 0; i < StudentCar; i++) {
            gameObjects.add(new StudentCar(random.nextInt(width), random.nextInt(height), width));
        }

        int StudentConfused = random.nextInt(2) + 1;
        for (int i = 0; i < StudentConfused; i++) {
            gameObjects.add(new StudentConfused(random.nextInt(width), random.nextInt(height)));
        }

        int StudentFriendly = random.nextInt(2) + 1;
        for (int i = 0; i < StudentFriendly; i++) {
            gameObjects.add(new StudentFriendly(random.nextInt(width), random.nextInt(height)));
        }

        int StudentHappy = random.nextInt(2) + 1;
        for (int i = 0; i < StudentHappy; i++) {
            gameObjects.add(new StudentHappy(random.nextInt(width), random.nextInt(height)));
        }

        int StudentNonstop = random.nextInt(2) + 1;
        for (int i = 0; i < StudentNonstop; i++) {
            gameObjects.add(new StudentNonstop(random.nextInt(width), random.nextInt(height)));
        }

        int StudentRunning = random.nextInt(2) + 1;
        for (int i = 0; i < StudentRunning; i++) {
            gameObjects.add(new StudentRunning(random.nextInt(width), random.nextInt(height)));
        }

        int StudentSleeping = random.nextInt(2) + 1;
        for (int i = 0; i < StudentSleeping; i++) {
            gameObjects.add(new StudentSleeping(random.nextInt(width), random.nextInt(height)));
        }

        player = StudentPlayer.getInstance(random.nextInt(width), random.nextInt(height));
        this.lecture = lecture;

        gameObjects.add(player);

        GameObjectCollection.Iterator iterator = gameObjects.getIterator();
        while (iterator.hasNext()) {
            GameObject gameObject = iterator.getNext();
            gameObject.setPosition(width, height);
        }
    }
    
    public GameObjectCollection getGameObjects() {
        return gameObjects;
    }

    public StudentPlayer getPlayer() {
        return player;
    }

    public void selectStudent(int studentType) {
        GameObjectCollection.Iterator iterator = gameObjects.getIterator();
        while (iterator.hasNext()) {
            GameObject gameObject = iterator.getNext();
            if (gameObject instanceof Student && getStudentType(gameObject) == studentType) {
                // Select the student
                // Implement the logic for selecting the student
                return;
            }
        }
    }

    public int getStudentType(GameObject gameObject) {
        if (gameObject instanceof StudentAngry) {
            return 0;
        } else if (gameObject instanceof StudentBiking) {
            return 1;
        } else if (gameObject instanceof StudentCar) {
            return 2;
        } else if (gameObject instanceof StudentConfused) {
            return 3;
        } else if (gameObject instanceof StudentFriendly) {
            return 4;
        } else if (gameObject instanceof StudentHappy) {
            return 5;
        } else if (gameObject instanceof StudentNonstop) {
            return 6;
        } else if (gameObject instanceof StudentRunning) {
            return 7;
        } else if (gameObject instanceof StudentSleeping) {
            return 8;
        } else {
            return -1;
        }
    }
//test
    public LectureHall getLectureHall() {
        return lectureHalls.get(random.nextInt(lectureHalls.size()));
    }

    public void simulateLectureHallCollision() {
        LectureHall lectureHall = getLectureHall();
        if (lectureHall != null) {
            player.collide(lectureHall);
        }
    }

    public void simulateRestroomCollision() {
        Restroom restroom = getRestroom();
        if (restroom != null) {
            player.collide(restroom);
        }
    }

    public Restroom getRestroom() {
        GameObjectCollection.Iterator iterator = gameObjects.getIterator();
        while (iterator.hasNext()) {
            GameObject gameObject = iterator.getNext();
            if (gameObject instanceof Restroom) {
                return (Restroom) gameObject;
            }
        }
        return null; // Return null if no Restroom instance is found
    }

    public void simulateWaterDispenserCollision() {
        WaterDispenser waterDispenser = getWaterDispenser();
        if (waterDispenser != null) {
            player.collide(waterDispenser);
        }
    }

    public WaterDispenser getWaterDispenser() {
        GameObjectCollection.Iterator iterator = gameObjects.getIterator();
        while (iterator.hasNext()) {
            GameObject gameObject = iterator.getNext();
            if (gameObject instanceof WaterDispenser) {
                return (WaterDispenser) gameObject;
            }
        }
        return null; // Return null if no WaterDispenser instance is found
    }
    
    

    public void testStudentCollision() {
        GameObject randomStudent = getRandomNonPlayerStudent();
        if (randomStudent != null) {
            randomStudent.handleCollide(player);
        }
    }

    public GameObject getRandomNonPlayerStudent() {
        List<GameObject> nonPlayerStudents = new ArrayList<>();
        GameObjectCollection.Iterator iterator = gameObjects.getIterator();
        while (iterator.hasNext()) {
            GameObject gameObject = iterator.getNext();
            if (gameObject instanceof Student && !(gameObject instanceof StudentPlayer)) {
                nonPlayerStudents.add(gameObject);
            }
        }
        if (nonPlayerStudents.isEmpty()) {
            return null; // No non-player students found
        }
        int randomIndex = new Random().nextInt(nonPlayerStudents.size());
        return nonPlayerStudents.get(randomIndex);
    }
    
    public List<StudentWithStrategy> getStudentsWithStrategy() {
        List<StudentWithStrategy> studentsWithStrategy = new ArrayList<>();
        GameObjectCollection.Iterator iterator = gameObjects.getIterator();
        while (iterator.hasNext()) {
            GameObject gameObject = iterator.getNext();
            if (gameObject instanceof StudentWithStrategy) {
                studentsWithStrategy.add((StudentWithStrategy) gameObject);
            }
        }
        return studentsWithStrategy;
    }
    
    public boolean isCollidingWith(GameObject other) {
        return collidingObjects.contains(other);
    }

    public void addCollision(GameObject other) {
        if (!isCollidingWith(other)) {
            collidingObjects.add(other);
        }
    }

    public void removeCollision(GameObject other) {
        collidingObjects.remove(other);
    }


    public void nextFrame() {
        if (!isPaused) {
            long elapsedTime = 20;
            increaseGameTime();
            LectureHall lectureHall = getLectureHall();
            if (!lecture.Ongoinglecture) {
                lecture.spawnLecture();
            }
            if (lecture.Ongoinglecture == true) {
                // ... existing code ...
            } else {
                lecture.spawnLecture();
            }
//
            moveCounter++;
            if (moveCounter >= MOVE_THRESHOLD) {
            	GameObjectCollection.Iterator iterator = gameObjects.getIterator();
                while (iterator.hasNext()) {
                    GameObject gameObject = iterator.getNext();
                    gameObject.move(elapsedTime);
                    GameObjectCollection.Iterator otherIterator = gameObjects.getIterator();
                    while (otherIterator.hasNext()) {
                        GameObject otherObject = otherIterator.getNext();
                        if (gameObject != otherObject) {
                            if (gameObject.collidesWith(otherObject)) {
                                if (!gameObject.isCollidingWith(otherObject)) {
                                    gameObject.addCollision(otherObject);
                                    otherObject.addCollision(gameObject);
                                    if (gameObject instanceof Student && otherObject instanceof Student) {
                                        ((Student) gameObject).handleCollide((Student) otherObject);
                                        ((Student) otherObject).handleCollide((Student) gameObject);
                                    }
                                }
                            } else {
                                gameObject.removeCollision(otherObject);
                                otherObject.removeCollision(gameObject);
                            }
                        }
                    }
                }
                moveCounter = 0;
            }

            collisionVector.clear();
            checkGameEnd();

            setChanged(); // Indicate that the state has changed
            notifyObservers(); // Notify all observers
        }
        checkGameOver();
    }

    
    public void setPaused(boolean isPaused) {
        this.isPaused = isPaused;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void setChangePositionMode(boolean changePositionMode) {
        this.changePositionMode = changePositionMode;
    }

    public boolean isChangePositionMode() {
        return changePositionMode;
    }

    public long getGameTime() {
        return gameTime;
    }

    public void increaseGameTime() {
        gameTime++;
    }

    public boolean checkGameEnd() {
        if (player.getHydration() == 0 || player.getWaterIntake() == 100 || player.getAbsenceTime() == 10) {
            return true;
        } else {
            return false;
        }
    }

    public void outputGameInformation() {
        System.out.println("Game Time: " + gameTime);
        GameObjectCollection.Iterator iterator = gameObjects.getIterator();
        while (iterator.hasNext()) {
            GameObject gameObject = iterator.getNext();
            System.out.println(gameObject.toString());
        }
    }
    
    public void notifyChanges() {
        setChanged();
        notifyObservers();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    public void updateCollisionMessage(GameObject object1, GameObject object2) {
        collisionMessage = object1.getClass().getSimpleName() + " collide with " + object2.getClass().getSimpleName() + ".";
    }

    public String getCollisionMessage() {
        return collisionMessage;
    }
    
    public void checkGameOver() {
        if (player.getAbsenceTime() >= 10 || player.getWaterIntake() >= 100 || player.getHydration() <= 0) {
            boolean confirmExit = Dialog.show("Game Over", "Game Over! Do you want to exit?", "Confirm", null);
            if (confirmExit) {
                Display.getInstance().exitApplication();
            }
        }
}
}
