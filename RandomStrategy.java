package com.csus.csc133;

import java.util.Random;

public class RandomStrategy implements Strategy {
    private Random random = new Random();

    @Override
    public void apply(StudentWithStrategy student) {
        // Change the direction of the student randomly
        int direction = random.nextInt(4);
        student.setDirection(Direction.values()[direction]);
    }
}