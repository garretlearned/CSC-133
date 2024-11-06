package com.csus.csc133;

import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import java.util.Observable;
import java.util.Observer;
import com.codename1.ui.Button;
import com.codename1.ui.Component;

public class ViewStatus extends Container implements Observer {
    private Container statusContainer;
    private Container buttonsContainer;
    private Container valuesContainer;
    private Label lectureTimeLabel;
    private Label lectureHallLabel;
    private Label lectureTimeRemainingLabel;
    private Label gameTimeLabel;
    private Label absenceLabel;
    private Label hydrationLabel;
    private Label waterIntakeLabel;
    private Label remainingTimeLabel;

    public ViewStatus(GameModel gameModel) {
        statusContainer = new Container(new BorderLayout());
        statusContainer.setUIID("StatusContainer");

        buttonsContainer = new Container(new FlowLayout(Component.CENTER));
        Button lectureHallButton = new Button("Lecture Hall");
        Button aboutButton = new Button("About");
        CommandLectureHall commandLectureHall = new CommandLectureHall(gameModel);
        lectureHallButton.addActionListener(e -> commandLectureHall.execute());
        CommandAbout commandAbout = new CommandAbout();
        aboutButton.addActionListener(e -> commandAbout.execute());
        buttonsContainer.addAll(lectureHallButton, aboutButton);

        valuesContainer = new Container(new GridLayout(7, 1));
        valuesContainer.setUIID("ValuesContainer");

        statusContainer.add(BorderLayout.NORTH, buttonsContainer);
        //statusContainer.add(BorderLayout.CENTER, valuesContainer);

        lectureHallLabel = new Label("Lecture Hall: Placeholder");
        lectureTimeLabel = new Label("Lecture Time: 00:00");
        lectureTimeRemainingLabel = new Label("Lecture Time Remaining: 00:00"); // Initialize the label
        gameTimeLabel = new Label("Game Time: 00:00");
        absenceLabel = new Label("Absence: 0");
        hydrationLabel = new Label("Hydration: 100%");
        waterIntakeLabel = new Label("Water Intake: 0 ml");
        remainingTimeLabel = new Label("Remaining Time: 00:00");

        valuesContainer.add(lectureHallLabel);
        valuesContainer.add(lectureTimeRemainingLabel); // Add the label to the container
        valuesContainer.add(gameTimeLabel);
        valuesContainer.add(absenceLabel);
        valuesContainer.add(hydrationLabel);
        valuesContainer.add(waterIntakeLabel);
        valuesContainer.add(remainingTimeLabel);

        Style labelStyle = lectureHallLabel.getUnselectedStyle();
        labelStyle.setFgColor(0x000000); // Black

        labelStyle = lectureTimeLabel.getUnselectedStyle();
        labelStyle.setFgColor(0x000000); // Black

        labelStyle = gameTimeLabel.getUnselectedStyle();
        labelStyle.setFgColor(0x000000); // Black

        labelStyle = absenceLabel.getUnselectedStyle();
        labelStyle.setFgColor(0x000000); // Black

        labelStyle = hydrationLabel.getUnselectedStyle();
        labelStyle.setFgColor(0x000000); // Black

        labelStyle = waterIntakeLabel.getUnselectedStyle();
        labelStyle.setFgColor(0x000000); // Black

        labelStyle = remainingTimeLabel.getUnselectedStyle();
        labelStyle.setFgColor(0x000000); // Black

        statusContainer.add(BorderLayout.WEST, valuesContainer);

        // Apply styling
        Style containerStyle = statusContainer.getUnselectedStyle();
        containerStyle.setPadding(0, 0, 0, 0);
        containerStyle.setMargin(0, 0, 0, 0);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof GameModel) {
            GameModel gameModel = (GameModel) o;

            // Get the necessary information from the gameModel
            LectureHall lectureHall = gameModel.getLectureHall();
            long gameTime = gameModel.getGameTime();
            int absenceTime = gameModel.getPlayer().getAbsenceTime();
            int hydration = gameModel.getPlayer().getHydration();
            int waterIntake = gameModel.getPlayer().getWaterIntake();
            int remainingTime = gameModel.getPlayer().getTimeRemaining();

            // Update the labels with the current values
            lectureHallLabel.setText("Lecture Hall: " + lectureHall.getName());
            lectureTimeLabel.setText("Lecture Time: " + (remainingTime > 0 ? String.valueOf(remainingTime) : "00:00"));
            gameTimeLabel.setText("Game Time: " + gameTime);
            absenceLabel.setText("Absence: " + absenceTime);
            hydrationLabel.setText("Hydration: " + hydration);
            waterIntakeLabel.setText("Water Intake: " + waterIntake);
            if (gameModel.getPlayer().isTalking()) {
                remainingTimeLabel.setText("Remaining Time: " + gameModel.getPlayer().getTalkingTime());
            } else {
                remainingTimeLabel.setText("Remaining Time: 00:00");
            }

            Lecture lecture = Lecture.getInstance();
            lectureTimeRemainingLabel.setText("Lecture Time Remaining: " + lecture.getLecTimeRemaining()); // update new label
        }
    }

    public Container getStatusContainer() {
        return statusContainer;
    }
}