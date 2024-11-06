package com.csus.csc133;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;

public class SideMenu {
    private GameModel gameModel;
    private Container sideMenuContainer;
    private Command changeStrategiesCommand;
    private Command aboutCommand;
    private Command exitCommand;

    public SideMenu(GameModel gameModel) {
        this.gameModel = gameModel;
        sideMenuContainer = new Container(new BorderLayout());
        sideMenuContainer.setUIID("SideMenuContainer");

        Container buttonsContainer = new Container(new FlowLayout(Component.CENTER));
        buttonsContainer.setUIID("SideMenuButtonsContainer");

        changeStrategiesCommand = new CommandChangeStrategies(gameModel);
        aboutCommand = new CommandAbout();
        exitCommand = new CommandExit();

        CustomButton changeStrategiesButton = createButton("Change Strategies", changeStrategiesCommand::execute);
        CustomButton aboutButton = createButton("About", aboutCommand::execute);
        CustomButton exitButton = createButton("Exit", exitCommand::execute);

        buttonsContainer.add(changeStrategiesButton);
        buttonsContainer.add(aboutButton);
        buttonsContainer.add(exitButton);

        sideMenuContainer.add(BorderLayout.NORTH, buttonsContainer);

        // Apply styling
        Style containerStyle = sideMenuContainer.getUnselectedStyle();
        containerStyle.setPadding(2, 2, 2, 2);
        containerStyle.setMargin(2, 0, 0, 0);
    }

    public Container getSideMenuContainer() {
        return sideMenuContainer;
    }

    private CustomButton createButton(String label, Runnable action) {
        return new CustomButton(label, action);
    }
}