import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Operator {

    Window window;
    ManagerUI ui;
    Logger logger;

    //config
    boolean dev;
    int computerDifficulty;
    int gameMode;
    boolean clock;
    int clockTime; // 1,3,5,10,15,60
    boolean animation;


    public Operator() {

        loadConfig();

        logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        logger.setLevel(Level.INFO);

        logger.log(Level.INFO, "TESTING LOG");

        window = new Window();
        Thread windowThread = new Thread(window);
        windowThread.start();
        window.setVisible(true);
        ui = new ManagerUI(this, window);
    }

    /**
     * It reads the config.txt file and stores the values in the variables
     */
    public void loadConfig() {
        File file = new File("src/main/java/config.txt");
        FileInputStream fis;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        int r = 0;
        StringBuilder fileStr = new StringBuilder();
        ArrayList<String> lines = new ArrayList<>();
        while (true) {
            try {
                if ((r = fis.read()) == -1) break;
                if ((char) r == '\n') {
                    lines.add(fileStr.toString());
                    fileStr = new StringBuilder();
                } else {
                    fileStr.append((char) r);
                }
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
        Map<String, String> dict = new HashMap<>();
        String[] line;
        for (String str : lines) {
            line = str.split(" ", 2);
            dict.put(line[0], line[1]);
        }
        dev = Integer.parseInt(dict.get("dev")) == 1;
        computerDifficulty = Integer.parseInt(dict.get("computerDifficulty"));
        gameMode = Integer.parseInt(dict.get("gameMode"));
        clock = Integer.parseInt(dict.get("clock")) == 1;
        clockTime = Integer.parseInt(dict.get("clockTime"));
        animation = Integer.parseInt(dict.get("animation")) == 1;
        String settingsLog = new String(String.format("Settings:\ndev: %b\ncomputerDifficulty: %d\ngameMode: %d\nclock: %b\nclockTime: %d\nanimation: %b", dev, computerDifficulty, gameMode, clock, clockTime, animation));
        Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.INFO, settingsLog);
    }

    public static void main(String[] args) {
        Operator op = new Operator();
    }
}
