import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Locale;

public class ReplayUI extends JPanel implements UI {

    ManagerUI ui;
    BoardReplay board;
    Tile[][] tiles;

    JLabel background;
    JLabel turnLabel;
    JLabel turnOfLabel;
    JTextField jumpTo;
    JLabel message;


    public ReplayUI(ManagerUI ui) {
        this.ui = ui;
        setup();
        board = new BoardReplay(this);
    }


    public void setup() {
        setBounds(0,0,1366,768);
        setLayout(null);

        message = new JLabel("", SwingConstants.CENTER);
        message.setBounds(0, 768/2-30, 1366, 60);
        message.setForeground(Color.red.darker());
        message.setFont(new Font("Arial", Font.BOLD, 50));
        add(message);

        tiles = new Tile[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j <8; j++) {
                tiles[i][j] = new Tile(this, i, j, board);
                add(tiles[i][j]);
            }
        }

        background = new JLabel();
        background.setBounds(0,0,1366,768);
        ImageIcon backgroundIcon = new ImageIcon("src/main/resources/MenuReplay.png");
        background.setIcon(backgroundIcon);

        turnLabel = new JLabel("1");
        turnLabel.setBounds(1190, 25, 100, 50);
        turnLabel.setFont(new Font("Zapfino", Font.BOLD, 25));
        add(turnLabel);

        turnOfLabel = new JLabel("White");
        turnOfLabel.setBounds(1190, 73, 100, 50);
        turnOfLabel.setFont(new Font("Zapfino", Font.BOLD, 25));
        add(turnOfLabel);

        JLabel buttonNext = new JLabel();
        buttonNext.setBounds(1043, 289, 220, 40);
        buttonNext.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!board.pgn.nextMove()) {
                    showMessage("End of game is reacehd", 4);
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
        add(buttonNext);

        JLabel buttonPrev = new JLabel();
        buttonPrev.setBounds(1043, 378, 280, 35);
        buttonPrev.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                board.pgn.prevMove();
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
        add(buttonPrev);

        JLabel buttonExit = new JLabel();
        buttonExit.setBounds(1043, 462, 260, 40);
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

        jumpTo = new JTextField();
        jumpTo.setBounds(1040, 528, 150, 60);
        jumpTo.setFont(new Font("Arial", Font.BOLD, 40));
        add(jumpTo);

        JLabel buttonJump = new JLabel();
        buttonJump.setBounds(1040, 600, 313, 45);
        buttonJump.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jumpTo(jumpTo.getText());
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
        add(buttonJump);


        add(background);
        repaint();
    }

    @Override
    public void tilePressed(int x, int y) {}

    @Override
    public Component getComp() {
        return this;
    }

    @Override
    public boolean getDev() {
        return ui.op.dev;
    }


    public void showMessage(String message, int seconds) {
        Runnable runnable = new Message(this.message, message, seconds);
        Thread thread = new Thread(runnable);
        thread.start();
    }


    public void putPiece(String piece, int x, int y) {
        tiles[x][y].setPiece(piece);
    }

    public void removePiece(int x, int y) {
        tiles[x][y].removePiece();
    }

    public void updateText() {
        turnLabel.setText(String.valueOf(board.pgn.moveNum/2 + 1));
        turnOfLabel.setText(board.pgn.moveNum % 2 == 0 ? "White" : "Black");
    }

    public void jumpTo(String jumpTo) {
        int jump;
        try {
            jump = Integer.parseInt(jumpTo);
            System.out.println("JUMP   " + jump);
            if (jump < 0) {
                throw new IllegalArgumentException();
            }
            board.pgn.moveTo(jump*2-1);
        } catch (Exception e) {
            if (jumpTo.toLowerCase(Locale.ROOT).equals("end")) {
                board.pgn.moveToEnd();
            } else {
                showMessage("Please enter a valid turn number or 'end'", 5);
            }
        }

    }
}
