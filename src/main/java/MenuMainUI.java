import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuMainUI extends JPanel implements UI{

    ManagerUI ui;

    public MenuMainUI(ManagerUI ui) {
        this.ui = ui;
        setup();
    }

    @Override
    public void setup() {
        setBounds(0,0, 1366, 768);

        JLabel background = new JLabel();
        background.setBounds(0,0,1366,768);
        ImageIcon backgroundIcon = new ImageIcon("src/main/resources/MenuMain.png");
        background.setIcon(backgroundIcon);

        JLabel buttonStart = new JLabel();
        buttonStart.setBounds(80, 355, 90, 45);
        buttonStart.addMouseListener(new MouseListener() {
                                         @Override
                                         public void mouseClicked(MouseEvent e) {
                                             ui.showPanel(ManagerUI.MENU_PLAY);
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
                add(buttonStart);

        JLabel buttonReplay = new JLabel();
        buttonReplay.setBounds(80, 406, 289, 53);
        buttonReplay.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ui.showPanel(ManagerUI.MENU_REPLAY);
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
        add(buttonReplay);

        JLabel buttonSettings = new JLabel();
        buttonSettings.setBounds(80, 470, 157, 50);
        buttonSettings.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                ui.showPanel(ManagerUI.MENU_SETTINGS);
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
        add(buttonSettings);

        JLabel buttonExit = new JLabel();
        buttonExit.setBounds(80, 527, 85, 45);
        buttonExit.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
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
        add(buttonExit);

        add(background);

    }

}
