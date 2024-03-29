package MVC;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import board.*;

public class MenuReplayUI extends JPanel {

    ManagerUI ui;
    JLabel background;
    JTextArea textArea;
    JButton loadButton;
    JFileChooser fileChooser;

    public MenuReplayUI(ManagerUI ui) {
        this.ui = ui;
        setup();
    }

    /**
     * It sets up the panel, adds the text area, the load button, the back button, the file chooser, and the button chooser
     */
    public void setup() {
        setBounds(0, 0, 1366, 768);
        setLayout(null);
        background = new JLabel();
        background.setBounds(0, 0, 1366, 768);
        ImageIcon backgroundIcon = new ImageIcon("src/main/resources/MenuGamePGN.png");
        background.setIcon(backgroundIcon);

        textArea = new JTextArea("[Event \"F/S Return Match\"]\n" +
                "[Site \"Belgrade, Serbia JUG\"]\n" +
                "[Date \"1992.11.04\"]\n" +
                "[Round \"29\"]\n" +
                "[White \"Fischer, Robert J.\"]\n" +
                "[Black \"Spassky, Boris V.\"]\n" +
                "[Result \"1/2-1/2\"]\n" +
                "\n" +
                "1. e4 e5 2. Nf3 Nc6 3. Bb5 a6 {This opening is called the Ruy Lopez.}\n" +
                "4. Ba4 Nf6 5. O-O Be7 6. Re1 b5 7. Bb3 d6 8. c3 O-O 9. h3 Nb8 10. d4 Nbd7\n" +
                "11. c4 c6 12. cxb5 axb5 13. Nc3 Bb7 14. Bg5 b4 15. Nb1 h6 16. Bh4 c5 17. dxe5\n" +
                "Nxe4 18. Bxe7 Qxe7 19. exd6 Qf6 20. Nbd2 Nxd6 21. Nc4 Nxc4 22. Bxc4 Nb6\n" +
                "23. Ne5 Rae8 24. Bxf7+ Rxf7 25. Nxf7 Rxe1+ 26. Qxe1 Kxf7 27. Qe3 Qg5 28. Qxg5\n" +
                "hxg5 29. b3 Ke6 30. a3 Kd6 31. axb4 cxb4 32. Ra5 Nd5 33. f3 Bc8 34. Kf2 Bf5\n" +
                "35. Ra7 g6 36. Ra6+ Kc5 37. Ke1 Nf4 38. g3 Nxh3 39. Kd2 Kb5 40. Rd6 Kc5 41. Ra6\n" +
                "Nf2 42. g4 Bd3 43. Re6 1/2-1/2");
        textArea.setBounds(250, 410, 350, 220);
        add(textArea);

        loadButton = new JButton("Load GUI.PGN");
        loadButton.setBounds(352, 645, 160, 60);
        loadButton.addActionListener(e -> {
            ui.showPanel(ManagerUI.REPLAY);
            ui.replay.board.pgn.loadPGNFromString(textArea.getText());
            ui.replay.board.setupClassic();
        });
        add(loadButton);

        JLabel backButton = new JLabel();
        backButton.setBounds(635,687, 108, 48);
        backButton.addMouseListener(new MouseListener() {
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

        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileFilter() {

            @Override
            public boolean accept(File f) {
                return f.getName().toLowerCase().endsWith(".pgn");
            }

            @Override
            public String getDescription() {
                return ".pgn";
            }
        });

        JButton buttonChooser = new JButton("Choose pgn File");
        buttonChooser.setBounds(882, 456, 140, 50);
        buttonChooser.addActionListener(e -> {
            chooseFile();
        });
        add(buttonChooser);

        JLabel buttonBack = new JLabel();
        buttonBack.setBounds(633,687,111,45);
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

    /**
     * "If the user selects a file, load the GUI.PGN from that file and show the game panel."
     *
     */
    public void chooseFile() {
        int r = fileChooser.showOpenDialog(null);
        if (r == JFileChooser.APPROVE_OPTION) {
            try {
                ui.game.board.pgn.loadPGNFromFile(fileChooser.getSelectedFile().getAbsolutePath());
                ui.showPanel(ManagerUI.GAME);
                ui.game.board.setupClassic();
                ui.game.board.pgn.moveToEnd();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
