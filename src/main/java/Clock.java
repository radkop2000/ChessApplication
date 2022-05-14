import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Clock implements Runnable {

    BoardGame board;
    JPanel panel;
    JLabel timeB;
    JLabel timeW;

    int minW;
    int secW;
    int minB;
    int secB;

    public Clock(BoardGame board, JPanel panel) {
        this.board = board;
        this.panel = panel;
        setup();
    }

    /**
     * This function sets up the time labels for both the black and white players
     */
    private void setup() {
        timeB = new JLabel("");
        timeB.setBounds(1041, 120, 300, 50);
        timeB.setFont(new Font("Arial", Font.BOLD, 50));
        panel.add(timeB);

        timeW = new JLabel("");
        timeW.setBounds(1041, 180, 300, 50);
        timeW.setFont(new Font("Arial", Font.BOLD, 50));
        panel.add(timeW);
    }


    /**
     * > The function runs a thread that updates the time on the clock every second
     */
    @Override
    public void run() {
        if (!board.ui.ui.op.clock) {
            timeW.setText("");
            timeB.setText("");
            return;
        }

        minW = board.getClockTime();
        minB = board.getClockTime();
        secW = 0;
        secB = 0;
        updateTime();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ss");
        LocalDateTime last = LocalDateTime.now();
        while (minW >= 0 && minB >= 0) {
            LocalDateTime now = LocalDateTime.now();
            if (!dtf.format(last).equals(dtf.format(now))) {
                timeMinus(board.getTurnOf());
                updateTime();
            }
            last = now;
        }

    }

    /**
     * If the color is white, subtract one second from the white time, and if the white time is less than zero, set the
     * white time to 59, and if the white time is less than zero, end the game. If the color is black, subtract one second
     * from the black time, and if the black time is less than zero, set the black time to 59, and if the black time is
     * less than zero, end the game
     *
     * @param color the color of the player whose time is being decreased
     */
    private void timeMinus(char color) {
        if (color == 'W') {
            if (--secW < 0) {
                secW = 59;
                if (--minW < 0) {
                    board.endGameTime('W');
                }
            }
        } else {
            if (--secB < 0) {
                secB = 59;
                if (--minB < 0) {
                    board.endGameTime('B');
                }
            }
        }
    }

    /**
     * It updates the time on the screen
     */
    private void updateTime() {
        timeW.setText(minW + ":" + secW);
        timeB.setText(minB + ":" + secB);
    }


}
