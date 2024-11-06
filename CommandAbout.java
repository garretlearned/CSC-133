package com.csus.csc133;

import com.codename1.ui.Dialog;

public class CommandAbout implements Command {
    @Override
    public void execute() {
        Dialog.show("About", "A2. Garret Learned. Summer 2024.", "OK", null);
    }
}