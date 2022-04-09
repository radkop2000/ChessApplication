import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameUI extends JPanel implements UI{

    ManagerUI ui;
    JLabel background;
    Board board;

    Color light = new Color(180, 123, 0);
    Color lightDarker = light.darker().darker();
    Color dark = new Color(141, 97, 0);
    Color darkDarker = dark.darker().darker();

    public GameUI(ManagerUI ui) {
        this.ui = ui;
        this.board = ui.op.board;
        setup();
    }

    @Override
    public void setup() {
        setBounds(0,0,1366,768);
        setLayout(null);

        background = new JLabel();
        background.setBounds(0,0,1366,768);
        ImageIcon backgroundIcon = new ImageIcon("src/main/resources/MenuPlay.png");
        background.setIcon(backgroundIcon);

        JLabel buttonExit = new JLabel();
        buttonExit.setBounds(1040, 566, 243, 39);
        buttonExit.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ui.showPanel(ManagerUI.MENU_MAIN);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        add(buttonExit);

        add(background);
    }

    public void redrawBackground() {
        add(background);
        repaint();
    }

    public void showPromotion(char color) {
        JLabel queen = new JLabel();
        JLabel rook = new JLabel();
        JLabel bishop = new JLabel();
        JLabel horse = new JLabel();

        queen.setBounds(220 + -1 * 94, 7 + 2 * 94, 88, 88);
        queen.setBackground(dark);
        queen.setOpaque(true);
        ImageIcon queenIcon = new ImageIcon("src/main/resources/Pieces/" + color + "Q.png");
        queen.setIcon(queenIcon);
        queen.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                board.promote('Q');
                hidePromotion(queen, rook, bishop, horse);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                queen.setBackground(darkDarker);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                queen.setBackground(dark);
            }
        });

        rook.setBounds(220 + -1 * 94, 7 + 3 * 94, 88, 88);
        rook.setBackground(light);
        rook.setOpaque(true);
        ImageIcon rookIcon = new ImageIcon("src/main/resources/Pieces/" + color + "R.png");
        rook.setIcon(rookIcon);
        rook.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                board.promote('R');
                hidePromotion(queen, rook, bishop, horse);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                rook.setBackground(lightDarker);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                rook.setBackground(light);
            }
        });

        bishop.setBounds(220 + -1 * 94, 7 + 4 * 94, 88, 88);
        bishop.setBackground(dark);
        bishop.setOpaque(true);
        ImageIcon bishopIcon = new ImageIcon("src/main/resources/Pieces/" + color + "B.png");
        bishop.setIcon(bishopIcon);
        bishop.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                board.promote('B');
                hidePromotion(queen, rook, bishop, horse);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                bishop.setBackground(darkDarker);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                bishop.setBackground(dark);
            }
        });

        horse.setBounds(220 + -1 * 94, 7 + 5 * 94, 88, 88);
        horse.setBackground(light);
        horse.setOpaque(true);
        ImageIcon horseIcon = new ImageIcon("src/main/resources/Pieces/" + color + "H.png");
        horse.setIcon(horseIcon);
        horse.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                board.promote('H');
                hidePromotion(queen, rook, bishop, horse);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                horse.setBackground(lightDarker);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                horse.setBackground(light);
            }
        });


        add(queen);
        add(rook);
        add(bishop);
        add(horse);
        add(background);
        repaint();

    }

    private void hidePromotion(JLabel queen, JLabel rook, JLabel bishop, JLabel horse) {
        remove(queen);
        remove(rook);
        remove(bishop);
        remove(horse);
        repaint();
        board.endTurn();
    }




}
