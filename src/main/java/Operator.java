import javax.swing.*;

public class Operator {

    Window window;
    ManagerUI ui;
    Board board;
    ManagerReplay replay;



    public Operator() {
        window = new Window();
        Thread windowThread = new Thread(window);
        windowThread.start();
        window.setVisible(true);
        board = new Board(this);
        ui = new ManagerUI(this, window);
        replay = new ManagerReplay(this);
    }

    public static void main(String[] args) {
        Operator op = new Operator();
    }
}
