package com.csus.csc133;

public class CommandRestroom implements Command {
    private GameModel gameModel;

    public CommandRestroom(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    @Override
    public void execute() {
        gameModel.simulateRestroomCollision();
    }
}