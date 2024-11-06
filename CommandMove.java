package com.csus.csc133;

public class CommandMove implements Command {
    private GameModel gameModel;

    public CommandMove(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    @Override
    public void execute() {
        gameModel.getPlayer().startMoving();
    }
}