import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Clock implements Runnable {

    Board board;
    JPanel panel;
    JLabel timeB;
    JLabel timeW;

    int minW;
    int secW;
    int minB;
    int secB;
    boolean stop = false;

    public Clock(Board board, JPanel panel) {
        this.board = board;
        this.panel = panel;
        setup();
    }

    private void setup() {
        timeB = new JLabel("aaa");
        timeB.setBounds(1041, 120, 300, 50);
        timeB.setFont(new Font("Arial", Font.BOLD, 50));
        panel.add(timeB);

        timeW = new JLabel("aaa");
        timeW.setBounds(1041, 180, 300, 50);
        timeW.setFont(new Font("Arial", Font.BOLD, 50));
        panel.add(timeW);
    }

    @Override
    public void run() {
        stop = false;
        System.out.println("RUNNING CLOCK");
        minW = board.getClockTime();
        minB = board.getClockTime();
        secW = 0;
        secB = 0;
        updateTime();

        System.out.println(minW + "    " + minB);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("ss");
        LocalDateTime last = LocalDateTime.now();
        while (minW >= 0 && minB >= 0 && !stop) {
            LocalDateTime now = LocalDateTime.now();
            if (!dtf.format(last).equals(dtf.format(now))) {
                timeMinus(board.getTurnOf());
                updateTime();
            }
            last = now;
        }

    }

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

    private void updateTime() {
        timeW.setText(minW + ":" + secW);
        timeB.setText(minB + ":" + secB);
    }

    private void close() {
        stop = true;
    }


}
