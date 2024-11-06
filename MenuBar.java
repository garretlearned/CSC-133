package com.csus.csc133;

public class MenuBar {
    private Command lectureHallCommand;
    private Command aboutCommand;
    private GameModel gameModel;

    public MenuBar() {
        this.gameModel = new GameModel();
        lectureHallCommand = new CommandLectureHall(gameModel);
        aboutCommand = new CommandAbout();
    }

    public void executeLectureHallCommand() {
        lectureHallCommand.execute();
    }

    public void executeAboutCommand() {
        aboutCommand.execute();
    }
}