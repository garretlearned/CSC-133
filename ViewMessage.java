package com.csus.csc133;

import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;

import java.util.Observable;
import java.util.Observer;

public class ViewMessage implements Observer {
    private Container messageContainer;
    private Label messageLabel;

    public ViewMessage() {
        messageContainer = new Container(new LayeredLayout());
        messageContainer.setUIID("MessageContainer");
        messageContainer.setHeight(50);
        messageContainer.setPreferredH(50);
        messageContainer.setVisible(true);

        messageLabel = new Label();
        messageLabel.setUIID("MessageLabel");

        messageContainer.add(messageLabel);

        // Apply styling
        Style containerStyle = messageContainer.getUnselectedStyle();
        containerStyle.setPadding(0, 0, 0, 0);
        containerStyle.setMargin(0, 0, 0, 0);

        Style labelStyle = messageLabel.getUnselectedStyle();
        labelStyle.setFgColor(0x000000); // Black
        
       //essageContainer.getUnselectedStyle().setBgColor(0x00FF00); // Set to green
       //essageContainer.getUnselectedStyle().setBgTransparency(255); // Make it visible
        
        messageContainer.revalidate();
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof GameModel) {
            GameModel gameModel = (GameModel) o;
            String message = gameModel.getCollisionMessage();
            if (message != null) {
                messageLabel.setText(message);
                messageContainer.revalidate();
                messageContainer.repaint();
            }
        }
    }

    public Container getMessageContainer() {
        return messageContainer;
    }
}