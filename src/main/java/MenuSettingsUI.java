import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MenuSettingsUI extends JPanel{

    ManagerUI ui;
    boolean dev;


    JLabel background;
    JButton buttonComputer;
    JButton buttonDev;
    JButton buttonMode;
    JButton buttonClock;
    JLabel buttonBack;
    JLabel buttonDefault;
    JButton buttonClockTime;
    JButton buttonAnimation;

    public MenuSettingsUI(ManagerUI ui) {
        this.ui = ui;
        setup();
        dev = ui.op.dev;
    }

    /**
     * It sets up the settings panel
     */
    public void setup() {
        setBounds(0,0,1366,768);
        setLayout(null);

        background = new JLabel();
        background.setBounds(0,0,1366,768);
        ImageIcon backgroundIcon = new ImageIcon("src/main/resources/Settings.png");
        background.setIcon(backgroundIcon);

        buttonAnimation = new JButton("GameMode Animation");
        buttonAnimation.setBounds(100,300,250,40);
        buttonAnimation.addActionListener(e -> {
            ui.op.animation = !ui.op.animation;
            setAnimationText();
        });
        setAnimationText();
        add(buttonAnimation);

        buttonDev = new JButton("Dev Mode");
        buttonDev.setBounds(100,350,250,40);
        buttonDev.addActionListener(e -> {
            ui.op.dev = !ui.op.dev;
            setDevText();
        });
        setDevText();
        add(buttonDev);

        buttonComputer = new JButton("");
        buttonComputer.setBounds(100,400, 250, 40);
        buttonComputer.addActionListener(e -> {
            ui.op.computerDifficulty = (ui.op.computerDifficulty + 1) % 3;
            setComputerText();
        });
        setComputerText();
        add(buttonComputer);

        buttonMode = new JButton("");
        buttonMode.setBounds(100,450, 250, 40);
        buttonMode.addActionListener(e -> {
            ui.op.gameMode = (ui.op.gameMode + 1) % 3;
            setModeText();
        });
        setModeText();
        add(buttonMode);

        buttonClock = new JButton("Clock");
        buttonClock.setBounds(100,500,250,40);
        buttonClock.addActionListener(e -> {
            ui.op.clock = !ui.op.clock;
            setClockText();
        });
        setClockText();
        add(buttonClock);

        buttonClockTime = new JButton("");
        buttonClockTime.setBounds(100, 550, 250, 40);
        buttonClockTime.addActionListener(e -> {
            ui.op.clockTime = (ui.op.clockTime + 1) % 6;
            setClockTime();
        });
        setClockTime();
        add(buttonClockTime);


        buttonBack = new JLabel();
        buttonBack.setBounds(91, 631, 106, 41);
        buttonBack.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                save();
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
        add(buttonBack);

        buttonDefault = new JLabel();
        buttonDefault.setBounds(238, 631, 310, 60);
        buttonDefault.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                resetSettings();
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
        add(buttonDefault);





        add(background);
    }

    /**
     * If the developer mode is on, set the text color to green, otherwise set it to red.
     */
    public void setDevText() {
        buttonDev.setForeground(ui.op.dev ? Color.green.darker() : Color.red);
    }

    /**
     * If the computer difficulty is 0, set the text to "ComputerDifficulty: Random", set the color to green, else if the
     * computer difficulty is 1, set the text to "ComputerDifficulty: Easy", set the color to orange, else if the computer
     * difficulty is 2, set the text to "ComputerDifficulty: Cheater", set the color to red
     */
    public void setComputerText() {
        if (ui.op.computerDifficulty == 0) {
            buttonComputer.setText("ComputerDifficulty: Random");
            buttonComputer.setForeground(Color.green.darker());
        } else if (ui.op.computerDifficulty == 1) {
            buttonComputer.setText("ComputerDifficulty: Easy");
            buttonComputer.setForeground(Color.orange.darker());
        } else if (ui.op.computerDifficulty == 2) {
            buttonComputer.setText("ComputerDifficulty: Cheater");
            buttonComputer.setForeground(Color.red);
        }
    }

    /**
     * It sets the text of the buttonMode JButton to the current game mode
     */
    public void setModeText() {
        if (ui.op.gameMode == 0) {
            buttonMode.setText("GameMode: Random type of piece");
        } else if (ui.op.gameMode == 1) {
            buttonMode.setText("GameMode: Random piece");
        } else if (ui.op.gameMode == 2) {
            buttonMode.setText("GameMode: Chaos");
        }
    }

    /**
     * If the clock is on, set the clock button to green, otherwise set it to red
     */
    public void setClockText() {
        if (ui.op.clock) {
            buttonClock.setForeground(Color.green.darker());
        } else {
            buttonClock.setForeground(Color.red);
    // Saving the settings to a file.
        }
    }

    /**
     * Saving the settings to a file.
     */
    public void save() {
        BufferedWriter writer;
        String str = String.format("""
                dev %d
                computerDifficulty %d
                gameMode %d
                clock %d
                clockTime %d
                animation %d
                """, ui.op.dev ? 1 : 0, ui.op.computerDifficulty, ui.op.gameMode, ui.op.clock ? 1 : 0, ui.op.clockTime, ui.op.animation ? 1 : 0);
        try {
            writer = new BufferedWriter(new FileWriter("src/main/java/config.txt"));
            writer.write(str);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Resetting the settings to default.
     */
    public void resetSettings() {
        ui.op.dev = false;
        ui.op.computerDifficulty = 0;
        ui.op.gameMode = 0;
        ui.op.clock = false;
        ui.op.clockTime = 0;
        ui.op.animation = false;
        setDevText();
        setComputerText();
        setModeText();
        setClockText();
        setClockTime();
        setAnimationText();
    }


    /**
     * It sets the text of the buttonClockTime JButton to the corresponding time from an array.
     */
    public void setClockTime() {
        int[] time = {1,3,5,10,15,60};
        buttonClockTime.setText("Time on clock: " + time[ui.op.clockTime] + " minutes");
    }


    /**
     * It sets the text of the buttonAnimation JButton to the current animation setting.
     */
    public void setAnimationText() {
        buttonAnimation.setForeground(ui.op.animation ? Color.green.darker() : Color.red);
    }


}
