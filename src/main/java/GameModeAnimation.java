import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GameModeAnimation implements Runnable {

    JPanel panel;
    BoardDice board;
    JLabel tile;

    int[] nextMove;
    char nextMoveCh;

    boolean nextMoveIsCh;

    Thread thread;

    public GameModeAnimation(JPanel panel, BoardDice board) {
        this.panel = panel;
        this.board = board;
        tile = new JLabel();
        tile.setBounds(1124, 362, 88, 88);
        tile.setBackground(new Color(141, 97, 0));
        tile.setOpaque(true);
        panel.add(tile);
    }

    public void NextAnimation(int[] nextMove) {
        this.nextMove = nextMove;
        nextMoveIsCh = false;
        thread = new Thread(this);
        thread.start();
    }

    public void NextAnimation(char nextMoveCh) {
        this.nextMoveCh = nextMoveCh;
        nextMoveIsCh = true;
        thread = new Thread(this);
        thread.start();
    }

    private void stop(int mode) {
        System.out.println("STOPPING THREAD");
        board.highlight(mode);
        thread.stop();
    }

    @Override
    public void run() {
        int mode;
        if (nextMoveIsCh) {
            mode = 2;
        } else {
            nextMoveCh = board.getPiece(nextMove[0], nextMove[1]);
            mode = 1;
        }
        if (!board.ui.ui.op.animation) {
            stop(mode);
            return;
        }

        char turnOf = board.turnOf;
        char[] pieces = {'P', 'R', 'H', 'B', 'Q', 'K'};
        Random random = new Random();
        int timeout = 0;

        while (timeout < 700) {
            try {
                Thread.sleep(timeout);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            timeout += random.nextInt(timeout/4+50);
            ImageIcon icon = new ImageIcon("src/main/resources/Pieces/" + turnOf + pieces[random.nextInt(5)] + ".png");
            tile.setIcon(icon);
        }
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ImageIcon icon = new ImageIcon("src/main/resources/Pieces/" + turnOf + nextMoveCh + ".png");
        tile.setIcon(icon);

        stop(mode);
    }

}
