public class ManagerReplay {

    Operator op;
    Board board;

    public ManagerReplay(Operator op) {
        this.op = op;
        this.board = new Board(op);
    }
}
