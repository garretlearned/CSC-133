package com.csus.csc133;

import java.util.Random;

public class StudentHappy extends Student {
    private static final Random random = new Random();

    public StudentHappy(int x, int y) {
        super(x, y);
        // Constructor code specific to StudentHappy
    }

    @Override
    public void move(long elapsedTime) {
        if (shouldJumpForward()) {
            int previousSpeed = speed;
            speed *= 2;
            super.move(elapsedTime);
            speed = previousSpeed;
        } else {
            super.move(elapsedTime);
        }
    }

    private boolean shouldJumpForward() {
        double probability = 0.25;
        return random.nextDouble() < probability;
    }
}