import javax.swing.*;
import java.awt.*;

public class Window {

    GameManager gm;

    JFrame window;

    public Window(GameManager gm) {
        this.gm = gm;
        window = new JFrame();
        window.setLayout(null);
        window.setSize(1366, 794);
        window.getContentPane().setBackground(Color.black);
        //noinspection MagicConstant
        window.setDefaultCloseOperation(exit());
    }

    public void add(JPanel panel) {
        window.add(panel);
    }

    public void remove(JPanel panel) {
        window.remove(panel);
    }

    public int exit() {
        return 3;
    }


}
