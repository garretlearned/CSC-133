package com.csus.csc133;

import java.util.Random;

public class StudentConfused extends Student {
    private static final Random random = new Random();

    public StudentConfused(int x, int y) {
        super(x, y);
    }

    @Override
    public void move(long elapsedTime) {
        this.head = (int) (random.nextDouble() * 360); // Convert the result to an int

        super.move(elapsedTime); // Call the move method of the parent class to perform the regular movement
    }
}