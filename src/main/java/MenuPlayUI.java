import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuPlayUI extends JPanel{

    ManagerUI ui;
    JLabel background;

    public MenuPlayUI(ManagerUI ui) {
        this.ui = ui;
        setup();
    }

    public void setup() {

        setBounds(0,0,1366,768);
        setLayout(null);

        background = new JLabel();
        background.setBounds(0,0,1366,768);
        ImageIcon backgroundIcon = new ImageIcon("src/main/resources/MenuPlayPlayer.png");
        background.setIcon(backgroundIcon);

        JLabel buttonPlayer = new JLabel();
        buttonPlayer.setBounds(75, 351, 267, 50);
        buttonPlayer.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ui.game.board.againstComputer = false;
                ui.showPanel(ManagerUI.GAME);
                ui.game.board.setupClassic();
                ui.game.redrawBackground();
                ui.op.window.repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        add(buttonPlayer);

        JLabel buttonComputer = new JLabel();
        buttonComputer.setBounds(75, 412, 277, 45);
        buttonComputer.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ui.game.board.againstComputer = true;
                ui.showPanel(ManagerUI.GAME);
                ui.game.board.setupClassic();
                ui.game.redrawBackground();
                ui.op.window.repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        add(buttonComputer);

        JLabel buttonPGN = new JLabel();
        buttonPGN.setBounds(75, 470, 350, 45);
        buttonPGN.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ui.showPanel(ManagerUI.MENU_PLAY_PGN);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        add(buttonPGN);

        JLabel buttonBuilder = new JLabel();
        buttonBuilder.setBounds(75, 530, 333, 45);
        buttonBuilder.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ui.showPanel(ManagerUI.MENU_PLAY_BUILDER);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        add(buttonBuilder);

        JLabel buttonBack = new JLabel();
        buttonBack.setBounds(75, 586, 105, 45);
        buttonBack.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ui.showPanel(ManagerUI.MENU_MAIN);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        add(buttonBack);


        add(background);
        repaint();
    }

}
