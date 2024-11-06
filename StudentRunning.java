package com.csus.csc133;

public class StudentRunning extends Student {
    public StudentRunning(int x, int y) {
        super(x, y);
        this.sweatingRate = sweatingRate * 2;
    }
    
    @Override
    public void move(long elapsedTime) {
        // Implement your logic here
        super.move(elapsedTime);
    }
}