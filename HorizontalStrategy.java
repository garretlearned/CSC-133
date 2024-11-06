package com.csus.csc133;

public class HorizontalStrategy implements Strategy {
    @Override
    public void apply(StudentWithStrategy student) {
        // Change the direction of the student horizontally
        if (student.getDirection() == Direction.EAST || student.getDirection() == Direction.WEST) {
            return;
        }
        student.setDirection(Direction.EAST);
    }
}