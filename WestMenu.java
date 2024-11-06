
package com.csus.csc133;

import com.codename1.ui.Container;
import com.codename1.ui.layouts.BoxLayout;

public class WestMenu {
    private Container buttonsContainer;
    private Command moveCommand;
    private Command stopCommand;
    private Command turnLeftCommand;
    private Command turnRightCommand;
    private Command changeStrategiesCommand;
    private Command aboutCommand;
    private Command exitCommand;
    private Command pauseCommand;
    private Command changePositionCommand;

    public WestMenu(GameModel gm) {
        buttonsContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        moveCommand = new CommandMove(gm);
        stopCommand = new CommandStop(gm);
        turnLeftCommand = new CommandTurnLeft(gm);
        turnRightCommand = new CommandTurnRight(gm);
        changeStrategiesCommand = new CommandChangeStrategies(gm);
        aboutCommand = new CommandAbout();
        exitCommand = new CommandExit();
        pauseCommand = new CommandPause(gm);
        changePositionCommand = new CommandChangePosition(gm);

        CustomButton moveButton = new CustomButton("Move", moveCommand::execute);
        CustomButton stopButton = new CustomButton("Stop", stopCommand::execute);
        CustomButton turnLeftButton = new CustomButton("Turn Left", turnLeftCommand::execute);
        CustomButton turnRightButton = new CustomButton("Turn Right", turnRightCommand::execute);
        CustomButton changeStrategiesButton = new CustomButton("Change Strategies", changeStrategiesCommand::execute);
        CustomButton aboutButton = new CustomButton("About", aboutCommand::execute);
        CustomButton exitButton = new CustomButton("Exit", exitCommand::execute);
        CustomButton pauseButton = new CustomButton("Pause", pauseCommand::execute);
        CustomButton changePositionButton = new CustomButton("Change Position", changePositionCommand::execute);

        buttonsContainer.addAll(moveButton, stopButton, turnLeftButton, turnRightButton,
                changeStrategiesButton, aboutButton, exitButton, pauseButton, changePositionButton);
    }

    public Container getButtonsContainer() {
        return buttonsContainer;
    }
}
