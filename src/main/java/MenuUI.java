import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/*
1. Play
    option for PvP or PvC
    if (PvC) {
        option for side
    }
    a) Standard game
    b) load game (PGN)
    c) Situation builder
    d) Back


2. Game Replays
    a) load PGN
    b) paste PGN
    c) back
3. Settings
    a) volume
    b) game background
    c) computer difficulty
        i)  easy
        ii) cheater
    c) default settings
    d) save
    e) back
4. Exit

In game screen
round
turn of
Clock TODO
Save PGN
Copy PGN
Undo last Move

End of game screen
1. Who won
2. Save PGN
3. Copy PGN
4. Back to Menu




 */


public class MenuUI {

    GameManager gm;
    MenuPlayUI playUI;
    MenuEndGame endGameUI;
    MenuReplayUI replayUI;
    MenuPlayPGNUI pgnUI;

    JPanel mainPanel;
    JLabel background;


    public MenuUI(GameManager gm) {
        this.gm = gm;
        playUI = new MenuPlayUI(this);
        endGameUI = new MenuEndGame(this);
        replayUI = new MenuReplayUI(this);
        pgnUI = new MenuPlayPGNUI(this);
    }

    public void start() {
        mainPanel = new JPanel();
        mainPanel.setBounds(0, 0, 1366, 768);
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.blue);

//        Background
        background = new JLabel();
        background.setBounds(0, 0, 1366, 768);
        ImageIcon backgroundIcon = new ImageIcon("src/main/resources/mainMenuBackground.png");
        background.setIcon(backgroundIcon);

        //        region buttonStart
        JLabel buttonStart = new JLabel();
        buttonStart.setBounds(81, 356, 82, 40);
        buttonStart.setBackground(Color.white);
        buttonStart.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    playUI.show();
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
        mainPanel.add(buttonStart);
        //        endregion

        //        region buttonReplays
        JLabel buttonReplay = new JLabel();
        buttonReplay.setBounds(81, 414, 282, 44);
        buttonReplay.setBackground(Color.white);
        buttonReplay.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    replayUI.show();
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
        mainPanel.add(buttonReplay);
//        endregion

        //        region buttonSettings
        JLabel buttonSettings = new JLabel();
        buttonSettings.setBounds(81, 474, 150, 44);
        buttonSettings.setBackground(Color.white);
        buttonSettings.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

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
        mainPanel.add(buttonSettings);
        //        endregion

        //        region buttonExit
        JLabel buttonExit = new JLabel();
        buttonExit.setBounds(81, 533, 78, 35);
        buttonExit.setBackground(Color.white);
        buttonExit.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    gm.exit();
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
        mainPanel.add(buttonExit);
        //        endregion

        mainPanel.add(background);
    }

    public void show() {
        if (gm.currentPanel != null) {
            gm.window.remove(gm.currentPanel);
        }
        gm.window.add(mainPanel);
        gm.currentPanel = mainPanel;
        gm.window.window.setVisible(true);
        gm.window.window.repaint();
    }
}
