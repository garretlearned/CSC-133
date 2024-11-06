
package com.csus.csc133;

public class CommandNextFrame implements Command {
    private GameModel gameModel;

    public CommandNextFrame(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    @Override
    public void execute() {
        gameModel.nextFrame();
    }
}
