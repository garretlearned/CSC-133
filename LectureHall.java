package com.csus.csc133;

public class LectureHall extends Facility {

    private String name;
    private Lecture lecture;

    public LectureHall(String name, int x, int y) {
        super(x, y);
        this.name = name;
        this.lecture = Lecture.getInstance();
        this.size = 90; // Set size to 90
    }

    @Override
    public String toString() {
        return "LectureHall{" +
                "x=" + x +
                ", y=" + y +
                ", name='" + name + '\'' +
                ", LecTimeRemaining=" + (getLecTimeRemaining() > 0 ? getLecTimeRemaining() : null) +
                '}';
    }

    @Override
    public void handleCollide(Student s) {
        if (this.x == s.x && this.y == s.y) {
            System.out.println("Student collided with the Lecture Hall.");
            if (s instanceof StudentPlayer) {
                // End the current lecture immediately
                this.lecture.Ongoinglecture = false;
                this.lecture.LecTimeRemaining = 0;
            }
        }
    }
    
	public String getName() {
		return name;
	}
	
	public int getLecTimeRemaining() {
		return this.lecture.LecTimeRemaining;
	}
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getSize() {
        return this.size;
    }
    
    @Override
    public void move(long elapsedTime) {
        // Implement your logic here
    }
}