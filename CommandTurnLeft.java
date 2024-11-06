
package com.csus.csc133;

public class CommandTurnLeft implements Command {
    private GameModel gameModel;

    public CommandTurnLeft(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    @Override
    public void execute() {
        int currentHead = gameModel.getPlayer().getHead();
        gameModel.getPlayer().setHead(currentHead + 90); // turn left by 90 degrees
    }
}
