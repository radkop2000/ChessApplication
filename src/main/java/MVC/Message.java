package MVC;

import javax.swing.*;

public class Message implements Runnable{

    String message;
    JLabel field;
    int seconds;

    public Message(JLabel field, String message, int seconds) {
        this.message = message;
        this.field = field;
        this.seconds = seconds;
    }

    /**
     * The function takes in a JTextField and a String and sets the text of the JTextField to the String for a certain
     * amount of time
     */
    @Override
    public void run() {
        field.setText(message);
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        field.setText("");


    }


}
