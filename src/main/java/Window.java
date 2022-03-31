import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    JPanel activePanel;


    public Window() {
        setup();
        activePanel = new JPanel();
    }

    private void setup() {
        setLayout(null);
        setBounds(0,0,1366,768);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void showPanel(JPanel panel) {
        remove(activePanel);
        add(panel);
        activePanel = panel;
        repaint();
    }
}
