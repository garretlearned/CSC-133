package com.csus.csc133;

import com.codename1.charts.util.ColorUtil;
import java.util.Random;

public class Student extends GameObject {
    protected static final int DEFAULT_SPEED = 800;
    protected static final int DEFAULT_TALKING_TIME = 400;

    protected int head;
    protected int speed = DEFAULT_SPEED;
    protected int talkingTime = DEFAULT_TALKING_TIME;
    protected int timeRemain = 0;
    protected int hydration = 100;
    protected int waterIntake;
    protected double sweatingRate = 0.05;
    protected int absenceTime = 0;
    protected boolean isTalking = false;

    public Student(int x, int y) {
        super(x, y);
        Random random = new Random();
        this.speed = DEFAULT_SPEED; // Set to a default or random speed
        this.head = new Random().nextInt(360); // Set to a random direction
        this.size = random.nextInt(21) + 40; // This will give a random number between 40 and 60
        this.color = ColorUtil.rgb(255, 0, 0); // Default color set to red
    }
    
    @Override
    public String toString() {
    	return getClass().getSimpleName() + "{" +
                "x=" + x +
                ", y=" + y +
                ", head=" + head +
                ", speed=" + speed +
                ", hydration=" + hydration +
                ", timeRemain=" + timeRemain +
                '}';
    }

    public int getHead() {
        return head;
    }

    public void setHead(int head) {
        this.head = head;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    public void startTalking(Student otherStudent) {
        this.isTalking = true;
        this.timeRemain = otherStudent.getTalkingTime();
    }

    public int getTalkingTime() {
        return this.talkingTime;
    }

    public void setTalkingTime(int talkingTime) {
        this.talkingTime = talkingTime;
    }

    public int getTimeRemaining() {
        return this.timeRemain;
    }

    public void setTimeRemain(int timeRemain) {
        this.timeRemain = timeRemain;
    }
    
    public void startTalking() {
        this.isTalking = true;
    }

    public void stopTalking() {
        this.isTalking = false;
    }
    
    public boolean isTalking() {
        // Return true if the student is talking, false otherwise.
        return talkingTime > 0;
    }

    public int getHydration() {
        return hydration;
    }

    public void setHydration(int hydration) {
        this.hydration = hydration;
    }

    public int getWaterIntake() {
        return waterIntake;
    }

    public void setWaterIntake(int waterIntake) {
        this.waterIntake = waterIntake;
    }

    public double getSweatingRate() {
        return sweatingRate;
    }

    public void setSweatingRate(int sweatingRate) {
        this.sweatingRate = sweatingRate;
    }

    public int getAbsenceTime() {
        return absenceTime;
    }

    public void setAbsenceTime(int absenceTime) {
        this.absenceTime = absenceTime;
    }


@Override
public void move(long elapsedTime) {
    // Talking logic
    if (isTalking && timeRemain > 0) {
        talkingTime -= elapsedTime;
        if (talkingTime <= 0) {
            isTalking = false;
            talkingTime = 0;
            head = (head +180) % 360;
        }
    }

    // Movement logic
    double angle = Math.toRadians(90 - head);
    double deltaX = Math.cos(angle) * speed * elapsedTime / 1000.0;
    double deltaY = Math.sin(angle) * speed * elapsedTime / 1000.0;
    super.x += deltaX;
    super.y += deltaY;

    if (super.x < 0) {
        super.x = 0;
        head = 'a';
    } else if (super.x > gamewidth) {
        super.x = gamewidth;
        head = 'd';
    }

    if (super.y < 0) {
        super.y = 0;
        head = 'w';
    } else if (super.y > gameheight) {
        super.y = gameheight;
        head = 's';
    }

    // Hydration logic
    hydration -= sweatingRate * elapsedTime / 1000.0;
}


    @Override
    public void handleCollide(Student s) {
        if (s instanceof StudentPlayer) {
            isTalking = true;
            talkingTime = DEFAULT_TALKING_TIME; // Set this to the desired talking time
        }
    }


    public void resetHydration() {
        this.hydration = 100;
    }

    public void drinkWater(int amount) {
        hydration += amount;
        if (hydration > 100) {
            waterIntake += (hydration - 100);
            hydration = 100;
        }
    }

    public void resetWaterIntake() {
        waterIntake = 0;
    }
    

    public void handleCollision(GameObject other) {
        if (other instanceof Student) {
            this.timeRemain = this.talkingTime;
        }
    }
    
}