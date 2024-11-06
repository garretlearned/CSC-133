package com.csus.csc133;

import java.util.Random;

public class CommandChangeStrategies implements Command {
    private GameModel gameModel;
    private Strategy[] strategies;
    private Random random;

    public CommandChangeStrategies(GameModel gameModel) {
        this.gameModel = gameModel;
        this.strategies = new Strategy[] {
            new RandomStrategy(),
            new VerticalStrategy(),
            new HorizontalStrategy()
        };
        this.random = new Random();
    }

    @Override
    public void execute() {
        for (StudentWithStrategy student : gameModel.getStudentsWithStrategy()) {
            int strategyIndex = random.nextInt(strategies.length);
            student.setStrategy(strategies[strategyIndex]);
        }
    }
}