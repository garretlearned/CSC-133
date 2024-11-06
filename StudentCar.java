
package com.csus.csc133;

import java.util.Random;

public class StudentCar extends Student {
    private static final Random random = new Random();
    private int gamewidth;

    public StudentCar(int x, int y, int gamewidth) {
        super(x, y);
        this.gamewidth = gamewidth;
        this.speed = DEFAULT_SPEED * 5;
        this.sweatingRate = 0;
    }

    public StudentCar() {
        super(0, 0);
    }

    @Override
    public void move(long elapsedTime) {
        // StudentCar can only move horizontally, so set the head to either 90 or 270
        this.head = (random.nextDouble() < 0.5) ? 90 : 270;

        // Calculate the new position based on the head and speed
        double newX = this.x + Math.cos(Math.toRadians(90 - this.head)) * this.speed * elapsedTime;
        double newY = this.y;
        // Update the position if it is within the valid range
        if (newX >= 0 && newX <= gamewidth) {
            this.x = (int) newX;
            this.y = (int) newY;
        }
    }

}
