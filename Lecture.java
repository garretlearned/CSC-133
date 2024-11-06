package com.csus.csc133;

import java.util.Random;

public class Lecture {

    private static Lecture instance;

    public boolean Ongoinglecture;
    public int LecTimeRemaining;

    private Lecture() {
        Ongoinglecture = false;
        LecTimeRemaining = 0;
    }

    public static Lecture getInstance() {
        if (instance == null) {
            instance = new Lecture();
        }
        return instance;
    }
    
    public int getLecTimeRemaining() {
        return LecTimeRemaining;
    }


    public void spawnLecture() {
        Random random = new Random();
        if (!Ongoinglecture && random.nextDouble() <= 0.1) {
            Ongoinglecture = true;
            LecTimeRemaining = 20;
        }
    }
}