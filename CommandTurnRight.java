
package com.csus.csc133;

public class CommandTurnRight implements Command {
    private GameModel gameModel;

    public CommandTurnRight(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    @Override
    public void execute() {
        int currentHead = gameModel.getPlayer().getHead();
        gameModel.getPlayer().setHead(currentHead - 90); // turn right by 90 degrees
    }
}
