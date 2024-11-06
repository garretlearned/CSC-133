package com.csus.csc133;

import com.codename1.ui.Button;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.Border;

public class CustomButton extends Button {
    public CustomButton(String label, Runnable action) {
        super(label);
        setUIID("CustomButton"); // Set the UIID to customize the visual look

        Style buttonStyle = getAllStyles();
        buttonStyle.setPadding(2, 2, 2, 2); // Smaller padding
        buttonStyle.setMargin(2, 2, 2, 2); // Smaller margin
        buttonStyle.setBorder(Border.createEmpty());
        buttonStyle.setBgColor(0x0000FF); // Blue background color
        buttonStyle.setFgColor(0xFFFFFF); // White text color
        buttonStyle.setBgTransparency(255); // Solid background

        addActionListener(e -> action.run());
    }
}