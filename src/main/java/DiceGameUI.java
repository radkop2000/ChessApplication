import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;

public class DiceGameUI extends JPanel implements UI{

    ManagerUI ui;
    JLabel rules;
    JLabel background;
    JLabel turnLabel;
    JLabel turnOfLabel;
    JLabel message;
    BoardDice board;
    Tile[][] tiles = new Tile[8][8];

    int mode;

    Color light = new Color(180, 123, 0);
    Color lightDarker = light.darker().darker();
    Color dark = new Color(141, 97, 0);
    Color darkDarker = dark.darker().darker();

    public DiceGameUI(ManagerUI ui) {
        this.ui = ui;
        this.board = new BoardDice(this);
        mode = ui.op.gameMode;
        setup();
    }

    public void setup() {
        setBounds(0,0,1366,768);
        setLayout(null);

        message = new JLabel("", SwingConstants.CENTER);
        message.setBounds(0, 768/2-30, 1366, 60);
        message.setForeground(Color.red.darker());
        message.setFont(new Font("Arial", Font.BOLD, 50));
        add(message);

        background = new JLabel();
        background.setBounds(0,0,1366,768);
        ImageIcon backgroundIcon = new ImageIcon("src/main/resources/GameMode.png");
        background.setIcon(backgroundIcon);

        JLabel buttonExit = new JLabel();
        buttonExit.setBounds(1040, 684, 110, 40);
        buttonExit.addMouseListener(new MouseListener() {
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
        add(buttonExit);

        rules = new JLabel();
        rules.setBounds(0,0,1366,768);
        ImageIcon rulesIcon = new ImageIcon("src/main/resources/MenuMain.png");
        rules.setIcon(rulesIcon);
        rules.setVisible(false);
        rules.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                rules.setVisible(false);
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
        add(rules);

        JLabel buttonRules = new JLabel();
        buttonRules.setBounds(1040, 147, 120, 40);
        buttonRules.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                rules.setVisible(true);
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
        add(buttonRules);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tiles[i][j] = new Tile(this, i, j, board);
                add(tiles[i][j]);
            }
        }

        turnLabel = new JLabel("999");
        turnLabel.setBounds(1190, 25, 100, 50);
        turnLabel.setFont(new Font("Zapfino", Font.BOLD, 25));
        add(turnLabel);

        turnOfLabel = new JLabel("White");
        turnOfLabel.setBounds(1190, 73, 100, 50);
        turnOfLabel.setFont(new Font("Zapfino", Font.BOLD, 25));
        add(turnOfLabel);

        add(background);
    }

    public void redrawBackground() {
        add(background);
        repaint();
    }

    public void putPiece(String piece, int x, int y) {
        tiles[x][y].setPiece(piece);
    }


    public void showPromotion(char color) {
        JLabel queen = new JLabel();
        JLabel rook = new JLabel();
        JLabel bishop = new JLabel();
        JLabel horse = new JLabel();

        queen.setBounds(220 + -1 * 94, 7 + 2 * 94, 88, 88);
        queen.setBackground(dark);
        queen.setOpaque(true);
        ImageIcon queenIcon = new ImageIcon("src/main/resources/Pieces/" + color + "Q.png");
        queen.setIcon(queenIcon);
        queen.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                board.promote('Q');
                hidePromotion(queen, rook, bishop, horse);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                queen.setBackground(darkDarker);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                queen.setBackground(dark);
            }
        });

        rook.setBounds(220 + -1 * 94, 7 + 3 * 94, 88, 88);
        rook.setBackground(light);
        rook.setOpaque(true);
        ImageIcon rookIcon = new ImageIcon("src/main/resources/Pieces/" + color + "R.png");
        rook.setIcon(rookIcon);
        rook.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                board.promote('R');
                hidePromotion(queen, rook, bishop, horse);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                rook.setBackground(lightDarker);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                rook.setBackground(light);
            }
        });

        bishop.setBounds(220 + -1 * 94, 7 + 4 * 94, 88, 88);
        bishop.setBackground(dark);
        bishop.setOpaque(true);
        ImageIcon bishopIcon = new ImageIcon("src/main/resources/Pieces/" + color + "B.png");
        bishop.setIcon(bishopIcon);
        bishop.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                board.promote('B');
                hidePromotion(queen, rook, bishop, horse);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                bishop.setBackground(darkDarker);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                bishop.setBackground(dark);
            }
        });

        horse.setBounds(220 + -1 * 94, 7 + 5 * 94, 88, 88);
        horse.setBackground(light);
        horse.setOpaque(true);
        ImageIcon horseIcon = new ImageIcon("src/main/resources/Pieces/" + color + "H.png");
        horse.setIcon(horseIcon);
        horse.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                board.promote('H');
                hidePromotion(queen, rook, bishop, horse);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                horse.setBackground(lightDarker);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                horse.setBackground(light);
            }
        });


        add(queen);
        add(rook);
        add(bishop);
        add(horse);
        add(background);
        repaint();

    }

    private void hidePromotion(JLabel queen, JLabel rook, JLabel bishop, JLabel horse) {
        remove(queen);
        remove(rook);
        remove(bishop);
        remove(horse);
        repaint();
    }


    @Override
    public void tilePressed(int x, int y) {
        if (tiles[x][y].isHighlighted) {
            resetHighlight();
            board.move(x, y);
            if (mode == 1 || board.mode == 1) {
                board.clickedOn[0] = board.nextMoveXY[0];
                board.clickedOn[1] = board.nextMoveXY[1];
                showMoves(board.clickedOn[0], board.clickedOn[1]);
            }
        } else if (tiles[x][y].isYellow) {
            resetMoves();
            showMoves(x,y);
            if ((mode == 1 || board.mode == 1 )&&x == board.nextMoveXY[0] && y == board.nextMoveXY[1]) {
                board.clickedOn[0] = x;
                board.clickedOn[1] = y;
                resetMoves();
                showMoves(x, y);
            } else if (board.getPiece(x,y) == board.nextMoveC && board.getColor(x,y) == board.turnOf)   {
                board.clickedOn[0] = x;
                board.clickedOn[1] = y;
                resetMoves();
                showMoves(x, y);
            }
        } else {
            resetMoves();
        }


    }

    public void resetMoves() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tiles[i][j].setLighterNoYellow();
            }
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

    public void removePiece(int x, int y) {
        tiles[x][y].removePiece();
    }

    private void showMoves(int x, int y) {
        ArrayList<int[]> moves = board.movesWithoutCheck(x, y);
        for (int[] move : moves) {
            tiles[move[0]][move[1]].setDarker();
        }
    }

    private void resetHighlight() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tiles[i][j].setLigher();
            }
        }
    }

    public void updateText() {
        turnLabel.setText(String.valueOf(board.turn/2 + 1));
        if (board.turnOf == 'W') {
            turnOfLabel.setText("White");
        } else {
            turnOfLabel.setText("Black");
        }
    }

    public void showMessage(String message, int seconds) {
        Runnable runnable = new Message(this.message, message, seconds);
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
