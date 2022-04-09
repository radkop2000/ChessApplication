public class Operator {

    Window window;
    ManagerUI ui;
    Board board;



    public Operator() {
        window = new Window();
        Thread windowThread = new Thread(window);
        windowThread.start();
        window.setVisible(true);
        board = new Board(this);
        ui = new ManagerUI(this, window);
    }

    public static void main(String[] args) {
        Operator op = new Operator();
    }
}
