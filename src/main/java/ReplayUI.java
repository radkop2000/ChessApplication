import javax.swing.*;

public class ReplayUI extends JPanel implements UI {

    ManagerUI ui;
    Board board;
    PGN pgn;

    public ReplayUI(ManagerUI ui) {
        this.ui = ui;
        board = new BoardReplay(this);
//        this.pgn = new PGN(board);
    }


    public void setup() {

    }

    @Override
    public void tilePressed(int x, int y) {

    }
}
