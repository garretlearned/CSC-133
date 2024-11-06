
package com.csus.csc133;

public class StudentPlayer extends Student {
    private static StudentPlayer instance = null;
    private boolean isTurningLeft = false;
    private boolean isTurningRight = false;
    private boolean shouldMove = false;
    private Lecture lecture;

    private StudentPlayer(int x, int y) {
        super(x, y);
        this.sweatingRate = 0.01;
        // Set any specific initial values for the StudentPlayer
    }

    public static StudentPlayer getInstance(int x, int y) {
        if (instance == null) {
            instance = new StudentPlayer(x, y);
        }
        return instance;
    }

    @Override
    public void move(long elapsedTime) {
        if (shouldMove) {
            super.move(elapsedTime);
        }
    }
    
    
    // Override the toString() method to include absenceTime and waterIntake
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "x=" + x +
                ", y=" + y +
                ", head=" + head +
                ", speed=" + speed +
                ", hydration=" + hydration +
                ", timeRemain=" + timeRemain +
                ", waterIntake=" + waterIntake +
                ", absenceTime=" + absenceTime +
                '}';
    }
    
    public boolean isInLectureHall(LectureHall lectureHall) {
        // Check if the player's coordinates are within the bounds of the lecture hall
        // This is a simple implementation and might need to be adjusted based on your specific requirements
        return this.x >= lectureHall.getX() && this.x <= lectureHall.getX() + lectureHall.getSize() &&
               this.y >= lectureHall.getY() && this.y <= lectureHall.getY() + lectureHall.getSize();
    }

    public void startMoving() {
        this.speed = DEFAULT_SPEED;
        shouldMove = true;
        System.out.println("StudentPlayer has started moving");
    }

    public void stopMoving() {
    	        shouldMove = false;
        System.out.println("StudentPlayer has stopped moving");
        this.speed = 0;
    }

    public void turnLeft() {
    	this.isTurningLeft = true;
    	this.isTurningRight = false;
        this.head += 90;
        System.out.println("StudentPlayer has turned left");
    }

    public void turnRight() {
    	this.isTurningLeft = false;
    	this.isTurningRight = true;
        this.head -= 90;
        System.out.println("StudentPlayer has turned right");
    }
    
    public boolean isMoving() {
        return this.speed > 0;
    }

    public boolean isStopped() {
        return this.speed == 0;
    }
    
    public boolean isTurningLeft() {
        return this.isTurningLeft;
    }

    public boolean isTurningRight() {
        return this.isTurningRight;
    }
    
    public void collide(GameObject gameObject) {
        gameObject.handleCollide(this);
    }
    
    
    
    @Override
    public void handleCollision(GameObject other) {
        super.handleCollision(other);
        if (other instanceof LectureHall) {
            // End the lecture
            this.lecture.Ongoinglecture = false;
            this.lecture.LecTimeRemaining = 0;
        }
    }
}
