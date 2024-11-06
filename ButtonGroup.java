package com.csus.csc133;

import java.util.ArrayList;
import java.util.List;

public class ButtonGroup {
    private List<CustomButton> buttons;

    public ButtonGroup(CustomButton... buttons) {
        this.buttons = new ArrayList<>();
        for (CustomButton button : buttons) {
            this.buttons.add(button);
        }
    }

    public void addButton(CustomButton button) {
        buttons.add(button);
    }

    public List<CustomButton> getButtons() {
        return buttons;
    }
}