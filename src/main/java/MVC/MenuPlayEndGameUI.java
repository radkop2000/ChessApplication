package MVC;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuPlayEndGameUI extends JPanel {

    ManagerUI ui;

    JPanel lastPanel;
    char backgroundType;

    JLabel background;
    JLabel buttonShow;
    JLabel buttonMenu;

    public MenuPlayEndGameUI(ManagerUI ui) {
        this.ui = ui;
        setup();
    }

    /**
     * It sets up the panel
     */
    public void setup() {
        setBounds(0,0,1366,768);
        setLayout(null);

        background = new JLabel();
        background.setBounds(0,0,1366,768);


        buttonShow = new JLabel();
        buttonShow.setBounds(76, 351, 280, 50);
        buttonShow.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                ui.window.showPanel(lastPanel);
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
        add(buttonShow);

        buttonMenu = new JLabel();
        buttonMenu.setBounds(79, 407, 263, 54);
        buttonMenu.addMouseListener(new MouseListener() {
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
        add(buttonMenu);

        add(background);
    }

    /**
     * It sets the background image of the game to the end game image
     *
     * @param panel the panel that you want to be displayed on the screen
     * @param backgroundType 'W' for win, 'L' for lose
     */
    public void endGame(JPanel panel, char backgroundType) {
        lastPanel = panel;
        ImageIcon icon = new ImageIcon("src/main/resources/endGame" + backgroundType + ".png");
        background.setIcon(icon);
        repaint();

    }

}
