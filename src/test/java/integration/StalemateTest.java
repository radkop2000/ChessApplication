package integration;

import MVC.ManagerUI;
import MVC.GameUI;
import MVC.Operator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.awt.*;
import java.awt.event.InputEvent;

import static java.lang.Thread.sleep;


class StalemateTest {

    @Test
    void testStalemate() throws InterruptedException {
        Operator op = new Operator();
//        op.window.setVisible(false);
        op.ui.showPanel(4);



        op.ui.game.board.againstComputer = false;
        op.ui.game.builder = false;
        op.ui.showPanel(ManagerUI.GAME);
        op.ui.game.board.setupClassic();
        op.ui.game.redrawBackground();
        op.ui.op.window.repaint();

        simulateClick();





        sleep(5000);
    }


    public void simulateClick(int x, int y) {
        JLabel label = op.ui.game.tiles[x][y]
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
}
