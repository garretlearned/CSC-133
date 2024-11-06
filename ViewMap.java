
package com.csus.csc133;

import java.util.Observable;
import com.codename1.ui.Graphics;
import java.util.Observer;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.charts.util.ColorUtil;

public class ViewMap extends Container implements Observer {
    private GameModel gameModel;

    public ViewMap(GameModel gameModel) {
        this.gameModel = gameModel;
        this.setFocusable(true);
        gameModel.addObserver(this); // Add this line to register ViewMap as an observer of GameModel
    }

    public ViewMap() {
        // constructor implementation
        this.setLayout(new BorderLayout());
        this.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.rgb(255, 0, 0)));
        this.setWidth(gameModel.getWidth() * 90 / 100); // Set the width of the map to be 90% of the width of the Form
        this.setHeight(gameModel.getHeight() * 90 / 100); // Set the height of the map to be 90% of the height of the Form
    }

    @Override
    public void update(Observable o, Object arg) {
        printGameObjects();
    }

    public void printGameObjects() {
        GameObjectCollection.Iterator iterator = gameModel.getGameObjects().getIterator();
        while (iterator.hasNext()) {
            GameObject gameObject = iterator.getNext();
            System.out.println(gameObject.toString());
        }
    }

    public void updateGameModelSize() {
        //this.gameModel = gameModel;
        this.repaint();
    }
    
    @Override
    public void pointerPressed(int x, int y) {
        if (gameModel.isChangePositionMode()) {
            changeSelectedObjectPosition(x, y);
            gameModel.setChangePositionMode(false);
        } else {
        GameObjectCollection.Iterator iterator = gameModel.getGameObjects().getIterator();
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
            iterator = gameModel.getGameObjects().getIterator();
            while (iterator.hasNext()) {
                GameObject gameObject = iterator.getNext();
                gameObject.deselect();
            }
        }
        repaint();
    }
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(ColorUtil.BLUE); // Set color to blue
        g.fillRect(0, 0, getWidth(), getHeight()); // Draw a big blue rectangle covering the entire container

        // Iterate over all GameObjects and draw them
        GameObjectCollection.Iterator iterator = gameModel.getGameObjects().getIterator();
        while (iterator.hasNext()) {
            GameObject gameObject = iterator.getNext();
            int x = gameObject.getX();
            int y = gameObject.getY();
            int size = gameObject.getSize();

            if (gameObject.isSelected()) {
                g.setColor(ColorUtil.rgb(255, 0, 0)); // Red color for selected objects
                g.drawRect(x, y, size, size); // Draw outline of selected object
            }

            // Draw different types of game objects
            if (gameObject instanceof LectureHall) {
                g.setColor(ColorUtil.BLUE);
                g.fillRect(x, y, size, size);
                g.drawString(((LectureHall) gameObject).getName(), x, y + size);
            } else if (gameObject instanceof WaterDispenser) {
                g.setColor(ColorUtil.CYAN);
                g.fillArc(x, y, size, size, 0, 360);
            } else if (gameObject instanceof Restroom) {
                g.setColor(ColorUtil.GREEN);
                g.fillRect(x, y, size, size);
            } else if (gameObject instanceof Student) {
                int[] xPoints = {x, x + size / 2, x + size};
                int[] yPoints = {y + size, y, y + size};
                if (gameObject instanceof StudentPlayer) {
                    g.setColor(ColorUtil.rgb(255, 0, 0)); // Red for StudentPlayer
                    g.fillPolygon(xPoints, yPoints, 3);
                } else {
                    g.setColor(ColorUtil.rgb(255, 105, 180)); // Pink for other students
                    g.fillPolygon(xPoints, yPoints, 3);
                }
            }
        }
    }
    
    
    /*
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        GameObjectCollection.Iterator iterator = gameModel.getGameObjects().getIterator();
        while (iterator.hasNext()) {
            GameObject gameObject = iterator.getNext();
            int x = gameObject.getX() - gameObject.getSize() / 2;
            int y = gameObject.getY() - gameObject.getSize() / 2;
            
            if (gameObject.isSelected()) {
                g.setColor(ColorUtil.rgb(255, 0, 0)); // Red color
                g.drawRect(gameObject.getX(), gameObject.getY(), gameObject.getSize(), gameObject.getSize());

            if (gameObject instanceof LectureHall) {
                g.setColor(ColorUtil.BLUE);
                g.fillRect(x, y, gameObject.getSize(), gameObject.getSize());
                g.drawString(((LectureHall) gameObject).getName(), x, y + gameObject.getSize());
            } else if (gameObject instanceof WaterDispenser) {
                g.setColor(ColorUtil.BLUE);
                g.fillArc(x, y, gameObject.getSize(), gameObject.getSize(), 0, 360);
            } else if (gameObject instanceof Restroom) {
                g.setColor(ColorUtil.GREEN);
                g.fillRect(x, y, gameObject.getSize(), gameObject.getSize());
            } else if (gameObject instanceof Student) {
                int[] xPoints = {x, x + gameObject.getSize() / 2, x + gameObject.getSize()};
                int[] yPoints = {y + gameObject.getSize(), y, y + gameObject.getSize()};
                if (gameObject instanceof StudentPlayer) {
                    g.setColor(ColorUtil.rgb(255, 0, 0)); // Red color for StudentPlayer
                    g.fillPolygon(xPoints, yPoints, 3);
                } else {
                    g.setColor(ColorUtil.rgb(255, 105, 180)); // Pink color for other students
                    g.drawPolygon(xPoints, yPoints, 3);
                }
            }
        }
    }
    }*/
    public void changeSelectedObjectPosition(int x, int y) {
        GameObjectCollection.Iterator iterator = gameModel.getGameObjects().getIterator();
        while (iterator.hasNext()) {
            GameObject gameObject = iterator.getNext();
            if (gameObject.isSelected()) {
                gameObject.setX(x);
                gameObject.setY(y);
            }
        }
    }
}
