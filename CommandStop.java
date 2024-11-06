package com.csus.csc133;

public class CommandStop implements Command {
    private GameModel gameModel;

    public CommandStop(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    @Override
    public void execute() {
        gameModel.getPlayer().stopMoving();
    }
}
