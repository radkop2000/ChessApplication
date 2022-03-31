import javax.swing.*;

public class Operator {

    Window window;
    Board board;
    ManagerUI ui;
    ManagerGame game;
    ManagerReplay replay;


    public Operator() {
        window = new Window();
        window.setVisible(true);
        ui = new ManagerUI(this, window);
        board = new Board(this);
        game = new ManagerGame(this, board);
        replay = new ManagerReplay(this, board);
    }

    public static void main(String[] args) {
        Operator op = new Operator();
    }
}
