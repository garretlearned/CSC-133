package com.csus.csc133;

public class CommandLectureHall implements Command {
    private GameModel gameModel;

    public CommandLectureHall(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    @Override
    public void execute() {
        gameModel.simulateLectureHallCollision();
    }
}