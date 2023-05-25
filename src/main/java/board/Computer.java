package board;

import board.BoardGame;
import board.NextMoveCheater;
import board.NextMoveEasy;
import board.NextMoveRandom;

public class Computer {

    BoardGame board;
    char color;
    int difficulty; // 0 == random, 1 == easy, 2 == cheater

    public Computer(BoardGame board, char color, int difficulty) {
        this.board = board;
        this.color = color;
        this.difficulty = difficulty;
    }

    /**
     * The function is called when the player makes a move. It creates a new thread that runs
     * the appropriate AI algorithm based on the difficulty level
     */
    public void nextTurn() {
        switch (difficulty) {
            case 0 -> {
                Thread thread1 = new Thread(new NextMoveRandom(board, color));
                thread1.start();
            }
            case 1 -> {
                Thread thread3 = new Thread(new NextMoveEasy(board, color));
                thread3.start();
            }
            case 2 -> {
                Thread thread2 = new Thread(new NextMoveCheater(board, color));
                thread2.start();
            }
        }
    }

    /**
     * This function returns the color of the computer.
     *
     * @return The color of the car.
     */
    public char getColor() {
        return color;
    }

}
