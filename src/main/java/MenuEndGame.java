import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class MenuEndGame {

    MenuUI ui;

    JPanel mainPanel;
    JLabel backgroundW;
    JLabel backgroundB;
    JLabel backgroundT;

    JLabel activeLabel;

    public MenuEndGame(MenuUI ui) {
        this.ui = ui;
        setup();
    }

    public void setup() {
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBounds(0,0,1366,768);

        backgroundW = new JLabel();
        backgroundW.setBounds(0,0,1366,768);
        ImageIcon iconW = new ImageIcon("src/main/resources/endGameBackgroundW.png");
        backgroundW.setIcon(iconW);

        backgroundB = new JLabel();
        backgroundB.setBounds(0,0,1366,768);
        ImageIcon iconB = new ImageIcon("src/main/resources/endGameBackgroundB.png");
        backgroundB.setIcon(iconB);

        backgroundT = new JLabel();
        backgroundT.setBounds(0,0,1366,768);
        ImageIcon iconT = new ImageIcon("src/main/resources/endGameBackgroundT.png");
        backgroundT.setIcon(iconT);

        // region buttonShow
        JLabel buttonShow = new JLabel();
        buttonShow.setBounds(81, 353, 238, 40);
        buttonShow.setBackground(Color.white);
        buttonShow.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    System.out.println("NFIOEB NFUIEHUFUE");
                    ui.gm.gameUI.show();
                }
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
        mainPanel.add(buttonShow);
//        endregion

        //        region buttonSave
        JLabel buttonSave = new JLabel();
        buttonSave.setBounds(81, 414, 210, 40);
        buttonSave.setBackground(Color.white);
        buttonSave.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    try {
                        ui.gm.ch.pgn.savePGN();
                    } catch (IOException ignored) {

                    }
                }
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
        mainPanel.add(buttonSave);

//        endregion

        // region buttonCopy
        JLabel buttonCopy = new JLabel();
        buttonCopy.setBounds(81, 470, 220, 46);
        buttonCopy.setBackground(Color.white);
        buttonCopy.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    ui.gm.ch.pgn.copyPGN();
                }
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
        mainPanel.add(buttonCopy);

//        endregion

        //        region buttonBack
        JLabel buttonBack = new JLabel();
        buttonBack.setBounds(81, 530, 280, 40);
        buttonBack.setBackground(Color.white);
        buttonBack.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    ui.show();
                }
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
        mainPanel.add(buttonBack);
//      endregion

        mainPanel.add(backgroundW);
        activeLabel = backgroundW;

    }

    public void show(int option) {
        mainPanel.remove(activeLabel);
        switch (option) {
            case 0:
                mainPanel.add(backgroundW);
                activeLabel = backgroundW;
                break;
            case 1:
                mainPanel.add(backgroundB);
                activeLabel = backgroundB;
                break;
            case 2:
                mainPanel.add(backgroundT);
                activeLabel = backgroundT;
                break;
        }

        ui.gm.window.remove(ui.gm.currentPanel);
        ui.gm.currentPanel = mainPanel;
        ui.gm.window.add(mainPanel);
        ui.gm.window.window.repaint();

    }
}
