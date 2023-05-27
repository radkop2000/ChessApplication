package test;

import MVC.ManagerUI;
import MVC.Operator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static java.lang.Thread.sleep;


public class StalemateTest {

    @Test
    public void testStalemate() throws InterruptedException {
        Operator op = new Operator();
//        op.window.setVisible(false);
        op.ui.showPanel(4);



        op.ui.game.board.againstComputer = false;
        op.ui.game.builder = false;
        op.ui.showPanel(ManagerUI.GAME);
        op.ui.game.board.setupClassic();
        op.ui.game.redrawBackground();
        op.ui.op.window.repaint();


        sleep(5000);
    }


}
