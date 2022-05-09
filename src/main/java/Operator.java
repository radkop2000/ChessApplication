import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Operator {

    Window window;
    ManagerUI ui;

    //config
    boolean dev;
    int computerDifficulty;
    int gameMode;
    boolean clock;
    int clockTime; // 1,3,5,10,15,60

    public Operator() {

        loadConfig();

        window = new Window();
        Thread windowThread = new Thread(window);
        windowThread.start();
        window.setVisible(true);
        ui = new ManagerUI(this, window);
    }

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
                if ((r=fis.read()) == -1) break;
                if ((char)r == '\n') {
                    lines.add(fileStr.toString());
                    fileStr = new StringBuilder();
                } else {
                    fileStr.append((char)r);
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
        System.out.println(dev);
        System.out.println(computerDifficulty);
        System.out.println(gameMode);
        System.out.println(clock);
        System.out.println(clockTime);
//        System.out.println(dict);

    }

    public static void main(String[] args) {
        Operator op = new Operator();
    }
}
