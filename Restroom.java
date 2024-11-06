package com.csus.csc133;

public class Restroom extends Facility {
	
    public Restroom(int x, int y) {
        super(x, y);
        this.size = 60; // Set size
    }
    
    @Override
    public String toString() {
        return "Restroom{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public void handleCollide(Student s) {
        if (this.x == s.x && this.y == s.y) {
            s.resetWaterIntake(); // Student uses restroom, resetting their water intake to 0
        }
    }
    
    @Override
    public void move(long elapsedTime) {
        // Implement your logic here
    }

}