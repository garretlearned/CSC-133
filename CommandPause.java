package com.csus.csc133;

public class CommandPause implements Command {
    private GameModel gameModel;

    public CommandPause(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    @Override
    public void execute() {
        gameModel.setPaused(!gameModel.isPaused());
    }
}