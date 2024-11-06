package com.csus.csc133;

public class CommandChangePosition implements Command {
    private GameModel gameModel;

    public CommandChangePosition(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    @Override
    public void execute() {
        gameModel.setChangePositionMode(true);
    }
}