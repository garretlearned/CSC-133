package com.csus.csc133;

public class StudentFriendly extends Student {
    public StudentFriendly(int x, int y) {
        super(x, y);
        this.talkingTime = DEFAULT_TALKING_TIME / 2;
    }
    @Override
    public void move(long elapsedTime) {
        // Implement your logic here
        super.move(elapsedTime);
    }
}