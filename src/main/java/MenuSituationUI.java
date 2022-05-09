import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

public class MenuSituationUI extends JPanel implements UI{

    ManagerUI ui;

    JLabel background;
    Tile[][] tiles;
    String selected;
    String[][] board;

    JLabel message;

    JLabel[][] selection;
    String[][] pieces;
    boolean[][] isYellow;

    Color light = new Color(180, 123, 0);
    Color lightDarker = light.darker().darker();
    Color dark = new Color(141, 97, 0);
    Color darkDarker = dark.darker().darker();

    public MenuSituationUI(ManagerUI ui) {
        this.ui = ui;
        tiles = new Tile[8][8];
        selected = "NN";
        setup();
    }

    public void setup() {

        selection = new JLabel[2][6];
        pieces = new String[2][6];
        isYellow = new boolean[2][6];
        board = new String[8][8];

        setBounds(0,0,1366,768);
        setLayout(null);

        message = new JLabel("", SwingConstants.CENTER);
        message.setBounds(0, 768/2-30, 1366, 60);
        message.setForeground(Color.red.darker());
        message.setFont(new Font("Arial", Font.BOLD, 50));
        add(message);

        background = new JLabel();
        background.setBounds(0,0,1366,768);
        ImageIcon backgroundIcon = new ImageIcon("src/main/resources/Builder.png");
        background.setIcon(backgroundIcon);

        JLabel buttonStart = new JLabel();
        buttonStart.setBounds(1056, 681, 124, 50);
        buttonStart.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (isAlright()) {
                    ui.game.board.againstComputer = false;
                    ui.showPanel(ManagerUI.GAME);
                    ui.game.board.setupBoard(board);
                    ui.game.updateText();
                    ui.game.builder = true;
                } else {
                    showMessage("You need both White and Black king to play", 4);
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
        add(buttonStart);

        JLabel buttonBack = new JLabel();
        buttonBack.setBounds(1200, 680, 130, 60);
        buttonBack.addMouseListener(new MouseListener() {
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
        add(buttonBack);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tiles[i][j] = new Tile(this,i,j, null);
                add(tiles[i][j]);
            }
        }

        pieces[0] = new String[]{"WP", "WH", "WB", "WR", "WQ", "WK"};
        pieces[1] = new String[]{"BP", "BH", "BB", "BR", "BQ", "BK"};

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 6; j++) {
                selection[i][j] = new JLabel();
                selection[i][j].setBounds(i == 0 ? 220-94-10 : 220 - 94*2 - 20, 7 + j*94, 88, 88);
                selection[i][j].setBackground((i + j % 2 == 1) ? light : dark);
                selection[i][j].setOpaque(true);
                int finalI = i;
                int finalJ = j;
                selection[i][j].addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        selected = pieces[finalI][finalJ];
                        System.out.println(selected);
                        undrawYellow();
                        isYellow[finalI][finalJ] = true;
                        selection[finalI][finalJ].setBackground(Color.yellow);
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        if (isYellow[finalI][finalJ]) {
                            selection[finalI][finalJ].setBackground(Color.yellow.darker().darker());
                        } else {
                            selection[finalI][finalJ].setBackground(((finalI + finalJ % 2 == 1) ? light : dark).darker().darker());
                        }
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        if (isYellow[finalI][finalJ]) {
                            selection[finalI][finalJ].setBackground(Color.yellow);
                        } else {
                            selection[finalI][finalJ].setBackground(((finalI + finalJ % 2 == 1) ? light : dark));
                        }
                    }
                });
                ImageIcon icon = new ImageIcon("src/main/resources/pieces/" + pieces[i][j] + ".png");
                selection[i][j].setIcon(icon);
                add(selection[i][j]);

            }
        }

        add(background);
        repaint();
    }

    @Override
    public void tilePressed(int x, int y) {
        if (!Objects.equals(selected, "NN")) {
            tiles[x][y].setPiece(selected);
            board[x][y] = selected;
        }
    }

    @Override
    public Component getComp() {
        return this;
    }

    @Override
    public boolean getDev() {
        return ui.op.dev;
    }

    public void undrawYellow() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 6; j++) {
                selection[i][j].setBackground((i + j % 2 == 1) ? light : dark);
                isYellow[i][j] = false;
            }
        }
    }

    public void showMessage(String message, int seconds) {
        Runnable runnable = new Message(this.message, message, seconds);
        Thread thread = new Thread(runnable);
        thread.start();
    }

    public boolean isAlright() {
        boolean w = false;
        boolean b = false;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                } else if (board[i][j].equals("WK")) {
                    w = true;
                } else if (board[i][j].equals("BK")) {
                    b = true;
                }
                if (w && b) {
                    return true;
                }
            }
        }
        return false;
    }

    public void resetBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = null;
                tiles[i][j].removePiece();
            }
        }
    }
}
