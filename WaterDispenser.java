package com.csus.csc133;

public class WaterDispenser extends Facility {
	
	public WaterDispenser(int x, int y) {
	    super(x, y);
	    this.size = 40; // Set size
	}
	
	@Override
    public String toString() {
        return "WaterDispenser{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
	
	@Override
	public void handleCollide(Student s) {
	    if (this.x == s.x && this.y == s.y) {
	        s.drinkWater(10); // Student drinks water, raising their hydration level by 10
	    }
	}
	
	@Override
	public void move(long elapsedTime) {
	    // Implement your logic here
	}
}