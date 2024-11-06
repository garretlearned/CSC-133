
package com.csus.csc133;

import com.codename1.ui.*;
import com.codename1.ui.layouts.*;
import com.codename1.ui.util.UITimer;
import java.util.Observer;
import java.util.Observable;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.ActionEvent;

public class SacRun extends Form implements Observer, Runnable, ActionListener {
    private GameModel gm;
    private ViewMap viewMap;
    private ViewStatus viewStatus;
    private ViewMessage viewMessage;
    private WestMenu westMenu;
    private SideMenu sideMenu;
    private Command moveCommand;
    private Command stopCommand;
    private Command turnLeftCommand;
    private Command turnRightCommand;

    public SacRun() {
        gm = new GameModel();
        viewMap = new ViewMap(gm);
        gm.addObserver(viewMap);
        viewStatus = new ViewStatus(gm);
        gm.addObserver(viewStatus);
        viewMessage = new ViewMessage();
        gm.addObserver(viewMessage);
        viewMessage.update(gm, null);
        gm.notifyChanges();
        westMenu = new WestMenu(gm);
        sideMenu = new SideMenu(gm);

        moveCommand = new CommandMove(gm);
        stopCommand = new CommandStop(gm);
        turnLeftCommand = new CommandTurnLeft(gm);  // Ensure this is initialized before it's used
        turnRightCommand = new CommandTurnRight(gm);

        setupUI();

        UITimer timer = new UITimer(this);
        timer.schedule(20, true, this);

        this.setScrollable(false);
        this.setFocusable(true);
        this.addKeyListener('w', evt -> moveCommand.execute());
        this.addKeyListener('a', evt -> turnLeftCommand.execute());
        this.addKeyListener('s', evt -> stopCommand.execute());
        this.addKeyListener('d', evt -> turnRightCommand.execute());

        this.addPointerPressedListener(e -> {
            // Handle pointer pressed event
            int x = e.getX();
            int y = e.getY();
            if (gm.isChangePositionMode()) {
                viewMap.changeSelectedObjectPosition(x, y);
                gm.setChangePositionMode(false);
            } else {
                GameObjectCollection.Iterator iterator = gm.getGameObjects().getIterator();
                boolean objectSelected = false;
                while (iterator.hasNext()) {
                    GameObject gameObject = iterator.getNext();
                    if (x >= gameObject.getX() && x <= gameObject.getX() + gameObject.getSize() &&
                        y >= gameObject.getY() && y <= gameObject.getY() + gameObject.getSize()) {
                        gameObject.select();
                        objectSelected = true;
                    } else {
                        gameObject.deselect();
                    }
                }
                if (!objectSelected) {
                    iterator = gm.getGameObjects().getIterator();
                    while (iterator.hasNext()) {
                        GameObject gameObject = iterator.getNext();
                        gameObject.deselect();
                    }
                }
                viewMap.repaint();
            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof GameModel) {

            // Call methods to update the UI based on the new state of the gameModel
            viewMap.repaint();
            //viewStatus.updateGameModelSize();
        }
    }

    private void setupUI() {
        this.setLayout(new BorderLayout());
        this.add(BorderLayout.SOUTH, viewMessage.getMessageContainer());
        this.add(BorderLayout.CENTER, viewMap);
        Container eastContainer = new Container(new BorderLayout());
        this.add(BorderLayout.EAST, eastContainer);
        this.add(BorderLayout.EAST, viewStatus.getStatusContainer());
        this.add(BorderLayout.WEST, westMenu.getButtonsContainer());
        this.add(BorderLayout.NORTH, sideMenu.getSideMenuContainer());
        this.show();
        viewMap.updateGameModelSize();
        viewMap.repaint();
    }

    @Override
    public void run() {
        gm.nextFrame();
        viewMap.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Implement the logic that should occur when an action is performed
    }
}
