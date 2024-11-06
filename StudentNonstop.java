package com.csus.csc133;

public class StudentNonstop extends Student {

    public StudentNonstop(int x, int y) {
        super(x, y);
        // Set any additional fields specific to StudentNonstop
    }

    @Override
    public void move(long elapsedTime) {
        // Double the speed of the student
        int previousSpeed = speed;
        speed *= 2;
        super.move(elapsedTime);
        speed = previousSpeed;
    }

    @Override
    public void handleCollide(Student s) {
        // Ignore the collision with another student
    }
}