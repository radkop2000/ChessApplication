//import java.util.ArrayList;
//import java.util.Random;
//
//public class ComputerCheater extends Thread implements Computer {
//
//    Board board;
//    char color;
//    int counterr;
//
//    public ComputerCheater(Board board, char color) {
//        this.board = board;
//        this.color = color;
//    }
//
//    @Override
//    public void run() {
//        nextTurn();
//    }
//
//    @Override
//    public void nextTurn() {
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException ignored) {
//        }
//        int counter = 0;
//        while (counter < 400) {
//            for (int i = 0; i < 8; i++) {
//                for (int j = 0; j < 8; j++) {
//                    if (board.getColor(i, j) == board.turnOf) {
//                        ArrayList<int[]> moves = board.movesWithoutCheck(i, j);
//                        for (int[] move : moves) {
//                            int heuristic = 0;
//                            for (int k = 0; k < 8; k++) {
//                                for (int l = 0; l < 8; l++) {
//                                    if (board.getColor(k,l) == 'W') {
//                                        heuristic -= board.heuristic(board.getPiece(k,l));
//                                    } else if (board.getColor(k, l) == 'B') {
//                                        heuristic += board.heuristic(board.getPiece(k,l));
//                                    }
//                                }
//                            }
//                            if (counter++ > 300) {
//                                counterr++;
//                                board.move(i, j, move[0], move[1]);
//                                if (heuristic < 0) {
//                                    board.putPiece("BQ", move[0], move[1]);
//                                }
//                                return;
//                            }
//                            if (board.getColor(move[0], move[1]) == board.not(color) && counter > 10) {
//                                board.move(i, j, move[0], move[1]);
//                                if (heuristic < 0) {
//                                    board.putPiece("BQ", move[0], move[1]);
//                                }
//                                return;
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//    @Override
//    public char getColor() {
//        return color;
//    }
//
//
//}
