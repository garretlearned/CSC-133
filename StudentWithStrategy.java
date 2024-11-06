package com.csus.csc133;

public class StudentWithStrategy extends Student {
    private Strategy strategy;
    private Direction direction;
    
    public StudentWithStrategy(int x, int y, Strategy strategy) {
        super(x, y);
        this.strategy = strategy;
        this.direction = Direction.NORTH;
    }
    
    public Direction getDirection() {
        return this.direction;
    }
    
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    @Override
    public void move(long elapsedTime) {
        strategy.apply(this);
        super.move(elapsedTime);
    }

    @Override
    public String toString() {
        return super.toString() + ", Current Strategy: " + strategy.getClass().getSimpleName();
    }
}