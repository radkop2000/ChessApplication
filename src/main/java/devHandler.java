import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class devHandler implements ActionListener {

    Board board;

    public devHandler(Board board) {
        System.out.println("DEVHANDLER " + board);
        this.board = board;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("DEVHANDLER " + board);
        String choice = e.getActionCommand().substring(0,2);
        int x = Integer.parseInt(e.getActionCommand().substring(2,3));
        int y = Integer.parseInt(e.getActionCommand().substring(3,4));
        board.putPiece(choice, x,y);
    }
}
