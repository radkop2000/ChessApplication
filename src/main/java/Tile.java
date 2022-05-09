import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Tile extends JLabel {

    UI ui;
    int x;
    int y;
    boolean isLight;
    boolean isHighlighted;
    boolean isYellow;
    Color light = new Color(180, 123, 0);
    Color lightDarker = light.darker().darker();
    Color dark = new Color(141, 97, 0);
    Color darkDarker = dark.darker().darker();
    JPopupMenu devMenu;
    Board board;

    public Tile(UI ui, int x, int y, Board board) {
        this.ui = ui;
        this.x = x;
        this.y = y;
        this.board = board;

        ActionListener handler = new devHandler(board);
        JMenuItem[] items = new JMenuItem[12];
        String[] pieces = {"WP", "WH", "WB", "WR", "WQ", "WK", "BP", "BH", "BB", "BR", "BQ", "BK"};

        devMenu = new JPopupMenu();

        for (int i = 0; i < 12; i++) {
            items[i] = new JMenuItem(pieces[i]);
            items[i].addActionListener(handler);
            items[i].setActionCommand(pieces[i] + x + "" + y);
            devMenu.add(items[i]);
        }

        setBounds(220 + y * 94, 7 + x * 94, 88, 88);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e) && ui.getDev()) {
                    devMenu.show(ui.getComp(), e.getX() + 220 + y * 94, e.getY() + 7 + x * 94);
                } else {
                    ui.tilePressed(x, y);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                mouseOn();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mouseOff();
            }
        });
        if ((x+y)%2 == 0) {
            setBackground(light);
            isLight = true;
        } else {
            setBackground(dark);
            isLight = false;
        }
        isHighlighted = false;
        setOpaque(true);
    }

    public void setPiece(String piece) {
        ImageIcon icon = new ImageIcon("src/main/resources/Pieces/" + piece + ".png");
        setIcon(icon);
    }

    public void removePiece() {
        setIcon(null);
    }

    public void mouseOn() {
        if (isYellow) {
            return;
        }
        if (isLight) {
            setBackground(lightDarker);
        } else {
            setBackground(darkDarker);
        }
    }

    public void mouseOff() {
        if (isYellow) {
            return;
        }
        if (isHighlighted) {
            return;
        }
        if (isLight) {
            setBackground(light);
        } else {
            setBackground(dark);
        }
    }

    public void setDarker() {
        if (isLight) {
            setBackground(lightDarker);
        } else {
            setBackground(darkDarker);
        }
        isHighlighted = true;
        isYellow = false;
    }

    public void setLigher() {
        if (isLight) {
            setBackground(light);
        } else {
            setBackground(dark);
        }
        isHighlighted = false;
        isYellow = false;
    }

    public void setLighterNoYellow() {
        if (!isYellow) {
            setLigher();
        }
    }

    public void highlight() {
        setBackground(Color.yellow);
        isHighlighted = false;
        isYellow = true;
    }

}
