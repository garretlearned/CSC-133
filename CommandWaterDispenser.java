
package com.csus.csc133;

public class CommandWaterDispenser implements Command {
    private GameModel gameModel;

    public CommandWaterDispenser(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    @Override
    public void execute() {
        gameModel.simulateWaterDispenserCollision();
    }
}
