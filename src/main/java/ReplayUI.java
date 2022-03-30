import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;

public class ReplayUI {

    GameManager gm;
    Chess ch;
    PGN pgn;
    JPanel mainPanel;
    JLabel background;
    Tile[][] tiles = new Tile[8][8];
    Piece[][] board;
    JLabel messageLabel;
    JLabel roundText;
    JLabel turnOfText;
    JTextArea jumpToRoundText;

    public ReplayUI(GameManager gm) {
        this.gm = gm;
        ch = gm.ch;
        board = ch.board;
        pgn = ch.pgn;
    }

    public void start() {


        mainPanel = new JPanel();
        mainPanel.setBounds(0, 0, 1366, 768);
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.black);
        background = new JLabel();
        background.setBounds(0, 0, 1366, 768);
        ImageIcon backgroundIcon = new ImageIcon("src/main/resources/replayBackground.png");
        background.setIcon(backgroundIcon);

        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setBounds(0, 364, 1366, 120);
        messageLabel.setText("");
        messageLabel.setFont(new Font("Zapfino", Font.BOLD, 50));
        messageLabel.setForeground(Color.red.darker().darker());
        mainPanel.add(messageLabel);

        JLabel nextTurnButton = new JLabel();
        nextTurnButton.setBounds(1039, 288, 210, 40);
        nextTurnButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    pgn.nextTurnReplay();
                    updateTopText();
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
        mainPanel.add(nextTurnButton);

        JLabel prevTurnButton = new JLabel();
        prevTurnButton.setBounds(1039, 380, 310, 40);
        prevTurnButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    int temp = pgn.moveIDX;
                    pgn.moveIDX = 0;
                    show();
                    while (pgn.moveIDX < temp - 1) {
                        pgn.nextTurnReplay();
                        mainPanel.repaint();
                    }
                }
                updateTopText();
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
        mainPanel.add(prevTurnButton);

        roundText = new JLabel();
        roundText.setText("1");
        roundText.setBounds(1197, 26, 200, 50);
        roundText.setFont(new Font("Zapfino", Font.PLAIN, 27));
        mainPanel.add(roundText);

        turnOfText = new JLabel();
        turnOfText.setText("White");
        turnOfText.setBounds(1197, 78, 200, 50);
        turnOfText.setFont(new Font("Zapfino", Font.PLAIN, 27));
        mainPanel.add(turnOfText);

        JLabel exitButton = new JLabel();
        exitButton.setBounds(1045, 461, 263, 40);
        exitButton.addMouseListener(new MouseListener() {
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
        mainPanel.add(exitButton);

        jumpToRoundText = new JTextArea("Jump to");
        jumpToRoundText.setBounds(1049, 529, 150, 40);
        jumpToRoundText.setFont(new Font( "Verdana", Font.PLAIN, 30));
        mainPanel.add(jumpToRoundText);

        JLabel jumpButton = new JLabel();
        jumpButton.setBounds(1020, 600, 327, 46);
        jumpButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    try {
                        if (Integer.parseInt(jumpToRoundText.getText()) > 0 && Integer.parseInt(jumpToRoundText.getText()) <= pgn.moves.size()/2+1) {
                            int temp = Integer.parseInt(jumpToRoundText.getText());
                            show();
                            while (pgn.moveIDX < temp*2 - 2) {
                                pgn.nextTurnReplay();
                            }
                        } else if (Integer.parseInt(jumpToRoundText.getText()) > pgn.moves.size()/2+1) {
                            showMessage("This game only has " + (pgn.moves.size()/2 + 1) + " rounds", 4);
                        } else {
                            showMessage("Please enter a number greater than 0", 4);
                        }
                    } catch (NumberFormatException c) {
                        if (jumpToRoundText.getText().equals("end")) {
                            show();
                            while (pgn.moveIDX < pgn.moves.size()) {
                                pgn.nextTurnReplay();
                            }
                        } else {
                            showMessage("Please enter a number or \"end\"", 4);
                        }
                    }
                    updateTopText();
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
        mainPanel.add(jumpButton);


        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tiles[i][j] = new Tile(this, i, j);
            }
        }
        mainPanel.add(background);
    }


    public void show() {
        gm.window.remove(gm.currentPanel);
        gm.currentPanel = mainPanel;
        gm.window.add(mainPanel);
        gm.window.window.repaint();
        pgn.moveIDX = 0;
        pgn.turnOf = 'W';
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                putPiece(i, j, 'N', 'N');
            }
        }
        char[] temp = {'R', 'H', 'B', 'Q', 'K', 'B', 'H', 'R'};
        for (int i = 0; i < 8; i++) {
            putPiece(0, i, 'B', temp[i]);
            putPiece(1, i, 'B', 'P');

            putPiece(6, i, 'W', 'P');
            putPiece(7, i, 'W', temp[i]);
        }


        drawBoard();
    }

    public void putPiece(int x, int y, char color, char piece) {
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

    private void drawBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].getColor() == 'N') {
                    tiles[i][j].removePiece();
                } else {
                    tiles[i][j].setPiece(board[i][j].getColor() + "" + board[i][j].getClass().getName().charAt(0));
                    System.out.println(board[i][j].getColor() + "" + board[i][j].getClass().getName().charAt(0));
                }
            }
        }
        mainPanel.add(background);
        mainPanel.repaint();
    }

    public void move(char turnOf, char piece, int fromY, int toX, int toY) {
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

    public void printBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.printf("%c%c ", board[i][j].getColor(), board[i][j].getClass().getName().charAt(0));
            }
            System.out.println();
        }
    }

    public void move(char turnOf, char piece, int toX, int toY) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].getColor() == turnOf && board[i][j].getClass().getName().charAt(0) == piece) {
                    int[][] moves = board[i][j].PossibleMoves();
                    int idx = 0;
                    while (moves[idx][0] != -1) {
                        System.out.println(moves[idx][0] + " , " + moves[idx][1]);
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

    public void showEndMessage() {
        int counter = 0;
        int idx = 0;
        while (counter < 6) {
            if (pgn.currentGame.charAt(idx++) == '\n') {
                counter++;
            }
        }
        System.out.println(pgn.currentGame);

        StringBuilder sb = new StringBuilder();
        while (pgn.currentGame.charAt(idx) != '\n') {
            sb.append(pgn.currentGame.charAt(idx++));
        }
        // TODO

        System.out.println(sb);

        if (sb.toString().equals("[Result \"1/2-1/2\"]")) {
            showMessage("The game ended in a DRAW", 10);
        }


    }

    public void showMessage(String message, int timeInSeconds) {

        TimerReplayMessage task = new TimerReplayMessage(this);

        messageLabel.setText(message);
        int time = timeInSeconds*1000;

        Timer timer = new Timer();
        timer.schedule(task, time);
    }

    public void updateTopText() {
        if (pgn.turnOf == 'W') {
            turnOfText.setText("White");
        } else {
            turnOfText.setText("Black");
        }
        roundText.setText(String.valueOf(pgn.moveIDX/2 + 1));
    }

}
