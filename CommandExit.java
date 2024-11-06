
package com.csus.csc133;

import com.codename1.ui.Dialog;
import com.codename1.ui.Display;

public class CommandExit implements Command {
    @Override
    public void execute() {
        boolean confirmExit = Dialog.show("Confirm Exit", "Are you sure you want to exit?", "Yes", "No");
        if (confirmExit) {
            Display.getInstance().exitApplication();
        }
    }
}
