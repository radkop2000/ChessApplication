import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuPlayUI extends JPanel{

    ManagerUI ui;
    boolean isComputer;
    JLabel background;

    public MenuPlayUI(ManagerUI ui) {
        this.ui = ui;
        setup();
    }

    public void setup() {

        setBounds(0,0,1366,768);
        setLayout(null);
        isComputer = true;

        background = new JLabel();
        background.setBounds(0,0,1366,768);
        ImageIcon backgroundIcon = new ImageIcon("src/main/resources/MenuPlayComputer.png");
        background.setIcon(backgroundIcon);

        JLabel buttonChanger = new JLabel();
        buttonChanger.setBounds(75, 351, 267, 50);
        buttonChanger.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changeAgainst();
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
        add(buttonChanger);

        JLabel buttonClassic = new JLabel();
        buttonClassic.setBounds(75, 412, 277, 45);
        buttonClassic.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
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
        add(buttonClassic);

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

    private void changeAgainst() {

        if (isComputer) {
            ui.game.board.againstComputer = false;
            ImageIcon icon = new ImageIcon("src/main/resources/MenuPlayPlayer.png");
            background.setIcon(icon);
        } else {
            ui.game.board.againstComputer = true;
            ImageIcon icon = new ImageIcon("src/main/resources/MenuPlayComputer.png");
            background.setIcon(icon);
        }
        isComputer = !isComputer;
    }
}
