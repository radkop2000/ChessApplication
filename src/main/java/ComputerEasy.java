public class ComputerEasy implements Computer{

    Board board;
    char color;

    public ComputerEasy(Board board) {
        this.board = board;
    }

    @Override
    public void nextTurn() {
        // TODO
    }

    @Override
    public char getColor() {
        return color;
    }
}
