
package com.csus.csc133;

public class StudentSleeping extends Student {
    public StudentSleeping(int x, int y) {
        super(x, y);
    }

    @Override
    public void move(long elapsedTime) {
        // This student does not move
    }
}
