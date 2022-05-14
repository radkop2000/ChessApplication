import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class devHandler implements ActionListener {

    Board board;

    public devHandler(Board board) {
        this.board = board;
    }

    /**
     * The actionPerformed function takes the action command of the button that was pressed, and uses the first two
     * characters to determine the piece type, and the last two characters to determine the x and y coordinates of the
     * button that was pressed
     *
     * @param e the ActionEvent that triggered the actionPerformed method
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String choice = e.getActionCommand().substring(0,2);
        int x = Integer.parseInt(e.getActionCommand().substring(2,3));
        int y = Integer.parseInt(e.getActionCommand().substring(3,4));
        board.putPiece(choice, x,y);
    }
}
