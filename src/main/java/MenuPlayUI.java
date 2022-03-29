import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuPlayUI {

    MenuUI ui;

    JPanel mainPanel;
    JLabel background;

    boolean isC = true;

    public MenuPlayUI(MenuUI ui) {
        this.ui = ui;
        setup();
    }

    public void setup() {

        mainPanel = new JPanel();
        mainPanel.setBounds(0, 0, 1366, 768);
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.blue);

//        Background
        background = new JLabel();
        background.setBounds(0, 0, 1366, 768);
        ImageIcon backgroundIcon = new ImageIcon("src/main/resources/playMenuBackgroundC.png");
        background.setIcon(backgroundIcon);

        //        region buttonSwitch
        JLabel buttonSwitch = new JLabel();
        buttonSwitch.setBounds(81, 356, 254, 44);
        buttonSwitch.setBackground(Color.white);
        buttonSwitch.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (isC) {
                        ImageIcon icon = new ImageIcon("src/main/resources/playMenuBackgroundP.png");
                        background.setIcon(icon);
                        isC = false;
                    } else {
                        ImageIcon icon = new ImageIcon("src/main/resources/playMenuBackgroundC.png");
                        background.setIcon(icon);
                        isC = true;
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
        mainPanel.add(buttonSwitch);
        //        endregion

        //        region buttonClassic
        JLabel buttonClassic = new JLabel();
        buttonClassic.setBounds(81, 416, 265, 44);
        buttonClassic.setBackground(Color.white);
        buttonClassic.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    ui.gm.ch.againstComputer = isC;
                    ui.gm.gameUI.startClassicGame();
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
        mainPanel.add(buttonClassic);
        //        endregion

        //        region buttonPGN
        JLabel buttonPGN = new JLabel();
        buttonPGN.setBounds(81, 475, 345, 36);
        buttonPGN.setBackground(Color.white);
        buttonPGN.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    ui.pgnUI.show();
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
        mainPanel.add(buttonPGN);
        //        endregion

        //        region buttonSim
        JLabel buttonSim = new JLabel();
        buttonSim.setBounds(81, 533, 326, 36);
        buttonSim.setBackground(Color.white);
        buttonSim.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {

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
        mainPanel.add(buttonSim);
        //        endregion

        //        region buttonBack
        JLabel buttonBack = new JLabel();
        buttonBack.setBounds(81, 592, 96, 36);
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
        //        endregion






        mainPanel.add(background);
    }

    public void show() {
        System.out.println("pichefn uioseruif hseriufyuiserguif uioseghofh rse fgh oseruofg seu8o tuosotsrguo");
        if (ui.gm.currentPanel != null) {
            ui.gm.window.remove(ui.gm.currentPanel);
        }
        ui.gm.window.add(mainPanel);
        ui.gm.currentPanel = mainPanel;
        ui.gm.window.window.setSize(1366, 798);
        ui.gm.window.window.setVisible(true);
        ui.gm.window.window.repaint();
    }
}
