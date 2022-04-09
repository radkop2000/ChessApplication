import javax.swing.*;

public class ReplayUI extends JPanel implements UI {

    ManagerUI ui;
    Board board;
    PGN pgn;

    public ReplayUI(ManagerUI ui) {
        this.ui = ui;
        this.board = new Board(ui.op);
        this.pgn = new PGN(board);
    }


    @Override
    public void setup() {

    }
}
