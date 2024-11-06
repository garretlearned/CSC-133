package com.csus.csc133;

public class StudentBiking extends Student {
    public StudentBiking(int x, int y) {
        super(x, y);
        this.speed = DEFAULT_SPEED * 2;
        this.sweatingRate = sweatingRate * 2;
    }
    
    @Override
    public void move(long elapsedTime) {
        // Implement your logic here
        super.move(elapsedTime);
    }
}