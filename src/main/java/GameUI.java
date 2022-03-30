import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Timer;

public class GameUI {

    GameManager gm;
    JPanel mainPanel;
    JLabel background;
    JLabel turnTimer;
    JLabel activePlayer;
    JLabel upgradeHorse;
    JLabel upgradeBishop;
    JLabel upgradeRook;
    JLabel upgradeQueen;
    JLabel messageText;

    int promotion = -1;
    Tile[][] tiles = new Tile[8][8];
    int[] clickedOn = new int[2];
    Color light = new Color(180, 123, 0);
    Color dark = new Color(141, 97, 0);

    public GameUI(GameManager gm) {
        this.gm = gm;
    }

    public void start() {
        //        Main Panel
        mainPanel = new JPanel();
        mainPanel.setBounds(0, 0, 1366, 768);
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.blue);

//        Background
        background = new JLabel();
        background.setBounds(0, 0, 1366, 768);
        ImageIcon backgroundIcon = new ImageIcon("src/main/resources/gameBackground.png");
        background.setIcon(backgroundIcon);


        //        region buttonSave
        JLabel buttonSave = new JLabel();
        buttonSave.setBounds(1043, 268, 192, 36);
        buttonSave.setBackground(Color.white);
        buttonSave.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    try {
                        gm.ch.pgn.savePGN();
                    } catch (IOException ignored) {

                    }
                    showMessage("PGN succesfully saved", 3);
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

        //        region buttonCopy
        JLabel buttonCopy = new JLabel();
        buttonCopy.setBounds(1043, 365, 196, 44);
        buttonCopy.setBackground(Color.white);
        buttonCopy.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    gm.ch.pgn.copyPGN();
                    showMessage("PGN copied to clipboard", 3);
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

        //        region buttonUndo
        JLabel buttonUndo = new JLabel();
        buttonUndo.setBounds(1043, 466, 283, 44);
        buttonUndo.setBackground(Color.white);
        buttonUndo.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e) && gm.ch.turn > 0) {
                    gm.ch.turn--;
                    startPGNGame(gm.ch.turn);
                    System.out.println(gm.ch.turn-2);
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
        mainPanel.add(buttonUndo);
        //        endregion

        //        region buttonExit
        JLabel buttonExit = new JLabel();
        buttonExit.setBounds(1043, 563, 237, 40);
        buttonExit.setBackground(Color.white);
        buttonExit.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    gm.menuUI.show();
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

        messageText = new JLabel("", SwingConstants.CENTER);
        messageText.setBounds(0,356, 1366, 120);
        messageText.setFont(new Font("Zapfino", Font.BOLD, 40));
        messageText.setForeground(Color.red.darker().darker());
        mainPanel.add(messageText);

        setupupgrade();

        mainPanel.add(background);

//        Turn Counter and active player
        drawTurnCounter();
        drawActivePlayer();
        createPieces();
    }

    public void showMessage(String message, int timeInSeconds) {

        messageText.setText(message);
        TimerGameMessage task = new TimerGameMessage(this);

        int time = timeInSeconds*1000;

        java.util.Timer timer = new Timer();

        timer.schedule(task, time);
    }

    public void setupupgrade() {
        upgradeHorse = new JLabel();
        upgradeHorse.setBounds(65,7 + 2 * 94,90,90);
        ImageIcon iconHorse = new ImageIcon("src/main/resources/Pieces/"+ gm.ch.turnOf +"H.png");
        upgradeHorse.setIcon(iconHorse);
        upgradeHorse.setBackground(dark);
        upgradeHorse.setOpaque(true);
        upgradeHorse.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    for (int i = 0; i < 8; i++) {
                        if (gm.ch.board[0][i].getClass().getName().equals("Pawn")) {
                            gm.ch.board[0][i] = new Horse(gm.ch, 0, i, 'W');
                            tiles[0][i].setPiece("WH");
                        } else if (gm.ch.board[7][i].getClass().getName().equals("Pawn")) {
                            gm.ch.board[7][i] = new Horse(gm.ch, 7, i, 'B');
                            tiles[7][i].setPiece("BH");
                        }
                    }
                    hideUpgrade();
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

        upgradeBishop = new JLabel();
        upgradeBishop.setBounds(65,7 + 3 * 94,90,90);
        ImageIcon iconBishop = new ImageIcon("src/main/resources/Pieces/"+ gm.ch.turnOf +"B.png");
        upgradeBishop.setIcon(iconBishop);
        upgradeBishop.setBackground(light);
        upgradeBishop.setOpaque(true);
        upgradeBishop.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    for (int i = 0; i < 8; i++) {
                        if (gm.ch.board[0][i].getClass().getName().equals("Pawn")) {
                            gm.ch.board[0][i] = new Bishop(gm.ch, 0, i, 'W');
                            tiles[0][i].setPiece("WB");
                        } else if (gm.ch.board[7][i].getClass().getName().equals("Pawn")) {
                            gm.ch.board[7][i] = new Bishop(gm.ch, 7, i, 'B');
                            tiles[7][i].setPiece("BB");
                        }
                    }
                    hideUpgrade();
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

        upgradeRook = new JLabel();
        upgradeRook.setBounds(65,7 + 4 * 94,90,90);
        ImageIcon iconRook = new ImageIcon("src/main/resources/Pieces/"+ gm.ch.turnOf +"R.png");
        upgradeRook.setIcon(iconRook);
        upgradeRook.setBackground(dark);
        upgradeRook.setOpaque(true);
        upgradeRook.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    for (int i = 0; i < 8; i++) {
                        if (gm.ch.board[0][i].getClass().getName().equals("Pawn")) {
                            gm.ch.board[0][i] = new Rook(gm.ch, 0, i, 'W');
                            tiles[0][i].setPiece("WR");
                        } else if (gm.ch.board[7][i].getClass().getName().equals("Pawn")) {
                            gm.ch.board[7][i] = new Rook(gm.ch, 7, i, 'B');
                            tiles[7][i].setPiece("BR");
                        }
                    }
                    hideUpgrade();
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

        upgradeQueen = new JLabel();
        upgradeQueen.setBounds(65,7 + 5 * 94,90,90);
        ImageIcon iconQueen = new ImageIcon("src/main/resources/Pieces/"+ gm.ch.turnOf +"Q.png");
        upgradeQueen.setIcon(iconQueen);
        upgradeQueen.setBackground(light);
        upgradeQueen.setOpaque(true);
        upgradeQueen.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    for (int i = 0; i < 8; i++) {
                        if (gm.ch.board[0][i].getClass().getName().equals("Pawn")) {
                            gm.ch.board[0][i] = new Queen(gm.ch, 0, i, 'W');
                            tiles[0][i].setPiece("WQ");
                        } else if (gm.ch.board[7][i].getClass().getName().equals("Pawn")) {
                            gm.ch.board[7][i] = new Queen(gm.ch, 7, i, 'B');
                            tiles[7][i].setPiece("BQ");
                        }
                    }
                    hideUpgrade();
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
    }

    public void showUpgrade(char color) {
        ImageIcon iconHorse = new ImageIcon("src/main/resources/Pieces/"+ color +"H.png");
        upgradeHorse.setIcon(iconHorse);
        mainPanel.add(upgradeHorse);

        ImageIcon iconBishop = new ImageIcon("src/main/resources/Pieces/"+ color +"B.png");
        upgradeBishop.setIcon(iconBishop);
        mainPanel.add(upgradeBishop);

        ImageIcon iconRook = new ImageIcon("src/main/resources/Pieces/"+ color +"R.png");
        upgradeRook.setIcon(iconRook);
        mainPanel.add(upgradeRook);

        ImageIcon iconQueen = new ImageIcon("src/main/resources/Pieces/"+ color +"Q.png");
        upgradeQueen.setIcon(iconQueen);
        mainPanel.add(upgradeQueen);

        mainPanel.add(background);
    }

    public void hideUpgrade() {
        mainPanel.remove(upgradeHorse);
        mainPanel.remove(upgradeBishop);
        mainPanel.remove(upgradeRook);
        mainPanel.remove(upgradeQueen);
        mainPanel.add(background);
        mainPanel.repaint();

        gm.ch.endEndTurn();
    }

    public void show() {
        if (gm.currentPanel != null) {
            gm.window.remove(gm.currentPanel);
        }
        gm.window.add(mainPanel);
        gm.currentPanel = mainPanel;
        gm.window.window.setSize(1366, 798);
        gm.window.window.setVisible(true);
        gm.window.window.repaint();
    }

    public void startClassicGame() {
        gm.ch.setupStandard();
//        gm.ch.setupTesting();
        gm.ch.state = -1;
        show();
        drawPieces();
    }

    public void startPGNGame(int round) {
        gm.ch.setupPGN(round);
        gm.ch.state = -1;
        show();
        drawPieces();
        updateActivePlayer();
        updateTurnCounter();
    }

    public void createPieces() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tiles[i][j] = new Tile(this, i, j);
            }
        }
    }

    public void drawPieces() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (gm.ch.board[i][j].getColor() != 'N') {
                    tiles[i][j].setPiece(gm.ch.board[i][j].getColor() + Character.toString(gm.ch.board[i][j].getClass().getName().charAt(0)));
                } else {
                    tiles[i][j].removePiece();
                }
            }
        }
        mainPanel.add(background);
    }

    public void paintMoves(int x, int y) {
        unpaintMoves();
        clickedOn[0] = x;
        clickedOn[1] = y;
        int[][] moves = gm.ch.board[x][y].PossibleMoves();
        for (int i = 0; i < 30; i++) {
            if (moves[i][0] == -1) {
                break;
            }
            if (gm.ch.isKingChecked(moves[i][0], moves[i][1])) {
                continue;
            }
            if (gm.ch.board[moves[i][0]][moves[i][1]].getColor() == 'N') {
                if (gm.ch.board[x][y].getClass().getName().equals("Pawn") && moves[i][1] != y) {
                    tiles[moves[i][0]][moves[i][1]].setColor(2);
                    gm.ch.board[moves[i][0]][moves[i][1]].setRed(true);
                } else {
                    tiles[moves[i][0]][moves[i][1]].setColor(1);
                    gm.ch.board[moves[i][0]][moves[i][1]].setYellow(true);
                }
            } else {
                tiles[moves[i][0]][moves[i][1]].setColor(2);
                gm.ch.board[moves[i][0]][moves[i][1]].setRed(true);
            }
        }
        mainPanel.repaint();
    }

    public void unpaintMoves() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tiles[i][j].setColor(0);
                gm.ch.board[i][j].setRed(false);
            }
        }
        mainPanel.repaint();
    }

    public void move(int x, int y) {
        gm.ch.pgn.updatePGN(clickedOn[0], clickedOn[1], x, y, false, gm.ch.board[clickedOn[0]][clickedOn[1]].getClass().getName().charAt(0));
        boolean flag = gm.ch.board[clickedOn[0]][clickedOn[1]].getClass().getName().equals("King") && Math.abs(y - clickedOn[1]) == 2;
        tiles[x][y].setPiece(gm.ch.board[clickedOn[0]][clickedOn[1]].getColor() + Character.toString(gm.ch.board[clickedOn[0]][clickedOn[1]].getClass().getName().charAt(0)));
        tiles[clickedOn[0]][clickedOn[1]].removePiece();
        mainPanel.repaint();
        gm.ch.move(x, y);
        unpaintMoves();
        if (flag) {
            if (y < 4) {
                clickedOn[0] = x;
                clickedOn[1] = 0;
                move(x, 3);
                gm.ch.turn-=2;
                gm.ch.startEndTurn();
            } else {
                clickedOn[0] = x;
                clickedOn[1] = 7;
                move(x, 5);
                gm.ch.turn-=2;
                gm.ch.startEndTurn();
            }
        }
    }

    public void movePGN(char turnOf, char piece, int fromY, int toX, int toY) {
        Piece[][] board = gm.ch.board;
        for (int i = 0; i < 8; i++) {
            if (board[i][fromY].getColor() == turnOf && board[i][fromY].getClass().getName().charAt(0) == piece) {
                int[][] moves = board[i][fromY].PossibleMoves();
                int idx = 0;
                while (moves[idx][0] != -1) {
                    System.out.println(moves[idx][0] + " " + moves[idx][1]);
                    if (moves[idx][0] == toX && moves[idx][1] == toY) {
                        break;
                    }
                    idx++;
                }
                if (moves[idx][0] == -1) {
                    continue;
                }
                putPiece(toX, toY, turnOf, piece);
                tiles[i][fromY].removePiece();
                tiles[toX][toY].setPiece(turnOf + "" + piece);
                putPiece(i, fromY, 'N', 'N');
                return;
            }
        }
    }

    public void movePGN(char turnOf, char piece, int toX, int toY) {
        Piece[][] board = gm.ch.board;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].getColor() == turnOf && board[i][j].getClass().getName().charAt(0) == piece) {
                    int[][] moves = board[i][j].PossibleMoves();
                    int idx = 0;
                    while (moves[idx][0] != -1) {
                        if (moves[idx][0] == toX && moves[idx][1] == toY) {
                            putPiece(toX, toY, turnOf, piece);
                            tiles[i][j].removePiece();
                            tiles[toX][toY].setPiece(turnOf + "" + piece);
                            putPiece(i, j, 'N', 'N');
                            return;
                        }
                        idx++;
                    }
                }
            }
        }
    }

    public void putPiece(int x, int y, char color, char piece) {
        Chess ch = gm.ch;
        Piece[][] board = gm.ch.board;
        switch (piece) {
            case 'R':
                board[x][y] = new Rook(ch, x, y, color);
                break;
            case 'H':
                board[x][y] = new Horse(ch, x, y, color);
                break;
            case 'B':
                board[x][y] = new Bishop(ch, x, y, color);
                break;
            case 'Q':
                board[x][y] = new Queen(ch, x, y, color);
                break;
            case 'K':
                board[x][y] = new King(ch, x, y, color);
                break;
            case 'P':
                board[x][y] = new Pawn(ch, x, y, color);
                break;
            case 'N':
                board[x][y] = new Empty(ch, x, y, 'N');
                break;
        }
    }

    public void take(int x, int y) {
        gm.ch.pgn.updatePGN(clickedOn[0], clickedOn[1], x, y, true, gm.ch.board[clickedOn[0]][clickedOn[1]].getClass().getName().charAt(0));
        if (gm.ch.board[clickedOn[0]][clickedOn[1]].getClass().getName().equals("Pawn") && gm.ch.board[x][y].getColor() == 'N') {
            if (gm.ch.board[clickedOn[0]][clickedOn[1]].getColor() == 'W') {
                gm.ch.enPassant(x + 1, y);
                tiles[x + 1][y].removePiece();
            } else {
                gm.ch.enPassant(x - 1, y);
                tiles[x - 1][y].removePiece();
            }
            mainPanel.repaint();
            move(x, y);
            unpaintMoves();
            return;
        }
        tiles[x][y].setPiece(gm.ch.board[clickedOn[0]][clickedOn[1]].getColor() + Character.toString(gm.ch.board[clickedOn[0]][clickedOn[1]].getClass().getName().charAt(0)));
        tiles[clickedOn[0]][clickedOn[1]].removePiece();
        mainPanel.repaint();
        gm.ch.take(x, y);
        unpaintMoves();
    }

    public void drawTurnCounter() {
        turnTimer = new JLabel();
        turnTimer.setText("1");
        turnTimer.setBounds(1184,20,200, 60);
        turnTimer.setFont(new Font("Zapfino", Font.PLAIN, 30));
        mainPanel.add(turnTimer);
    }

    public void drawActivePlayer() {
        activePlayer = new JLabel();
        activePlayer.setText("White");
        activePlayer.setBounds(1198,69,200, 60);
        activePlayer.setFont(new Font("Zapfino", Font.PLAIN, 30));
        mainPanel.add(activePlayer);
    }

    public void updateTurnCounter() {
        turnTimer.setText(String.valueOf(gm.ch.turn));
    }
    public void updateActivePlayer() {
        if (gm.ch.turnOf == 'W') {
            activePlayer.setText("White");
        } else {
            activePlayer.setText("Black");
        }
    }
}
