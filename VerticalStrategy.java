package com.csus.csc133;

public class VerticalStrategy implements Strategy {
    @Override
    public void apply(StudentWithStrategy student) {
        // Change the direction of the student vertically
        if (student.getDirection() == Direction.NORTH || student.getDirection() == Direction.SOUTH) {
            return;
        }
        student.setDirection(Direction.NORTH);
    }
}