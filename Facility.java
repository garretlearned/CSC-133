package com.csus.csc133;

public abstract class Facility extends GameObject {
    public Facility(int x, int y) {
        super(x, y);
    }
    
    @Override
    public void move(long elapsedTime) {
    	//do nothing
    	//System.out.println("FACILITY DOES NOT MOVE");
    }
}
