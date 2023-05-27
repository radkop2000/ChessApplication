package test.java;

import MVC.ManagerUI;
import MVC.Operator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;

import static java.lang.Thread.sleep;

public class Clicker {

    public Operator op;

    public Clicker() {
        op = new Operator();
        op.ui.showPanel(4);
        op.ui.game.board.againstComputer = false;
        op.ui.game.builder = false;
        op.ui.showPanel(ManagerUI.GAME);
        op.ui.game.board.setupClassic();
        op.ui.game.redrawBackground();
        op.ui.op.window.repaint();
    }

    public void simulateTileClick(int x, int y) {
        JLabel label = op.ui.game.tiles[x][y];
        try {
            Robot robot = new Robot();
            Point point = label.getLocationOnScreen();
            robot.mouseMove(point.x + label.getWidth() / 2, point.y + label.getHeight() / 2);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        } catch (AWTException ex) {
            ex.printStackTrace();
        }
    }

    public void simulateClick(int x, int y) {
        try {
            Robot robot = new Robot();
            robot.mouseMove(x,y);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
        } catch (AWTException ex) {
            ex.printStackTrace();
        }
    }
}
