import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.io.IOException;

public class MenuPlayPGNUI {

    MenuUI ui;

    JPanel mainPanel;
    JLabel background;
    JButton continueButton;
    JLabel exitButton;
    JTextArea textArea;
    JFileChooser fileChooser;
    JButton chooseFileButton;

    public MenuPlayPGNUI(MenuUI ui) {
        this.ui = ui;
        start();
    }

    public void start() {
        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBounds(0,0,1366,768);

        background = new JLabel();
        background.setBounds(0,0,1366,768);
        ImageIcon backgroundIcon = new ImageIcon("src/main/resources/PGNMenuBackground.png");
        background.setIcon(backgroundIcon);

        continueButton = new JButton("Continue");
        continueButton.setBounds(360, 635, 140, 50);
        continueButton.addActionListener(e -> buttonPressed());
        mainPanel.add(continueButton);

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
                "Nf2 42. g4 Bd3 43. Re6 1/2-1/2\n" +
                "\n");
        textArea.setBounds(260, 400, 350, 220);
        mainPanel.add(textArea);

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

        chooseFileButton = new JButton("Choose pgn File");
        chooseFileButton.setBounds(882, 456, 140, 50);
        chooseFileButton.addActionListener(e -> {
            chooseFile();
        });
        mainPanel.add(chooseFileButton);






        mainPanel.add(background);
        mainPanel.repaint();
    }

    public void buttonPressed() {
        if (textArea.getLineCount() < 9) {
            return;
        }
        ui.gm.ch.pgn.loadPGNString(textArea.getText());
        ui.gm.gameUI.startPGNGame(-1);
    }

    public void chooseFile() {
        int r = fileChooser.showOpenDialog(null);
        if (r == JFileChooser.APPROVE_OPTION) {
            try {
                ui.gm.ch.pgn.loadPGNFile(fileChooser.getSelectedFile().getAbsolutePath());
                // TODO
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void show() {
        ui.gm.window.remove(ui.gm.currentPanel);
        ui.gm.currentPanel = mainPanel;
        ui.gm.window.add(mainPanel);
        ui.gm.window.window.repaint();

    }
}
