import javax.swing.*;

public class Window extends JFrame implements Runnable {

    JPanel activePanel;


    public Window() {
        activePanel = new JPanel();
    }

    @Override
    public void run() {
        setLayout(null);
        setBounds(0,0,1366,798);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void showPanel(JPanel panel) {
        remove(activePanel);
        add(panel);
        activePanel = panel;
        repaint();
    }


}
