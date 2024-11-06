package com.csus.csc133;

import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;

public class CommandStudent implements Command {
    private GameModel gameModel;
    private  Form form;
    private TextField inputField; // Create a single TextField instance

    public CommandStudent(GameModel gameModel, Form form) {
        this.gameModel = gameModel;
        this.form = form;
        this.inputField = new TextField(); // Initialize the TextField
        this.inputField.setHint("Enter the number of the student type (0-9):");
        this.inputField.addDataChangedListener((i1, i2) -> {
            try {
                int studentType = Integer.parseInt(inputField.getText());
                if (studentType < 0 || studentType > 9) {
                    throw new NumberFormatException();
                }
                gameModel.selectStudent(studentType);
            } catch (NumberFormatException e) {
                Dialog.show("Error", "Invalid input. Please enter a number between 0 and 9.", "OK", null);
            }
        });
    }

    @Override
    public void execute() {
        form.add(BorderLayout.CENTER, inputField); // Add the TextField to the form);
        form.show();
    }
}