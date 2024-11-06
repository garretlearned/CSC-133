package com.csus.csc133;

public class StudentAngry extends Student {
    public StudentAngry(int x, int y) {
        super(x, y);
        this.talkingTime = DEFAULT_TALKING_TIME * 2;
    }
    
    @Override
    public void move(long elapsedTime) {
        // Implement your logic here
        super.move(elapsedTime);
    }
    
    
}